package dao;

/*
 * This class holds the device information
 */
public class Device implements DataObject {
	public static final String EntityLogicalName = "Device";
	
	public Device(String id, String token) {
		this.deviceId = id;
		this.token = token;
	}
	
	@Override
	public String getId() {
		return deviceId;
	}
	
	public void setId(String id) {
		this.deviceId = id;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	private String deviceId;
	private String token;

	@Override
	public String getEntityLogicalName() {
		return EntityLogicalName;
	}

	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
}