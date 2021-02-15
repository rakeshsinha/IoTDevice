package dao;

/*
 * The interface design pattern, this is an interface for all data classes
 */
public interface DataObject {
	public String getEntityLogicalName();
	public String getId();
}
