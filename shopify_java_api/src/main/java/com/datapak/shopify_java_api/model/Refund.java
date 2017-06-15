package com.datapak.shopify_java_api.model;

import java.util.Arrays;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class Refund extends ShopifyObject {
	
	private Date created_at;
	private Date processed_at;
	private Long id;
	private Refund_Line_Item[] refund_line_items;
	private Boolean restock;
	private Transaction[] transactions;
	private Long user_id;
	private String note;
	private Shipping shipping;
	private Logger logger = LogManager.getLogger(Refund.class);
	
	
	public Boolean getRestock() {
		return restock;
	}
	public Shipping getShipping() {
		return shipping;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getProcessed_at() {
		return processed_at;
	}
	public void setProcessed_at(Date processed_at) {
		this.processed_at = processed_at;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long long1) {
		this.id = long1;
	}
	public Refund_Line_Item[] getRefund_line_items() {
		return refund_line_items;
	}
	public void setRefund_line_items(Refund_Line_Item[] refund_Line_Items2) {
		this.refund_line_items = refund_Line_Items2;
	}
	public Boolean isRestock() {
		return restock;
	}
	public void setRestock(Boolean restock) {
		this.restock = restock;
	}
	public Transaction[] getTransactions() {
		return transactions;
	}

	public void setTransactions(Transaction[] transactions2) {
		this.transactions = transactions2;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long long1) {
		this.user_id = long1;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
	
	@Override
	public String toString() {
		return "Refund [created_at=" + created_at + ", processed_at=" + processed_at + ", id=" + id
				+ ", refund_line_items=" + Arrays.toString(refund_line_items) + ", restock=" + restock
				+ ", transactions=" + Arrays.toString(transactions) + ", user_id=" + user_id + ", note=" + note
				+ ", shipping=" + shipping + "]";
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {

		JSONObject obj = new JSONObject();
		
		obj.put("created_at", created_at);
		obj.put("processed_at", processed_at);
		obj.put("id", id);
		obj.put("note", note);
		obj.put("refund_line_items", ArrayParser.toJSON(refund_line_items));
		obj.put("restock", restock);
		obj.put("transactions",  ArrayParser.toJSON(transactions));
		obj.put("user_id", user_id);
		
		return obj;
	}
	@Override
	public void fromJSON(JSONObject json) {
		
		logger.entry(json);
		if (json ==null) {return; }
	
		this.setCreated_at(ISODate((String) json.get("created_at")));
		this.setProcessed_at(ISODate((String) json.get("processed_at")));
		//refund.setShipping(convertShippingfromJSON((JSONObject) json.get("shipping")));
		
		Shipping shipping = new Shipping();
		shipping.fromJSON((JSONObject) json.get("shipping"));
		
		this.setShipping(shipping);
		this.setId((Long) json.get("id"));
		this.setNote((String) json.get("note"));
		
		this.setRefund_line_items(ArrayParser.toRefundLineItems((JSONArray) json.get("refund_line_items")));
		this.setRestock((Boolean) json.get("restock"));
		this.setTransactions(ArrayParser.toTransactions((JSONArray) json.get("transactions")));
	
		this.setUser_id((Long) json.get("user_id"));
		
		logger.traceExit(this);
	}


}
