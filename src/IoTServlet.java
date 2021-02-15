import java.io.IOException;
import java.time.LocalTime;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.DataObject;
import dao.Device;
import dao.DeviceHeartBeat;
import dao.Employee;
import dao.EmployeeTimeEntry;
import dataAccess.DataStore;
import dataAccess.DataStoreCache;
import dataAccess.Events.Event;

@SuppressWarnings("serial")
public class IoTServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// If the date is marked as restricted, i.e. a holiday then noone is allowed
		if (DataStore.isDateRestricted) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		// Check if the header has the required token of authentication
		String authHeader = request.getHeader("authorization");
		String encodedToken = (authHeader != null) ? authHeader.substring(authHeader.indexOf(' ') + 1) : "";
		String decodedToken = new String(Base64.getDecoder().decode(encodedToken));
		
		if(!DataStoreCache.isTokenAccepted(decodedToken)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		// Check if the device is the right one
		String deviceId = request.getParameter("deviceId");
		DataObject deviceObject = DataStore.getData(Device.EntityLogicalName, deviceId);
		if (deviceObject == null || !((Device)(deviceObject)).getToken().equals(decodedToken)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String eventString = request.getParameter("event");
		Event event = Event.valueOf(eventString);
		switch (event) {
			case HeartBeat:	// Store every heart beat for analyzing
				LocalTime heartBeatTime = LocalTime.now();
				DataStore.saveData(new DeviceHeartBeat(deviceId + heartBeatTime, deviceId, heartBeatTime));				
			break;
			
			case Enter:
			case Exit:
				String empId = request.getParameter("empId");
				if (!Employee.isValid(empId)) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}
				
				// If the employee is marked as restricted then don't allow him
				if (Employee.isRestricted(empId)) {
					String json = "{\n";
					json += "\"result\": " + JSONObject.quote("not_allowed") + ",\n";
					json += "}";
					response.getOutputStream().println(json);
					response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
					return;
				}
				
				LocalTime entryTime = null;
				LocalTime exitTime = null;
				LocalTime localTime = LocalTime.now();
				if (event.equals(Event.Enter)) {
					entryTime = localTime;
				}
				else {
					exitTime = localTime;
				}
				
				DataStore.saveData(
						new EmployeeTimeEntry(empId + deviceId + localTime,
								empId, deviceId, entryTime, exitTime));
				String json = "{\n";
				json += "\"result\": " + JSONObject.quote("allowed") + ",\n";
				json += "}";
				response.getOutputStream().println(json);
				response.setStatus(HttpServletResponse.SC_ACCEPTED);
			break;
			
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}
}