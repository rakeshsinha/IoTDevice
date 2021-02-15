package dao;

import dataAccess.DataStore;

/*
 * This class holds the employee information
 */
public class Employee implements DataObject {
	public static final String EntityLogicalName = "Employee";
	
	public Employee(String empId, String empName) {
		this.empId = empId;
		this.empName = empName;
	}
	
	@Override
	public String getId() {
		return empId;
	}
	
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	public String getEmpName() {
		return empName;
	}
	
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	private String empId;
	private String empName;
	
	@Override
	public String getEntityLogicalName() {
		return EntityLogicalName;
	}
	
	public static boolean isValid(String empId) {
		if (DataStore.getData(EntityLogicalName, empId) == null) {
			return false;
		}

		return true;
	}
	
	public static boolean isRestricted(String empId) {
		if (DataStore.restrictedEmployees.contains(empId)) {
			return true;
		}
		
		return false;
	}
}
