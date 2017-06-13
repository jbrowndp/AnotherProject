package com.datapak.shopify_java_api.model;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/** 
 * Represents a Shopify Order
 * An order is a customer's completed request to purchase one or more products from a shop. An order
 * is created when a customer completes the checkout process, during which time s/he provides an
 * email address or phone number, billing address and payment information.
 * 
 * @author jbrown
 * @version 1.0
 *
 */
public class Order extends ShopifyObject {
	
	private BillingAddress billing_address;
	
	private String browser_ip;
	
	private Boolean buyer_accepts_marketing;
	
	private String cancel_reason;
	
	private Date cancelled_at;
	
	private String cart_token;
	
	private ClientDetails client_details;
	
	private Date closed_at;
	
	private Date created_at;
	
	private String currency; 
	
	private Customer customer;
	
	private DiscountCode[] discountCodes;
	
	private String email;
	
	private String financial_status;
	
	private FulFillment[] fulfillments;
	
	private String fulfillment_status;
	
	private String tags;
	
	private Long id;
	
	private String landing_site;
	
	private LineItem[] line_items;
	
	private Long location_id;
	
	private String name;
	
	private String note;
	
	private Map<String,String> note_attributes;
	
	private Long number;
	
	private Long order_number;
	
	private PaymentDetail payment_details;
	
	private String[] payment_gateway_names;
	
	private Date processed_at;
	
	private String processing_method;
	
	private String referring_site;
	
	private Refund[] refunds;
	
	private ShippingAddress[] shippingAddress;
	
	private ShippingLine[] shippingLines;
	
	private String source_name;
	
	private Double subtotal_price;
	
	private TaxLine[] tax_lines;
	
	private Boolean taxes_included;
	
	private String token;
	
	private String total_discounts;
	
	private String total_line_items_price;
	
	private String total_price;
	
	private String total_tax;
	
	private Long total_weight;
	
	private Date updated_at;
	
	private Long user_id;
	
	private String order_status_url;

	private Logger logger = LogManager.getLogger(Order.class);
	
	/** Gets the mailing address associated with the payment method. This address
	 * is an optional field that will not be available on orders that do not require one.
	 * 
	 * @return <code>BillingAddress[]</code> containing the mailing address association with the payment method.
	 */
	public BillingAddress getBilling_address() {
		return billing_address;
	}

	/** Sets the mailing address associated with the payment method. This address
	 * is an optional field that will not be available on orders that do not require one.
	 * 
	 * @param billing_address BillingAddress[] containing the mailing address association with the payment method.
	 */
	public void setBilling_address(BillingAddress billing_address) {
		this.billing_address = billing_address;
	}

	/** Gets the IP address of the browser used by the customer when placing the order. 
	 * 
	 * @return A String object containing the IP address of the browser used by the customer when placnig the order.
	 */
	public String getBrowser_ip() {
		return browser_ip;
	}

	
	/** Sets the IP address of the browser used by the customer when placing the order. 
	 * 
	 * @param browser_ip A String object containing the IP address of the browser used by the customer when placnig the order.
	 */
	public void setBrowser_ip(String browser_ip) {
		this.browser_ip = browser_ip;
	}

	/** Indicates whether of not the person who placed the order woould like to receive email udates from the shop.
	 * 
	 * @return A Boolean object containing value of true of false
	 */
	public Boolean isBuyer_accepts_marketing() {
		return buyer_accepts_marketing;
	}

	/** Sets whether of not the person who placed the order woould like to receive email udates from the shop.
	 * 
	 * @param buyer_accepts_marketing  Valid values are boolean  "true" or "false"
	 */
	public void setBuyer_accepts_marketing(Boolean buyer_accepts_marketing) {
		this.buyer_accepts_marketing = buyer_accepts_marketing;
	}

	
	/** Gets the reason why the order was cancelled. If the order was not cancelled, the value will be on of the following:
	 * <b>customer:</b> The customer changed cancelled the order
	 * <b>fraud:</b> The order was fradulent
	 * <b>inventory:</b> Items in the order were not in inventory
	 * <b>other:</b> The order was cancelled for a reason not in the list above
	 * @return A String containing the reason why the order was cancelled
	 */
	public String getCancel_reason() {
		return cancel_reason;
	}

	/** Sets the reason why the order was cancelled. If the order was not cancelled, the value will be on of the following:
	 * <b>customer:</b> The customer changed cancelled the order
	 * <b>fraud:</b> The order was fradulent
	 * <b>inventory:</b> Items in the order were not in inventory
	 * <b>other:</b> The order was cancelled for a reason not in the list above
	 * @param cancel_reason  The reason why the order was cancelled
	 */
	public void setCancel_reason(String cancel_reason) {
		this.cancel_reason = cancel_reason;
	}

	
	/** Gets the date and time when the order was cancelled 
	 * 
	 * @return A Date object content 
	 */
	public Date getCancelled_at() {
		return cancelled_at;
	}

	
	/** Sets the date and time when the order was cancelled
	 * 
	 * @param cancelled_at A Date object containing the date and time when the order was cancelled
	 */
	public void setCancelled_at(Date cancelled_at) {
		this.cancelled_at = cancelled_at;
	}

	/** Gets the unique identifier for a particular cart that is attached to a particular order.
	 * 
	 * @return A String object containing the unique identifier for a particular cart that is attached to a particular order.
	 */
	public String getCart_token() {
		return cart_token;
	}

	
	/** Sets the unique identifier for a particular cart that is attached to a particular order.
	 * 
	 * @param cart_token The unique identifier for a particular cart that is attached to a particular order.
	 */
	public void setCart_token(String cart_token) {
		this.cart_token = cart_token;
	}

	
	/** Gets the client details
	 * 
	 * @return ClientDetails[] object containing information about the client
	 */
	public ClientDetails getClient_details() {
		return client_details;
	}
	
	
	/** Gets the client details
	 * 
	 * @param clientDetails object containing information about the client
	 */
	public void setClient_details(ClientDetails clientDetails) {
		this.client_details = clientDetails;
	}
	
	
	/** Gets the date and time when the order was closed. If the order was not closed, this value is null
	 * 
	 * @return A Date object containing the date and time when the order was closed.
	 */
	public Date getClosed_at() {
		return closed_at;
	}

	
	/** Sets the date and time when the order was closed. If the order was not closed, this value is null
	 * 
	 * @param  closed_at The date and time when the order was closed.
	 */
	public void setClosed_at(Date closed_at) {
		this.closed_at = closed_at;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public DiscountCode[] getDiscountCodes() {
		return discountCodes;
	}

	public void setDiscountCodes(DiscountCode[] discountCodes) {
		this.discountCodes = discountCodes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFinancial_status() {
		return financial_status;
	}

	public void setFinancial_status(String financial_status) {
		this.financial_status = financial_status;
	}

	public FulFillment[] getFulfillments() {
		return fulfillments;
	}

	public void setFulfillments(FulFillment[] fulfillments) {
		this.fulfillments = fulfillments;
	}

	public String getFulfillment_status() {
		return fulfillment_status;
	}

	public void setFulfillment_status(String fulfillment_status) {
		this.fulfillment_status = fulfillment_status;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLanding_site() {
		return landing_site;
	}

	public void setLanding_site(String landing_site) {
		this.landing_site = landing_site;
	}

	public LineItem[] getLine_items() {
		return line_items;
	}

	public void setLine_items(LineItem[] line_items) {
		this.line_items = line_items;
	}

	public Long getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Map<String, String> getNote_attributes() {
		return note_attributes;
	}

	public void setNote_attributes(Map<String, String> note_attributes) {
		this.note_attributes = note_attributes;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Long getOrder_number() {
		return order_number;
	}

	public void setOrder_number(Long order_number) {
		this.order_number = order_number;
	}

	public String[] getPayment_gateway_names() {
		return payment_gateway_names;
	}

	public void setPayment_gateway_names(String[] payment_gateway_names) {
		this.payment_gateway_names = payment_gateway_names;
	}

	public Date getProcessed_at() {
		return processed_at;
	}

	public void setProcessed_at(Date processed_at) {
		this.processed_at = processed_at;
	}

	public String getProcessing_method() {
		return processing_method;
	}

	public void setProcessing_method(String processing_method) {
		this.processing_method = processing_method;
	}

	public String getReferring_site() {
		return referring_site;
	}

	public void setReferring_site(String referring_site) {
		this.referring_site = referring_site;
	}

	public Refund[] getRefunds() {
		return refunds;
	}

	public void setRefunds(Refund[] refunds) {
		this.refunds = refunds;
	}

	public ShippingAddress[] getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress[] shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public ShippingLine[] getShippingLines() {
		return shippingLines;
	}

	public void setShippingLines(ShippingLine[] shippingLines) {
		this.shippingLines = shippingLines;
	}

	public String getSource_name() {
		return source_name;
	}

	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}

	public Double getSubtotal_price() {
		return subtotal_price;
	}

	public void setSubtotal_price(Double subtotal_price) {
		this.subtotal_price = subtotal_price;
	}

	public TaxLine[] getTax_lines() {
		return tax_lines;
	}

	public void setTax_lines(TaxLine[] tax_lines) {
		this.tax_lines = tax_lines;
	}

	public Boolean getTaxes_included() {
		return taxes_included;
	}

	public void setTaxes_included(Boolean taxes_included) {
		this.taxes_included = taxes_included;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTotal_discounts() {
		return total_discounts;
	}

	public void setTotal_discounts(String total_discounts) {
		this.total_discounts = total_discounts;
	}

	public String getTotal_line_items_price() {
		return total_line_items_price;
	}

	public void setTotal_line_items_price(String total_line_items_price) {
		this.total_line_items_price = total_line_items_price;
	}

	public String getTotal_price() {
		return total_price;
	}

	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}

	public String getTotal_tax() {
		return total_tax;
	}

	public void setTotal_tax(String total_tax) {
		this.total_tax = total_tax;
	}

	public Long getTotal_weight() {
		return total_weight;
	}

	public void setTotal_weight(Long total_weight) {
		this.total_weight = total_weight;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getOrder_status_url() {
		return order_status_url;
	}

	public void setOrder_status_url(String order_status_url) {
		this.order_status_url = order_status_url;
	}

	public PaymentDetail getPayment_details() {
		return payment_details;
	}

	public void setPayment_details(PaymentDetail payment_details) {
		this.payment_details = payment_details;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		
		logger.entry(this);
		JSONObject obj = new JSONObject();
		
		obj.put("billing_address", billing_address.toJSON());
		obj.put("browser_ip", browser_ip);
		obj.put("buyer_accepts_marketing", buyer_accepts_marketing);
		obj.put("cancel_reason", cancel_reason);
		obj.put("cancelled_at", cancelled_at);
		obj.put("cart_token", cart_token);
		obj.put("client_details", client_details.toJSON());
		obj.put("closed_at", closed_at);
		obj.put("created_at", created_at);
		obj.put("currency", currency);
		obj.put("customer", customer.toJSON());
		obj.put("discount_codes", ArrayParser.toJSON(discountCodes));
		obj.put("email", email);
		obj.put("financial_status", financial_status);
		obj.put("fullfillments", ArrayParser.toJSON(fulfillments));
		obj.put("fulfillment_status", fulfillment_status);
		obj.put("tags", tags);
		obj.put("id", id);
		obj.put("landing_site", landing_site);
		obj.put("line_items", ArrayParser.toJSON(line_items));
		obj.put("location_id", location_id);
		obj.put("name", name);
		obj.put("note", note);
		
		//FIXME: How to parse map?
		//obj.put("note_attributes", ArrayParser.toJSON(note_attributes));
		obj.put("number", number);
		obj.put("order_number", order_number);
		
		// FIXME: This comes from string
		//obj.put("payment_gateway_names", ArrayParser.toJSON(payment_gateway_names));
		obj.put("processed_at", processed_at);
		obj.put("processing_method", processing_method);
		obj.put("referring_site", referring_site);
		obj.put("refunds", ArrayParser.toJSON(refunds));
		obj.put("shipping_address", ArrayParser.toJSON(shippingAddress));
		obj.put("shipping_lines",ArrayParser.toJSON(shippingLines));
		obj.put("source_name", source_name);
		obj.put("subtotal_price", subtotal_price);
		obj.put("tax_lines", ArrayParser.toJSON(tax_lines));
		obj.put("taxes_included", taxes_included);
		obj.put("token", token);
		obj.put("total_discounts", total_discounts);
		obj.put("total_line_items_price", total_line_items_price);
		obj.put("total_price", total_price);
		obj.put("total_tax", total_tax);
		obj.put("total_weight", total_weight);
		obj.put("updated_at", updated_at);
		obj.put("user_id", user_id);
		obj.put("order_status_url", order_status_url);
		
			logger.traceExit(obj);
		return obj;
	}

	@Override
	public void fromJSON(JSONObject json) {
		
		
		
		logger.entry(json);
		if (json ==null) {  
			
		
		return; }
		
		// FIXME: Will not be available if order does not require one
		
		billing_address = new BillingAddress();
		billing_address.fromJSON((JSONObject) json.get("billing_address"));
		setBilling_address(billing_address);


		setBrowser_ip((String) json.get("browser_ip"));
		setBuyer_accepts_marketing((Boolean) json.get("buyer_accepts_marketing"));
		setCancel_reason((String) json.get("cancel_reason"));
		setCancelled_at(ISODate((String) json.get("cancelled_at")));
		setCart_token((String) json.get("cart_token"));
		
		
		
		client_details = new ClientDetails();
		client_details.fromJSON((JSONObject) json.get("client_details"));
		
		setClient_details(client_details);
		
		setClosed_at(ISODate((String) json.get("closed_at")));
		setCreated_at(ISODate((String) json.get("created_at")));
		setCurrency((String) json.get("currency"));
		
		customer = new Customer();
		customer.fromJSON((JSONObject) json.get("customer"));
		setCustomer(customer);
		
		setDiscountCodes(ArrayParser.toDiscountCodes((JSONArray) json.get("discount_codes")));
		setEmail((String) json.get("email"));
		setFinancial_status((String) json.get("financial_status"));
		
		
		
		setFulfillments(ArrayParser.toFulfillments((JSONArray) json.get("fulfillments")));
		setFulfillment_status((String) json.get("fullfillment_status"));
		setTags((String) json.get("tags"));
		setId((Long) json.get("id"));
		setLanding_site((String) json.get("landing_site"));
		setLine_items((LineItem[]) ArrayParser.toLineItems((JSONArray) json.get("line_items")));
		setLocation_id((Long) json.get("location_id"));
		setName((String) json.get("name"));
		setNote((String) json.get("note"));
		
		// FIXME: How to cast map from JSON 
		// setNote_attributes((Map<String, String>) json.get("note_attributes"));
		setNumber((Long) json.get("number"));
		setOrder_number((Long) json.get("order_number"));
		
		// FIXME: This comes from string
		//setPayment_gateway_names(ArrayParser.toGatewayName(json.get("payment_gateway_names")));
		setProcessed_at(ISODate((String) json.get("processed_at")));
		setProcessing_method((String) json.get("processing_method"));
		setReferring_site((String) json.get("referring_site"));
		setRefunds(ArrayParser.toRefund((JSONArray) json.get("refunds")));
		setShippingAddress(ArrayParser.toShippingAddress((JSONArray) json.get("shipping_address")));
		setShippingLines(ArrayParser.toShippingLines( (JSONArray) json.get("shipping_lines")));
		setSource_name((String) json.get("source_name"));
		
		setSubtotal_price(Double.valueOf((String) json.get("subtotal_price")));
		
		//FIXME:  What's happening here?  Returning null 
		//setSubtotal_price(Double.valueOf((String) json.get("subtotal_price")));
		setTax_lines(ArrayParser.toTaxLines( (JSONArray) json.get("tax_lines")));
		setTaxes_included((Boolean) json.get("taxes_included"));
		setToken((String) json.get("token"));
		setTotal_discounts((String) json.get("total_discounts"));
		setTotal_line_items_price((String) json.get("total_line_items_price"));
		setTotal_price((String) json.get("total_price"));
		setTotal_tax((String) json.get("total_tax"));
		setTotal_weight((Long) json.get("total_weight"));
		
		//FIXME: Something's wrong here. Getting more than one date.
		// setUpdated_at(ISODate((String) json.get("total_tax")));
		setUser_id((Long) json.get("user_id"));
		setOrder_status_url((String) json.get("order_status_url"));
		
		
		logger.traceExit(this);
				
	}

	@Override
	public String toString() {
		return "Order [billing_address=" + billing_address + ", browser_ip=" + browser_ip
				+ ", buyer_accepts_marketing=" + buyer_accepts_marketing + ", cancel_reason=" + cancel_reason
				+ ", cancelled_at=" + cancelled_at + ", cart_token=" + cart_token + ", client_details="
				+ client_details + ", closed_at=" + closed_at + ", created_at=" + created_at
				+ ", currency=" + currency + ", customer=" + customer + ", discountCodes="
				+ Arrays.toString(discountCodes) + ", email=" + email + ", financial_status=" + financial_status
				+ ", fulfillments=" + Arrays.toString(fulfillments) + ", fulfillment_status=" + fulfillment_status
				+ ", tags=" + tags + ", id=" + id + ", landing_site=" + landing_site + ", line_items="
				+ Arrays.toString(line_items) + ", location_id=" + location_id + ", name=" + name + ", note=" + note
				+ ", note_attributes=" + note_attributes + ", number=" + number + ", order_number=" + order_number
				+ ", payment_details=" + payment_details + ", payment_gateway_names="
				+ Arrays.toString(payment_gateway_names) + ", processed_at=" + processed_at + ", processing_method="
				+ processing_method + ", referring_site=" + referring_site + ", refunds=" + Arrays.toString(refunds)
				+ ", shippingAddress=" + Arrays.toString(shippingAddress) + ", shippingLines="
				+ Arrays.toString(shippingLines) + ", source_name=" + source_name + ", subtotal_price=" + subtotal_price
				+ ", tax_lines=" + Arrays.toString(tax_lines) + ", taxes_included=" + taxes_included + ", token="
				+ token + ", total_discounts=" + total_discounts + ", total_line_items_price=" + total_line_items_price
				+ ", total_price=" + total_price + ", total_tax=" + total_tax + ", total_weight=" + total_weight
				+ ", updated_at=" + updated_at + ", user_id=" + user_id + ", order_status_url=" + order_status_url
				+ "]";
	}
		
		
		
	

}
