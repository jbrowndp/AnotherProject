package com.datapak.shopify_java_api.model;

import org.json.simple.JSONObject;

public class TaxLine extends ShopifyObject {
	
	private Double price;
	private Double rate;
	private String title;
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the rate
	 */
	public Double getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		return null;
	}
	public void fromJSON(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
	}
	

}
