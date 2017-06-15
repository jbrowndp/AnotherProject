package com.datapak.shopify_java_api.interfaces;


import com.datapak.shopify_java_api.model.Order;
import com.datapak.shopify_java_api.model.Refund;
import com.datapak.shopify_java_api.model.ShopifyError;


/** Allows access to Shopify's Order Interface
 * 
 * @author jbrown
 * @version 1.0
 */
public interface OrderInterface {
	
	
	/**
	 * Cancels and refunds an order using the amount parameter
	 * 
	 * @param order_id
	 *            The id of the order to cancel
	 * @param amount
	 *            String containing the amount to be refunded.
	 * @param note
	 *            String containing cancellation note
	 * @return <code>Order</code> object containing the cancelled order
	 *         information
	 * @throws ShopifyError if order is not found or there is some issue
	 */
	boolean cancelOrder(Long order_id, String amount, String note) throws ShopifyError;

	
	

	/**
	 * Cancels and refunds an order using the refund parameter
	 * 
	 * @param order_id
	 *            The id of the order to cancel
	 * @param refund 
	 *            <code>Refund</code> object containing the amount to be refunded.
	 * @return <code>Order</code> object containing the cancelled order
	 *         information
	 * @throws ShopifyError if order is not found or there is some issue
	 */
	boolean cancelOrder(Long order_id, Refund refund) throws ShopifyError;

	
	/**
	 * Cancels a Shopify order
	 * 
	 * @param order_id The id of the order to cancel
	 * @return <code>Order</code> object containing the cancelled order
	 * @throws ShopifyError if order is not found or there is some issue
	 */
	boolean cancelOrder(Long order_id) throws ShopifyError;
	
	
	/**
	 * Retrieve all orders in Shopify Store
	 * @return <code> Order[] </code> object containing all orders
	 * @throws ShopifyError if orders are not found or there is some issue
	 */
	Order[] getOrders() throws ShopifyError;

}
