package dao;

import java.time.LocalTime;

/*
 * This class holds the information of every time an employee enters or exits the building
 * This can be imagined as many to many relationship between device and employee ids
 */
public class EmployeeTimeEntry implements DataObject {
	public static final String EntityLogicalName = "EmployeeTimeEntry";
	
	public EmployeeTimeEntry(String id, String empId, String deviceId, LocalTime entryTime, LocalTime exitTime) {
		this.id = id;
		this.empId = empId;
		this.deviceId = deviceId;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEmpId() {
		return empId;
	}
	
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public LocalTime getEntryTime() {
		return entryTime;
	}
	
	public void setEntryTime(LocalTime entryTime) {
		this.entryTime = entryTime;
	}
	
	public LocalTime getExitTime() {
		return exitTime;
	}
	
	public void setExitTime(LocalTime exitTime) {
		this.exitTime = exitTime;
	}

	private String id;
	private String empId;
	private String deviceId;
	private LocalTime entryTime;
	private LocalTime exitTime;
	
	@Override
	public String getEntityLogicalName() {
		return EntityLogicalName;
	}
}
