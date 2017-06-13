package com.datapak.shopify_java_api.model;

import org.json.simple.JSONObject;

public class ShippingLine extends ShopifyObject {
	
	private String code;
	private Double price;
	private String source;
	private String title;
	private TaxLine[] tax_lines;
	private String carrier_identifier;
	private String requested_fullfillment_service_id;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
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
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
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
	/**
	 * @return the tax_lines
	 */
	public TaxLine[] getTax_lines() {
		return tax_lines;
	}
	/**
	 * @param tax_lines the tax_lines to set
	 */
	public void setTax_lines(TaxLine[] tax_lines) {
		this.tax_lines = tax_lines;
	}
	/**
	 * @return the carrier_identifier
	 */
	public String getCarrier_identifier() {
		return carrier_identifier;
	}
	/**
	 * @param carrier_identifier the carrier_identifier to set
	 */
	public void setCarrier_identifier(String carrier_identifier) {
		this.carrier_identifier = carrier_identifier;
	}
	/**
	 * @return the requested_fullfillment_service_id
	 */
	public String getRequested_fullfillment_service_id() {
		return requested_fullfillment_service_id;
	}
	/**
	 * @param requested_fullfillment_service_id the requested_fullfillment_service_id to set
	 */
	public void setRequested_fullfillment_service_id(String requested_fullfillment_service_id) {
		this.requested_fullfillment_service_id = requested_fullfillment_service_id;
	}
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		return null;
	}
	public void fromJSON(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
