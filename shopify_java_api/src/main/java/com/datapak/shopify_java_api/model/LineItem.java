package com.datapak.shopify_java_api.model;

import java.util.Arrays;

import org.json.simple.JSONObject;

/** Contains information about an item in the order
 * 
 * @author jbrown
 * @version 1.0
 *
 */
public class LineItem extends ShopifyObject {
	
	private Long fulfillable_quantity;
	private String fulfillment_service;
	private String fulfillment_status;
	private Long grams;
	private Long id;
	private String price;
	private Long product_id;
	private Long quantity;
	private Boolean requires_shipping;
	private String sku;
	private String title;
	private Long variant_id;
	private String variant_title;
	private String vendor;
	private String name;
	private Boolean gift_card;
	private Properties[] properties;
	private Boolean taxable;
	private TaxLine[] tax_lines;
	private String total_discount;
	
	
	/** Gets the amount available to fulfill
	 * 
	 * @return A Long object containing the amount available to fulfill
	 */
	public Long getFulfillable_quantity() {
		return fulfillable_quantity;
	}
	
	/** Sets the amount available to fulfill
	 * 
	 * @param fulfillable_quantity The amount avaiable to fulfill
	 */
	public void setFulfillable_quantity(Long fulfillable_quantity) {
		this.fulfillable_quantity = fulfillable_quantity;
	}
	
	/** Gets the service provider who is doing the fulfillment. Valid values are either "manual" or 
	 * the name of the provider" eg: "amazon, "shipwire", etc.
	 * 
	 * @return A String object containing the service provider who is doing the fulfillment.
	 */
	public String getFulfillment_service() {
		return fulfillment_service;
	}
	
	
	/** Gets the service provider who is doing the fulfillment. Valid values are either "manual" or 
	 * the name of the provider" eg: "amazon, "shipwire", etc.
	 * 
	 * @param fulfillment_service The service provider who is doing the fulfillment.
	 */
	public void setFulfillment_service(String fulfillment_service) {
		this.fulfillment_service = fulfillment_service;
	}
	
	/** Gets how far along an order is in terms line items fulfilled. Valid values are 
	 * fulfilled, null, or partial
	 * @return A String object containing how far along an order is in terms line items are fullfulled.
	 */
	public String getFulfillment_status() {
		return fulfillment_status;
	}
	
	/** Sets how far along an order is in terms line items fulfilled. Valid values are 
	 * fulfilled, null, or partial
	 * @param fulfillment_status A String object containing how far along an order is in terms line items are fullfulled.
	 */
	public void setFulfillment_status(String fulfillment_status) {
		this.fulfillment_status = fulfillment_status;
	}
	
	/** Gets the weight of the item in grams 
	 * 
	 * @return A Long object containing the weight of the item in grams.
	 */
	public Long getGrams() {
		return grams;
	}
	
	/** Sets the weight of the item in grams
	 * 
	 * @param grams The weight of the item in grams.
	 */
	public void setGrams(Long grams) {
		this.grams = grams;
	}
	
	/** Gets the id of the line item
	 * 
	 * @return A Long object containing the id of the line item
	 */
	public Long getId() {
		return id;
	}
	
	/** Sets the id of the line item
	 * 
	 * @param id The id of the line item
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/** Gets the price of the item before discounts have been applied
	 * 
	 * @return A String object containing the price of the item beofre discounts have been applied.
	 */
	public String getPrice() {
		return price;
	}
	
	/** Sets the price of the item before discounts have been applied
	 * 
	 * @param price The price of the item before discounts have been applied.
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	
	/** Gets the unique numeric identifier for the product in the fulfillment. Can be null if the
	 * original product associated with the order is deleted at a later date 
	 * @return A Long object containing the unique numeric identifier for the product in the fulfillment. 
	 */
	public Long getProduct_id() {
		return product_id;
	}
	
	/** Gets the unique numeric identifier for the product in the fulfillment. Can be null if the
	 * original product associated with the order is deleted at a later date 
	 * @param product_id The unique numeric identifier for the product in the fulfillment. 
	 */
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	
	/** Gets the number of products that were purchased 
	 * 
	 * @return A Long object containing the number of products that were purchased
	 */
	public Long getQuantity() {
		return quantity;
	}
	
	/** Sets the number of products that were purchased
	 * 
	 * @param quantity The number of products that were purchased/
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
	/** Gets whether or not the fulfillment requires shipping. Values are: true or false.
	 * 
	 * @return A Boolean object containing whether or not the fulfillment requires shipping.
	 */
	public Boolean getRequires_shipping() {
		return requires_shipping;
	}
	
	/** Sets whether or not the fulfillment requires shipping. Values are: true or false.
	 * 
	 * @param requires_shipping Whether or not the fulfillment requires shipping.
	 */
	public void setRequires_shipping(Boolean requires_shipping) {
		this.requires_shipping = requires_shipping;
	}
	
	/** Gets the unique identifier of the item in the fulfillment
	 * 
	 * @return A String containing the unique identifier of the item in the fulfillment
	 */
	public String getSku() {
		return sku;
	}
	/**
	 * Sets the unique identifier of the item in the fulfillment
	 * @param sku the unique identifier of the item in the fulfillment
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	/** Gets the title of the product
	 * 
	 * @return A String object containing the title of the product
	 */
	public String getTitle() {
		return title;
	}
	
	/** Sets the title of the product
	 * 
	 * @param title  The title of the product
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/** Gets the id of the product variant
	 * 
	 * @return A Long object containing the id of the product variant
	 */
	public Long getVariant_id() {
		return variant_id;
	}
	
	/** Sets the id of the product variant 
	 * 
	 * @param variant_id The id of the product variant
	 */
	public void setVariant_id(Long variant_id) {
		this.variant_id = variant_id;
	}
	
	/** Gets the title of the product variant
	 * 
	 * @return A String containing the title of the product variant
	 */
	public String getVariant_title() {
		return variant_title;
	}
	/**
	 * Sets the title of the product variant
	 * 
	 * @param variant_title The title of the product variant
	 */
	public void setVariant_title(String variant_title) {
		this.variant_title = variant_title;
	}
	
	/** 
	 * Get the name of the supplier of the item.
	 * 
	 * @return A String containing the name of the supplier of the item.
	 */
	public String getVendor() {
		return vendor;
	}
	
	/** 
	 * Sets the name of the supplier of the item
	 * 
	 * @param vendor The name of the supplier of the item.
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	
	/** Gets the name of the product variant 
	 * 
	 * @return A String object containing the name of the product variant
	 */
	public String getName() {
		return name;
	}
	
	
	/** Sets the name of the product variant
	 * 
	 * @param name The name of the product variant
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** Gets whether or not the line_item is a gift card. If so, the item is not taxed or considered 
	 * for shipping charges
	 * @return  A Boolean object containing whether or not the line_item is a gift card. If so, the item is not taxed or considered 
	 * for shipping charges
	 */
	public Boolean getGift_card() {
		return gift_card;
	}
	
	/** Sets whether or not the line_item is a gift card. If so, the item is not taxed or considered 
	 * for shipping charges
	 * @param gift_card   Whether or not the line_item is a gift card. If so, the item is not taxed or considered 
	 * for shipping charges
	 */
	public void setGift_card(Boolean gift_card) {
		this.gift_card = gift_card;
	}
	
	
	/** Gets an array of custom information for an item that has been added to the cart
	 * 
	 * @return Properties[] containing custom information for an item that has been added to the cart.
	 */
	public Properties[] getProperties() {
		return properties;
	}
	
	/** Sets an array of custom information for an item that has been added to the cart
	 * 
	 * @param properties Custom information for an item that has been added to the cart.
	 */
	public void setProperties(Properties[] properties) {
		this.properties = properties;
	}
	
	/** Gets whether or not the product was taxable. Valid values are: true or false
	 * 
	 * @return A Boolean object containing whether or not the product was taxable.
	 */
	public Boolean getTaxable() {
		return taxable;
	}
	
	/** Sets whether or not the product was taxable. Valid values are: true or false
	 * 
	 * @param taxable A Boolean object containing whether or not the product was taxable.
	 */
	public void setTaxable(Boolean taxable) {
		this.taxable = taxable;
	}
	
	
	/** Gets a list if tax_line objects, each of which details the taxes applicable to this line_item
	 * 
	 * @return TaxLine[] containing a list of tax_line_objects, each of which details the taxes 
	 * applicable to this line item.
	 */
	public TaxLine[] getTax_lines() {
		return tax_lines;
	}
	
	/** Sets a list if tax_line objects, each of which details the taxes applicable to this line_item
	 * 
	 * @param  tax_lines A list of tax_line_objects, each of which details the taxes applicable to this line item.
	 */
	public void setTax_lines(TaxLine[] tax_lines) {
		this.tax_lines = tax_lines;
	}
	
	/** Gets the total discount amount applied to this line item. The value is not subtracted in the line item price
	 * 
	 * @return  A String containing the total discount amount applied to this line item.
	 */
	public String getTotal_discount() {
		return total_discount;
	}
	
	
	/** Gets the total discount amount applied to this line item. The value is not subtracted in the line item price
	 * 
	 * @param total_discount The total discount amount applied to this line item.
	 */
	public void setTotal_discount(String total_discount) {
		this.total_discount = total_discount;
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		
		JSONObject obj = new JSONObject();
		obj.put("fulfillable_quantity", fulfillable_quantity);
		obj.put("fulfillment_service", fulfillment_service);
		obj.put("fulfillment_status", fulfillment_status);
		obj.put("grams", grams);
		obj.put("id", id);
		obj.put("price", price);
		obj.put("product_id", product_id);
		obj.put("quantity", quantity);
		obj.put("requires_shipping", requires_shipping);
		obj.put("sku", sku);
		obj.put("title", title);
		obj.put("variant_id", variant_id);
		obj.put("variant_title", variant_title);
		obj.put("vendor", vendor);
		obj.put("name", name);
		obj.put("gift_card", gift_card);
		obj.put("taxable", taxable);
		obj.put("tax_lines", ArrayParser.toJSON(tax_lines));
		obj.put("total_discount", total_discount);
		
		return obj;
	}
	@Override
	public void fromJSON(JSONObject json) {
		
		
		
		this.setFulfillable_quantity((Long) json.get("quantity"));
		this.setFulfillment_service((String) json.get("fulfillment_service"));
		this.setFulfillment_status((String) json.get("filfillment_status"));
		this.setGrams((Long) json.get(grams));
		this.setId((Long) json.get("id"));
		this.setPrice((String) json.get("price"));
		this.setProduct_id((Long) json.get("product_id"));
		this.setQuantity((Long) json.get("quantity"));
		this.setRequires_shipping((Boolean) json.get("requires_shipping"));
		this.setSku((String) json.get("sku"));
		this.setTitle((String) json.get("title"));
		this.setVariant_id((Long) json.get("variant_id"));
		this.setVariant_title((String) json.get("variant_title"));
		this.setVendor((String) json.get("vendor"));
		this.setName((String) json.get("name"));
		this.setGift_card((Boolean) json.get("gift_card"));
		
		//FIXME: Need to retrieve properties from text 
		this.setProperties(null);
		
		this.setTaxable((Boolean) json.get("taxable"));
		
		
		//FIXME: Need to retrieve properties from text 
		this.setTax_lines(null);
		
		
		this.setTotal_discount((String) json.get("total_discount"));
		
		
		
	}
	@Override
	public String toString() {
		return "LineItem [fulfillable_quantity=" + fulfillable_quantity + ", fulfillment_service=" + fulfillment_service
				+ ", fulfillment_status=" + fulfillment_status + ", grams=" + grams + ", id=" + id + ", price=" + price
				+ ", product_id=" + product_id + ", quantity=" + quantity + ", requires_shipping=" + requires_shipping
				+ ", sku=" + sku + ", title=" + title + ", variant_id=" + variant_id + ", variant_title="
				+ variant_title + ", vendor=" + vendor + ", name=" + name + ", gift_card=" + gift_card + ", properties="
				+ Arrays.toString(properties) + ", taxable=" + taxable + ", tax_lines=" + Arrays.toString(tax_lines)
				+ ", total_discount=" + total_discount + "]";
	}
	
	

}
