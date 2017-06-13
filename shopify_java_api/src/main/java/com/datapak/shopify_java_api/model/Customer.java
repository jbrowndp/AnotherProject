package com.datapak.shopify_java_api.model;

import java.util.Date;

import org.json.simple.JSONObject;

/** An object containing information about the customer.
 * 
 * @author jbrown
 * @version 1.0
 *
 */
@SuppressWarnings("unchecked")
public class Customer extends ShopifyObject{

	private boolean accepts_marketing;
	
	private Date created_at;
	private String email;
	private String phone;
	private String first_name;
	private Long id;
	private String last_name;
	private String note;
	private Long orders_count;
	private String state;
	private String total_spent;
	private Date updated_at;
	private String tags;
	
	
	/** Indicates whether or not the customer would like to receive email updates from the ship.
	 * 
	 * @return A Boolean value containing true or false
	 */
	public boolean isAccepts_marketing() {
		return accepts_marketing;
	}
	
	
	/** Indicates whether or not the customer would like to receive email updates from the ship.
	 * 
	 * @param accepts_marketing A Boolean value containing true or false
	 */
	public void setAccepts_marketing(boolean accepts_marketing) {
		this.accepts_marketing = accepts_marketing;
	}
	
	/** Gets the date and time when the customer record was created.
	 * 
	 * @return a Date object containing the date and time when the customer record was created.
	 */
	public Date getCreated_at() {
		return created_at;
	}
	
	/** Sets the date and time when the customer record was created.
	 * 
	 * @param created_at a Date object containing the date and time when the customer record was created\
	 *        through Shopify POS
	 */
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	
	/** Gets the customer's email address
	 * 
	 * @return A String object containing the customer's email address
	 */
	public String getEmail() {
		return email;
	}
	
	/** Sets the customer's email address
	 * 
	 * @param email A String object containing the customer's email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/** Gets the customer's phone number.
	 * 
	 * @return A String object containing the customer's phone number
	 */
	public String getPhone() {
		return phone;
	}
	
	/** Sets the customer's phone number.
	 * 
	 * @param phone A String object containing the customer's phone number.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/** Gets the customer's first name
	 * 
	 * @return A String object containing the customer's first name
	 */
	public String getFirst_name() {
		return first_name;
	}
	
	
	/** Sets the customer's first name
	 * 
	 * @param first_name A String object containing the customer's first name
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	/** Gets the unique numeric identifier for the customer
	 * 
	 * @return A String containing the unique numeric identifier for the customer
	 */
	public Long getId() {
		return id;
	}
	
	/** Sets the unique numeric identifier for the customer
	 * 
	 * @param id A String containing the unique numeric identifier for the customer
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/** Gets the customer's last name 
	 * 
	 * @return A String containing the customer's last name
	 */
	public String getLast_name() {
		return last_name;
	}
	
	/** Sets the customer's last name
	 * 
	 * @param last_name A String containing the customer's last name
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	/** Gets extra information about the customer (if available)
	 * 
	 * @return A String containing extra information about the customer
	 */
	public String getNote() {
		return note;
	}
	
	/** Sets extra information about the customer (if available)
	 * 
	 * @param note A String containing extra information about the customer
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	/** Gets the number of orders placed by this customer to a shop. Pulls the current information.
	 * 
	 * @return A String containing the number of orders placed by this customer to a shop.
	 */
	public Long getOrders_count() {
		return orders_count;
	}
	
	/** Sets the number of orders placed by this customer to a shop. Pulls the current information
	 * 
	 * @param orders_count A String containing the number of orders placed by this customer to a shop
	 */
	public void setOrders_count(Long orders_count) {
		this.orders_count = orders_count;
	}
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getTotal_spent() {
		return total_spent;
	}
	public void setTotal_spent(String total_spent) {
		this.total_spent = total_spent;
	}
	
	/** Gets the date and time when the customer record was last updated. 
	 * 
	 * @return A String containing the date and time when the customer record was last updated.
	 */
	public Date getUpdated_at() {
		return updated_at;
	}
	
	/** Sets the date and time when the customer record was last updated.
	 * 
	 * @param updated_at A Date object containing the date and time when the custoemr record was last updated
	 */
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	/** Gets tags - Tags are additional short descriptiors, commonly used for filtering and searching, 
	 * formatted as a string of comma-separated values.
	 * @return A String containing tags
	 */
	public String getTags() {
		return tags;
	}
	
	/** Sets tags - Tags are additional short descriptiors, commonly used for filtering and searching, 
	 * formatted as a string of comma-separated values.
	 * @param tags A String containing tags
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
	/** 
	 * @see ShopifyObject
	 */
	@Override
	public JSONObject toJSON() {
		
		JSONObject obj = new JSONObject();
		
		obj.put("accepts_marketing", accepts_marketing);
		obj.put("created_at", created_at);
		obj.put("email", email);
		obj.put("phone", phone);
		obj.put("first_name", first_name);
		obj.put("id", id);
		obj.put("last_name", last_name);
		obj.put("note", note);
		obj.put("orders_count", orders_count);
		obj.put("state", state);
		obj.put("total_spent", total_spent);
		obj.put("updated_at", updated_at);
		obj.put("tags", tags);
		
		return obj;
		
	}
	
	/**
	 * @see ShopifyObject
	 */
	@Override
	public void fromJSON(JSONObject json) {
		
		
		if (json==null) { return; }
		
		this.setAccepts_marketing((boolean) json.get("accepts_marketing"));
		this.setCreated_at(ISODate((String) json.get("created_at")));
		this.setEmail((String) json.get("email"));
		this.setPhone((String) json.get("phone"));
		this.setFirst_name((String) json.get("first_name"));
		this.setId((Long) json.get("id"));
		this.setLast_name((String) json.get("last_name"));
		this.setNote((String) json.get("note"));
		this.setOrders_count((Long) json.get("orders_count"));
		this.setState((String) json.get("state"));
		this.setTotal_spent((String) json.get("total_spent"));
		this.setUpdated_at(ISODate((String) json.get("updated_at")));
		this.setTags((String) json.get("tags"));
		
		
	}
	
}
