package com.datapak.shopify_java_api.model;

import java.util.Date;

import org.json.simple.JSONObject;

public class FulFillment extends ShopifyObject {
	
	
	private Date created_at;
	private Long id;
	private String status;
	private String tracking_company;
	private String tracking_number;
	private Date update_at;
	
	
	
	/**
	 * Gets the date and time when the fullfillment was created. 
	 * @return  A Date object containing the date and time when the fulfillment was created. 
	 */
	public Date getCreated_at() {
		return created_at;
	}
	/**Sets the date and time when the fullfillment was created. 
	 * @param created_at The date and time when the fulfillment was created.
	 */
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	/**
	 * Gets the unique numeric identifier for the fulfillment
	 * @return The unique numeric identifier for the fulfillment.
	 */
	public Long getId() {
		return id;
	}
	/** 
	 * Sets the unique numeric identifier for the fulfillment
	 * @param id The unique numeric identifier for the fulfillment.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/** 
	 * Gets the status of the fulfilment
	 * @return The status of the fulfillment.
	 */
	public String getStatus() {
		return status;
	}
	/** Sets the status of the fulfilment
	 * @param status The status of the fulfillment.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * Gets the name of the shipping company
	 * @return The name of the shipping company
	 */
	public String getTracking_company() {
		return tracking_company;
	}
	/** Sets the name of the shipping company
	 * @param tracking_company The name of the shipping company
	 */
	public void setTracking_company(String tracking_company) {
		this.tracking_company = tracking_company;
	}
	/**
	 * Gets the shipping number, provided by the shipping company.
	 * @return The shipping number, provided by the shipping company.
	 */
	public String getTracking_number() {
		return tracking_number;
	}
	/**
	 * Sets the shipping number, provided by the shipping company
	 * @param tracking_number The shipping number, provided by the shipping company.
	 */
	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}
	/**
	 * Gets the date and time when the fulfillment was last modified
	 * @return The date and time when the fulfillment was last modified.
	 */
	public Date getUpdate_at() {
		return update_at;
	}
	/**
	 * Sets the date and time when the fulfillment was last modified
	 * @param update_at The date and time when the fulfillment was last modified.
	 */
	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FulFillment [created_at=" + created_at + ", id=" + id + ", status=" + status + ", tracking_company="
				+ tracking_company + ", tracking_number=" + tracking_number + ", update_at=" + update_at + "]";
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		
		JSONObject obj= new JSONObject();
		obj.put("created_at", created_at);
		obj.put("id", id);
		obj.put("status", status);
		obj.put("tracking_company", tracking_company);
		obj.put("tracking_number", tracking_number);
		obj.put("update_at", update_at);
		
		return obj;
	}
	@Override
	public void fromJSON(JSONObject json) {
		
		this.setCreated_at((Date) json.get("created_at"));
		this.setId((Long) json.get("id"));
		this.setStatus((String) json.get("status"));
		this.setTracking_company((String) json.get("tracking_company"));
		this.setTracking_number((String) json.get("tracking_number"));
		this.setUpdate_at((Date) json.get("update_at"));
		
	}
	
	
	

}
