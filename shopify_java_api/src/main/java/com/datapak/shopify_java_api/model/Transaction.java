package com.datapak.shopify_java_api.model;


import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.datapak.shopify_java_api.interfaces.JSONInterface;


/** 
 * Represents a Shopify transaction
 * Transactions are created for every order that results in an exchange of money .
 * @author jbrown
 * 
 * 
 * **/
public class Transaction implements JSONInterface {
	
	private String amount;
	
	private String authorization;
	
	private Date created_at;
	private String device_id;
	private String gateway;
	private String source_name;
	private PaymentDetail[] payment_details;
	private Long id;
	private String kind;
	private Long order_id;
	private String receipt;
	private String error_code;
	private String status;
	private Boolean test;
	private String user_id;
	private String currency;
	
	
	/** Gets the transaction amount. 
	 * 
	 * @return A string representing the transaction amount.
	 * */
	public String getAmount() {
		return amount;
	}
	
	/** Sets the transaction amount.
	 * 
	 * @param amount A string containing the transaction amount.
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	/** Gets the authorization code associated with the transaction. 
	 * 
	 * @return A string containing the authorization code for the transaction.
	 */
	public String getAuthorization() {
		return authorization;
	}
	
	/** Sets the authorization code associated with the transaction.
	 * 
	 * @param authorization A string containing the authorization code for the transaction.
	 */
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	
	/** Gets the date and time when the transaction was created. 
	 * 
	 * @return A date object containing the time and date transaction was created.
	 */
	public Date getCreated_at() {
		return created_at;
	}
	
	/** Sets the date and time when the transaction was created.
	 * 
	 * @param created_at A date object containing the time and date transaction was created.
	 */
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	/** Gets the unique identifier for the device. 
	 * 
	 * @return A string containing unique identifier for the device.
	 */
	public String getDevice_id() {
		return device_id;
	}
	

	/** Sets the the unique identifier for the device.
	 * 
	 * @param device_id  A string containing unique identifier for the device.
	 */
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	
	/** Gets the gateway the transaction was issue through. 
	 * 
	 * @return The gateway the transaction was issued through
	 *
	 */
	public String getGateway() {
		return gateway;
	}
	
	
	/** Sets the gateway the transaction was issue through. 
	 * 
	 * @param gateway  The name of the gateway the transaction was issued through
	 * 
	 */
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	
	
	/** Gets the origin of the transaction. 
	 * 
	 * @return A string representing the origin of the transaction.
	 * 
	 */
	public String getSource_name() {
		return source_name;
	}
	
	
	/** Sets the origin of the transaction. 
	 * 
	 * @param source_name A string representing the origin of the transaction.
	 * 
	 */
	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
	
	
	/** Gets information about the credit card used for this transaction. 
	 * 
	 * @return Payment Details array containing information about the credit card used for this transaction.
	 * @see PaymentDetail
	 */
	public PaymentDetail[] getPayment_details() {
		return payment_details;
	}
	
	/** Sets information about the credit card used for this transaction. 
	 * 
	 * @param paymentDetails array containing information about the credit card used for this transaction.
	 * @see PaymentDetail
	 */
	public void setPayment_details(PaymentDetail[] paymentDetails) {
		this.payment_details = paymentDetails;
	}
	
	
	/** Gets the unique numeric identifier for the transaction. 
	 * 
	 * @return A Long object containing  the unique numeric identifier for the transaction.
	 *
	 */
	public Long getId() {
		return id;
	}
	
	
	/** Gets the unique numeric identifier for the transaction. 
	 * 
	 * @param id A Long object containing  the unique numeric identifier for the transaction.
	 *
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/** Gets the kind of transaction. 
	 * 
	 * @return A String object containing the kind of transaction.
	 * 
	 */
	public String getKind() {
		return kind;
	}
	
	
	/** Sets the kind of transaction. 
	 * 
	 * @param kind A String object containing the kind of transaction.
	 * 
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	/** Gets the unique numeric identifer for the order
	 * 
	 * @return A Long object containing the unique numeric identifier for the transaction.
 	 */
	public Long getOrder_id() {
		return order_id;
	}
	
	/** Sets the unique numeric identifer for the order
	 * 
	 * @param order_id A Long object containing the unique numeric identifier for the transaction.
	 */
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	
	
	/** Get transaction receipt attached to the transaction by the gateway. The value of this field will vary depending
	 * on which gateway the shop is using.
	 * @return A string object containing the attached transaction receipt.
	 */
	public String getReceipt() {
		return receipt;
	}
	
	/** Set transaction receipt attached to the transaction by the gateway. The value of this field will vary depending
	 * on which gateway the shop is using.
	 * @param receipt A string object containing the attached transaction receipt.
	 */
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	
	
	/** Get standardized error code, independent of the payment provider.
	 * @return A ErrorCode object containing the standardized error code;
	 */
	public String getError_code() {
		return error_code;
	}
	
	
	/** Set standardized error code, independent of the payment provider.
	 * @param error_code A ErrorCode object containing the standardized error code;
	 */
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	
	/** Get status of the transaction 
	 * 
	 * @return A String Object containing the status of the transaction.
	 * 
	 */
	public String getStatus() {
		return status;
	}
	
	
	/** Set status of the transaction 
	 * 
	 * @param status A String Object containing the status of the transaction.
	 * 
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	/** Gets the option to use the transaction for testing purpose. 
	 * 
	 * @return A Boolean value containing the option to use the transaction for testing purpose.
	 */
	public Boolean getTest() {
		return test;
	}
	
	/** Sets the option to use the transaction for testing purpose. 
	 * 
	 * @param test A Boolean value containing the option to use the transaction for testing purpose.
	 */
	public void setTest(Boolean test) {
		this.test = test;
	}
	
	/** Gets the unique identifier for the user. 
	 * 
	 * @return A String value containing the unique identifier for the user.
	 */
	public String getUser_id() {
		return user_id;
	}
	
	/** Sets the unique identifier for the user. 
	 * 
	 * @param user_id A String value containing the unique identifier for the user.
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	/** Gets the three letter code for the currency used for the payment. 
	 * 
	 * @return A String value containing the three letter code for the currency used for the payment.
	 */
	public String getCurrency() {
		return currency;
	}
	
	/** Sets the three letter code for the currency used for the payment. 
	 * 
	 * @param currency A String value containing the three letter code for the currency used for the payment.
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", authorization=" + authorization + ", created_at=" + created_at
				+ ", device_id=" + device_id + ", gateway=" + gateway + ", source_name=" + source_name
				+ ", payment_details=" + payment_details + ", id=" + id + ", kind=" + kind + ", order_id=" + order_id
				+ ", receipt=" + receipt + ", error_code=" + error_code + ", status=" + status + ", test=" + test
				+ ", user_id=" + user_id + ", currency=" + currency + "]";
	}
	
	
	
	public void setParent_id(String string) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		
		JSONObject obj = new JSONObject();
		
		obj.put("amount", amount);
		obj.put("authorization", authorization);
		obj.put("created_at", created_at);
		obj.put("device_id", device_id);
		obj.put("gateway", gateway);
		obj.put("source_name", source_name);
		obj.put("payment_details", ArrayParser.toJSON(payment_details));
		obj.put("id", id);
		obj.put("kind", kind);
		obj.put("order_id", order_id);
		obj.put("receipt", receipt);
		obj.put("error_code", error_code);
		obj.put("status", status);
		obj.put("test", test);
		obj.put("user_id", user_id);
		obj.put("currency",currency);
		
		return obj;
	}

	@Override
	public void fromJSON(JSONObject json) {

		if (json==null) {return; }
		
		this.setAmount((String) json.get("amount"));
		this.setAuthorization((String)json.get("authorization"));
		this.setCreated_at(ISODate((String) json.get("created_at")));
		this.setDevice_id((String) json.get("device_id"));

		
		this.setGateway((String) json.get("gateway"));
		this.setSource_name("source_web");
		this.setId((Long) json.get("id"));
		

		
		this.setKind((String) json.get("kind"));
		this.setOrder_id((Long) json.get("order_id"));
		
		//FIXME: Returns JSONObject need to parse this
		this.setReceipt((String) json.get("recieipt"));
		this.setPayment_details(ArrayParser.toPaymentDetails((JSONArray) json.get("payment_details")));

		
		//FIXME: Returning null here
		
		//this.setError_code(convertErrorCodefromJSON(json.get("error_code").toString()));
		
		
		this.setStatus((String) json.get("status"));
		this.setTest((Boolean) json.get("test"));
		this.setUser_id((String) json.get("user_id"));
		this.setCurrency((String) json.get("currency"));
		
		
	}
	
	
	
	
	

}
