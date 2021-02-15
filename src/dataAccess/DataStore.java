package dataAccess;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import dao.DataObject;
import dao.Device;
import dao.DeviceHeartBeat;
import dao.Employee;
import dao.EmployeeTimeEntry;

/*
 * This class can be replaced when there is an actual db
 * Right now we have hard coded data originating from this class
 */
public class DataStore {
	private static Map<String, Map<String, DataObject>>
		dataMapper = new HashMap<String, Map<String, DataObject>>();
	public static HashSet<String> restrictedEmployees = new HashSet<String>();
	
	public static boolean isDateRestricted = false;
	
	private static DataStore instance = new DataStore();
	public static DataStore getInstance(){
		return instance;
	}

	private DataStore() {
		HashMap<String, DataObject> deviceMap = new HashMap<String, DataObject>();
		deviceMap.put("IoT1", new Device("IoT1", "abcd+-"));
		deviceMap.put("IoT2", new Device("IoT1", "1234+-"));
		deviceMap.put("IoT3", new Device("IoT1", "whatIsThis"));
		
		HashMap<String, DataObject> employeeMap = new HashMap<String, DataObject>();
		employeeMap.put("Emp1", new Employee("Emp1", "SomeName"));
		employeeMap.put("Emp2", new Employee("Emp2", "NoName"));
		employeeMap.put("Emp3", new Employee("Emp3", "WhyName"));
		
		dataMapper.put(Device.EntityLogicalName, deviceMap);
		dataMapper.put(Employee.EntityLogicalName, employeeMap);
		dataMapper.put(EmployeeTimeEntry.EntityLogicalName, new HashMap<String, DataObject>());
		dataMapper.put(DeviceHeartBeat.EntityLogicalName, new HashMap<String, DataObject>());
	}
	
	public static boolean saveData(DataObject dataObject) {
		if (!dataMapper.containsKey(dataObject.getEntityLogicalName())) {
			return false;
		}
		
		Map<String, DataObject> dataObjectMap = dataMapper.get(dataObject.getEntityLogicalName());
		dataObjectMap.put(dataObject.getId(), dataObject);
		
		return true;
	}
	
	public static DataObject getData(String entityLogicalName, String id) {
		if (id == null || !dataMapper.get(entityLogicalName).containsKey(id)) {
			return null;
		}
		
		return dataMapper.get(entityLogicalName).get(id);
	}
}