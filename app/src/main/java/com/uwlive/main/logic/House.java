package com.uwlive.main.logic; /**
 * 
 */

/**
 * @author yz-li
 *
 */
public class House {

	/**
	 * 
	 */
	private String housename;
	private String description;
	//private ArrayList<String> comments;
	private double price;
	private String ownerID; //Owner user ID
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	private String HID; // House ID. This is UID + number of house created by the User.
	
	public House(String housename, String description, String hid) {
		// TODO Auto-generated constructor stub
		this.housename = housename;
		this.description = description;
		this.HID = hid;
		//comments = null;
	}
	public House(String housename, String description, double price, String hid, String owner) {
		// TODO Auto-generated constructor stub
		this.housename = housename;
		this.description = description;
		this.HID = hid;
		this.price = price;
		this.ownerID = owner;
		//comments = null;
	}
	/*public void addcomment(String comment) {
		comments.add(comment);
	}*/
	/**
	 * @return the housename
	 */
	public String getHousename() {
		return housename;
	}
	/**
	 * @param housename the housename to set
	 */
	public void setHousename(String housename) {
		this.housename = housename;
	}
	/**
	 * @return the description
	 */
	public String getdescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setdescription(String description) {
		this.description = description;
	}
	/**
	 * @return the hID
	 */
	public String getHID() {
		return HID;
	}
	/**
	 * @param hid the hID to set
	 */
	public void setHID(String hid) {
		HID = hid;
	}
	/**
	 * @return the comments
	 */
	/*public ArrayList<String> getComments() {
		return comments;
	}*/
	@Override
	public String toString(){
		return "\n-----------------------------------------------------------------\n"+"| House ID: " + HID + " Housename: " + housename + " Price: " + price + " |\n-----------------------------------------------------------------";
	}

}
