package com.datapak.shopify_java_api.interfaces.impl;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.datapak.shopify_java_api.ShopifyAPI;
import com.datapak.shopify_java_api.interfaces.OrderInterface;
import com.datapak.shopify_java_api.model.ArrayParser;
import com.datapak.shopify_java_api.model.Order;
import com.datapak.shopify_java_api.model.Refund;
import com.datapak.shopify_java_api.model.ShopifyError;


/**
 * @see OrderInterface
 * @author jbrown
 *
 */

@SuppressWarnings("unchecked")
public class OrderInterfaceImpl extends com.datapak.shopify_java_api.ShopifyInterface implements OrderInterface {

	private Logger logger = LogManager.getLogger(OrderInterface.class);
	public OrderInterfaceImpl(ShopifyAPI shopify) {

		super.setShopify(shopify);
	}

	
	@Override
	public boolean cancelOrder(Long order_id) throws ShopifyError{

		logger.info("Cancelling order - Order ID: {}", order_id);
		return cancelOrder(order_id, new JSONObject());
	}



	private boolean cancelOrder(Long order_id, JSONObject json) throws ShopifyError
	{

		String urlString = "orders/" + order_id + "/cancel.json";

		JSONObject response = null;
			
			if (json!=null)
			{
				response = doPost(urlString, json.toJSONString());
			}
			else
			{
				response = doPost(urlString, "");
			}
			
			
			if (response.containsKey("order"))
			{
				Order order = new Order();
				order.fromJSON((JSONObject) response.get("order"));
				
				logger.info("Successfully canceled Order: {} ", order.getId());
				return true;

			}
			
			logger.info("Cannot cancel order - Order ID: {} is not available for cancellation", order_id);
			
			
		return false;
		
	}
	
	@Override
	public boolean cancelOrder(Long order_id, String amount, String note) throws ShopifyError {

		logger.info("Cancelling order - Order ID: {}, Amount: {}, Note: {}", order_id, amount,note);

		JSONObject json = new JSONObject();
		json.put("note", note);
		json.put("amount", Double.valueOf(amount));
		
		return cancelOrder(order_id, json);
	
	}

	
	@Override
	public boolean cancelOrder(Long order_id, Refund refund) throws ShopifyError {

		logger.info("Cancelling order - Order ID: {}, Refund; {}", order_id, refund);

		JSONObject json = new JSONObject();
		json.put("refund", refund.toJSON());

		return cancelOrder(order_id,json);
	}



	
	@Override
	public Order[] getOrders() throws ShopifyError 
	{
		logger.info("Retrieving orders....");
		
		String urlString = "orders.json";
		
		JSONObject response;
		response = getResponse(urlString);
		
		Order[] orders = ArrayParser.toOrders((JSONArray) response.get("orders"));
		
		
		logger.info("Retrieved orders: {}", Arrays.toString(orders));
		
		return orders;
		
	}
	

}
