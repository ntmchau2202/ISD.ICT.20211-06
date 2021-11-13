package entities;

/**
 * This is the class for object entity Dock including all information of the dock
 * @author Duong
 *
 */
public class Dock {

	/**
	 * name of the dock
	 */
	private String name;
	
	/**
	 * An unique string describes the id of the dock
	 */
	private String dock_id;
	
	/**
	 * The address of the dock
	 */
	private String dock_address;
	
	/**
	 * The area of the dock calculated in meter square
	 */
	private String dock_area;
	
	/**
	 * The number of available bikes in the dock that customers can rent
	 */
	private int num_available_bike;

	/**
	 * The number of blank occupation for customers to return bike 
	 */
	private int num_dock_space_free;
	
	public Dock() {
		
	}

	public Dock(String name, String dock_id, String dock_address, String dock_area, int num_available_bike,
			int num_dock_space_free) {
		super();
		this.name = name;
		this.dock_id = dock_id;
		this.dock_address = dock_address;
		this.dock_area = dock_area;
		this.num_available_bike = num_available_bike;
		this.num_dock_space_free = num_dock_space_free;
	}
	
}
