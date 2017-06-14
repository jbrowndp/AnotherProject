package com.datapak.shopify_java_api.interfaces.impl;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.datapak.shopify_java_api.ShopifyAPI;
import com.datapak.shopify_java_api.ShopifyInterface;
import com.datapak.shopify_java_api.interfaces.RefundInterface;
import com.datapak.shopify_java_api.model.Refund;
import com.datapak.shopify_java_api.model.Refund_Line_Item;
import com.datapak.shopify_java_api.model.Shipping;
import com.datapak.shopify_java_api.model.ShopifyError;



/** @see RefundInterface
 * 
 * @author jbrown
 *
 */
@SuppressWarnings("unchecked")
public class RefundInterfaceImpl extends ShopifyInterface implements RefundInterface{
	
	private Logger logger = LogManager.getLogger(RefundInterface.class);
	
	public RefundInterfaceImpl(ShopifyAPI shopify)
	{
		super.setShopify(shopify);
	}
	
	public Refund[] getRefunds(Long order_id) throws ShopifyError {
		
		logger.info("Getting refunds - Order ID: {}", order_id);
		
		String urlString = "orders/" + order_id + "/refunds.json";
		
		JSONObject response = getResponse(urlString);

		JSONArray arr = (JSONArray) response.get("refunds");
		Refund[] refunds = new Refund[arr.size()];
		
		for (int index=0; index<arr.size(); index++)
		{
			Refund refund = new Refund();
			
			refund.fromJSON((JSONObject) arr.get(index));
			
			refunds[index]=refund;
		}
	
		logger.info("Retrieved refunds: {}",  Arrays.toString(refunds));
		
		return refunds;
		
		
	}
	
	public Refund getRefund(Long order_id, Long refund_id) throws ShopifyError {
	
		logger.info("Getting refunds - Order ID: {}, Refund ID: {} ", order_id, refund_id);
		
		
		String urlString = "orders/" + order_id + "/refunds/" + refund_id + ".json";
		JSONObject response = getResponse(urlString);
		
		Refund refund = new Refund();
		
		refund.fromJSON((JSONObject) response.get("refund"));
		
		//Refund refund = parser.convertRefundFromJSON((JSONObject) parser.convertToJSONObject(response).get("refund"));
		
		logger.traceExit(refund);
		logger.info("Retrieved refund: {}", refund);
		return refund;
	}

	
	@Override
	public Refund calculateRefund(Long orderId, Refund_Line_Item[] refund_line_items, Shipping shipping) throws ShopifyError {
		
		logger.info("Calculating refund for OrderID: {} , Refund Line Items: {}, Shipping: {}", orderId, refund_line_items, shipping);
		
		if (refund_line_items==null)
		{
			logger.error("There are no refund_line_items available for OrderID: {}", orderId);
			return null;
		}
		
		
		String urlString = "orders/" + orderId + "/refunds/calculate.json";
		
		JSONArray refundLineItemsArray = new JSONArray();
		
		for (Refund_Line_Item refund_line_item: refund_line_items)
		{
			JSONObject obj = refund_line_item.toJSON();
			
			refundLineItemsArray.add(obj);
		}
		
		JSONObject json = new JSONObject();
		JSONObject refund = new JSONObject();
		refund.put("shipping", shipping.toJSON());
		refund.put("refund_line_items", refundLineItemsArray);
		
		json.put("refund", refund);
		
		JSONObject response = doPost(urlString, json.toJSONString());
		
		
		Refund suggested_refund = new Refund();
		
		JSONObject refundObject = (JSONObject) response.get("refund");
		
		if (refundObject!=null){
			
		suggested_refund.fromJSON(refundObject);
		}
		
		//Refund suggested_refund = parser.convertRefundFromJSON((JSONObject) );
		
		logger.info("Order ID:{}, Suggested Refund: {}", orderId, suggested_refund);
		
		return suggested_refund;
	}
	
	@Override
	public Refund createRefund(Long orderId, Refund refund) throws ShopifyError
	{
		logger.info("Creating refund - Order ID: {}, Refund: {}", orderId, refund);
		
		String urlString = "orders/" + orderId + "/refunds.json";
		
		JSONObject refundObject = new JSONObject();
		
		JSONObject refundJSONString = refund.toJSON();
		
		refundObject.put("refund", refundJSONString);
		
		JSONObject response = doPost(urlString, refundObject.toJSONString());
		
		Refund createdRefund = new Refund();
		createdRefund.fromJSON((JSONObject) response.get("refund"));
		
		//Refund createdRefund = parser.convertRefundFromJSON((JSONObject) parser.convertToJSONObject(response).get("refund"));
				
		logger.traceExit(createdRefund);
		
		return createdRefund;
	}

//	@Override
//	public Refund calculateRefund(Long order_Id, Refund_Line_Item[] refund_line_items, double d) throws ShopifyError {
//
//	
//		logger.entry(order_Id, Arrays.toString(refund_line_items), d);
//		
//		String urlString = "orders/" + order_Id + "/refunds/calculate.json";
//		
//		
//		Shipping shipping = new Shipping();
//		shipping.setAmount(d);
//		
//		//JSONObject shippingObject = parser.converttoJSON(shipping);
//		
//		JSONObject shippingObject = shipping.toJSON();
//		
//		JSONArray refundLineItemsArray = new JSONArray();
//		
//		for (Refund_Line_Item refund_line_item: refund_line_items)
//		{
//			JSONObject obj = refund_line_item.toJSON();
//			
//			refundLineItemsArray.add(obj);
//		}
//		
//		JSONObject json = new JSONObject();
//		JSONObject refund = new JSONObject();
//		refund.put("shipping", shippingObject);
//		refund.put("refund_line_items", refundLineItemsArray);
//		
//		json.put("refund", refund);
//		
//
//		JSONObject response = postData(urlString, json.toJSONString());
//		
//		Refund suggested_refund = new Refund();
//		suggested_refund.fromJSON((JSONObject) response.get("refund"));
//		
//		//Refund suggested_refund = parser.convertRefundFromJSON((JSONObject) parser.convertToJSONObject(response).get("refund"));
//		
//		logger.traceExit(suggested_refund);
//		
//		return suggested_refund;
//	}


	


	

}
