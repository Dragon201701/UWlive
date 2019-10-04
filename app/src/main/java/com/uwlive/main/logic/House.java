package com.uwlive.main.logic; /**
 * 
 */

/**
 * @author yz-li
 *
 */
import java.util.*;
public class House {

	/**
	 * 
	 */
	private String housename;
	private String description;
	private ArrayList<String> comments;
	private int price;
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	private int HID; // House ID. This is UID + number of house created by the User.
	
	public House(String housename, String description, int hid) {
		// TODO Auto-generated constructor stub
		this.housename = housename;
		this.description = description;
		this.HID = hid;
		comments = null;
	}
	public void addcomment(String comment) {
		comments.add(comment);
	}
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
	public int getHID() {
		return HID;
	}
	/**
	 * @param hID the hID to set
	 */
	public void setHID(int hID) {
		HID = hID;
	}
	/**
	 * @return the comments
	 */
	public ArrayList<String> getComments() {
		return comments;
	}

}
