package com.datapak.shopify_java_api;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datapak.shopify_java_api.model.LineItem;
import com.datapak.shopify_java_api.model.Order;
import com.datapak.shopify_java_api.model.Refund;
import com.datapak.shopify_java_api.model.Refund_Line_Item;
import com.datapak.shopify_java_api.model.Shipping;
import com.datapak.shopify_java_api.model.ShopifyError;
import com.datapak.shopify_java_api.model.Transaction;

public class TestOrderFactory{
		
	private static ShopifyAPI shopify = new ShopifyAPI();
	private static Logger logger = LogManager.getLogger(TestOrderFactory.class);
	
	
	public static Order getOrderforTesting(String status)
	{
		logger.info("Getting {} order for testing....", status);
		
		
		Order[] orders;
		try {
			orders = shopify.getOrderManager().getOrders();
			if (orders!=null) { 
				
				for (int index=0; index<orders.length; index++)
				{
					if (status=="any") { return orders[index]; }
					
					if (orders[index].getFinancial_status().equals(status) && orders[index].getTags().isEmpty())
					{
						logger.info("Retrieved {} order for testing. Order: {}", status, orders[index]);
						return orders[index];
					}	
				}
				logger.info("There are no {} orders available for testing.",  status);
				}
		} catch (ShopifyError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public static Order getOrderbyTagforTesting(String tagName)
	{
		logger.info("Getting order with tag: { } for testing....", tagName);
		
		Order[] orders;
		try {
			orders = shopify.getOrderManager().getOrders();
			if (orders!=null) { 
				
				for (int index=0; index<orders.length; index++)
				{

					
					if (orders[index].getTags().equals(tagName))
					{
						logger.info("Retrieved order with tag: {} for testing. Order: {}", tagName, orders[index]);
						return orders[index];
					}	
				}
				logger.info("There are no orders with tag: {} available for testing.",  tagName);
				}
		} catch (ShopifyError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getAmount(Order order) {
		
		double amount = Double.valueOf(order.getSubtotal_price());
		
		Refund[] refunds = order.getRefunds();
		
		if (refunds!=null)
			
		for (Refund refund: refunds)
		{
			for (Transaction transaction: refund.getTransactions())
			{
				amount = amount - Double.valueOf(transaction.getAmount());
			}
		}
		return String.valueOf(amount);

	}


	public static Refund_Line_Item[] getRefundLineItemsforOrder(Order order) {
		
		
		LineItem[] lineItems = order.getLine_items();
		
		if (lineItems ==null) {
			
			logger.info("There are no line items available for this order.");
			
			return null;
			
		}
		
		int numberofItems = (int) ((Math.random() * lineItems.length));
		
		
		
		Refund_Line_Item[] refund_line_items = new Refund_Line_Item[numberofItems];
		
		for (int index =0; index< numberofItems; index++)
		{
			
			int numberToRefund = (int) ((Math.random() * lineItems[index].getQuantity()) +1);
			
			
			refund_line_items[index] = new Refund_Line_Item();
			refund_line_items[index].setLine_item_id(lineItems[index].getId());;
			refund_line_items[index].setQuantity(numberToRefund);
		}
		
		
		logger.info("Created refund_line_items: {}", Arrays.toString(refund_line_items));
		return refund_line_items;
		
	}


	public static Refund calculateRefundforOrder(Order order) throws ShopifyError {
		
		Shipping shipping = new Shipping();
		shipping.setFull_refund(true);
		
		Refund_Line_Item[] refund_line_items = getRefundLineItemsforOrder(order);
		
		
		return shopify.getRefundManager().calculateRefund(order.getId(), refund_line_items, shipping);
	}


	public static Transaction getTransactionforTesting(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	public static String getShippingAmount(Order order) {
	
		return String.valueOf("0.01");
	}
	
	
	

}
