package dao;

import java.time.LocalTime;

/*
 * This class holds the device heart beat information
 */
public class DeviceHeartBeat implements DataObject {
	public static final String EntityLogicalName = "DeviceHeartBeat";
	
	public DeviceHeartBeat(String id, String deviceId, LocalTime heartBeatTimestamp) {
		this.id = id;
		this.deviceId = deviceId;
		this.heartBeatTimestamp = heartBeatTimestamp;
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public LocalTime getHeartBeatTimestamp() {
		return heartBeatTimestamp;
	}
	
	public void setHeartBeatTimestamp(LocalTime heartBeatTimestamp) {
		this.heartBeatTimestamp = heartBeatTimestamp;
	}
	
	private String id;
	private String deviceId;
	private LocalTime heartBeatTimestamp;
	
	@Override
	public String getEntityLogicalName() {
		return EntityLogicalName;
	}
}
