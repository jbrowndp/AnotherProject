package com.datapak.shopify_java_api.model;

import org.json.simple.JSONObject;


/** Applicable discount codes that can be applied to the order
 * 
 * @author jbrown
 * @version 1.0
 */
public class DiscountCode extends ShopifyObject {
	
	
	private String amount;
	private String code;
	private String type;
	
	/** Gets the amount of the discount 
	 * 
	 * @return A String containing the amount of the discount
	 */
	public String getAmount() {
		return amount;
	}
	
	/** Sets the amount of the discount
	 * 
	 * @param amount A String containing the amount of the discount
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	/** Gets the discount code
	 * 
	 * @return A String containing the discount code
	 */
	public String getCode() {
		return code;
	}
	
	/** Sets the discount code
	 * 
	 * @param code A String containing the discount code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/** Gets the type of discount
	 * 
	 * @return A String containing the type of discount
	 */
	public String getType() {
		return type;
	}
	
	
	/** Sets the type of discount 
	 * 
	 * @param type A String containing the type of discount
	 */
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DiscountCode [amount=" + amount + ", code=" + code + ", type=" + type + "]";
	}
	
	
	
	/** 
	 * @see ShopifyObject
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		
		JSONObject obj = new JSONObject();
		
		obj.put("amount", amount);
		obj.put("code", code);
		obj.put("type", type);
		
		return obj;
		
		
	}

	@Override
	public void fromJSON(JSONObject json) {
		
		this.setAmount((String) json.get("amount"));
		this.setCode((String) json.get("code"));
		this.setType((String) json.get("type"));
		
	}
	

}
