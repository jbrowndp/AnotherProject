package com.datapak.shopify_java_api.model;

import org.json.simple.JSONObject;


public class Refund_Line_Item extends ShopifyObject{
	
	private Long id;
	private LineItem line_item;
	private Long line_item_id;
	private Integer quantity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long long1) {
		this.id = long1;
	}
	
	public LineItem getLine_item() {
		return line_item;
	}
	public void setLine_item(LineItem lineItem) {
		this.line_item = lineItem;
	}
	public Long getLine_item_id() {
		return line_item_id;
	}
	public void setLine_item_id(Long line_item_id) {
		this.line_item_id = line_item_id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Refund_Line_Item [id=" + id + ", line_item=" + line_item + ", line_item_id=" + line_item_id
				+ ", quantity=" + quantity + "]";
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {

			JSONObject obj = new JSONObject();
			
			obj.put("id", id);
			obj.put("line_item", line_item);
			obj.put("line_item_id", line_item_id);
			obj.put("quantity", quantity);
				
			return obj;
	}
	
	@Override
	public void fromJSON(JSONObject json) {
		
			this.setId((Long) json.get("id"));
			
			// FIXME: This is sometimes null
			//line_item.fromJSON((JSONObject) json.get("line_item"));
			
			
			this.setLine_item_id((Long) json.get("line_item_id"));
			this.setQuantity((Integer) json.get("qty"));
		
	}
	

}
