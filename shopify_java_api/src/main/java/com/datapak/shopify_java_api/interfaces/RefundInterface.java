package com.datapak.shopify_java_api.interfaces;

import com.datapak.shopify_java_api.model.Refund;
import com.datapak.shopify_java_api.model.Refund_Line_Item;
import com.datapak.shopify_java_api.model.Shipping;
import com.datapak.shopify_java_api.model.ShopifyError;

/** Allows access to Shopify's Refund Interface
 * 
 * @author jbrown
 * @version 1.0
 */
public interface RefundInterface {

	
	/** Retrieve a list of Refunds for an Order
	 * 
	 * @param order_id A Long object containing the order Id 
	 * @return Refund[] containing A list of refunds for order
	 * @throws ShopifyError if order is not found or there is some issue
	 */
	Refund[] getRefunds(Long order_id) throws ShopifyError;

	
	
	
	/** Retrieve a specific refund for an Order
	 * 
	 * @param order_id A string containing the order Id
	 * @param refund_id  A string containing the id of the refund
	 * @return Refund object containing refund for the order
	 * @throws ShopifyError if order/refund is not found or there is some issue
	 */
	Refund getRefund(Long order_id, Long refund_id) throws ShopifyError;

	
	
	/** Create a Refund for an existing Order. It is highly suggested that you use <code>calculateRefund</code> to
	 * produce the transactions to submit.
	 * @param order_id  A string containing  the order Id
	 * @param refund A refund object containing information about refund to process
	 * @return Refund object containing the processed refund.
	 * @throws ShopifyError if order is not found or there is some issue
	 */
	Refund createRefund(Long order_id, Refund refund) throws ShopifyError;
	
	
	/** Calculate refund transactions based on line items and shipping. The returned response can
	 * be used to create a Refund. Note that returned transactions are of the kind
	 * "suggested refund", which must be changed to refund in order for them to be accepted.
	 * 
	 * @param order_id  A Long object containing the order ID
	 * @param lineItems Array of line item IDs and quantities to refund 
	 * @param shipping  <code>Shipping</code> Object specifying how much shipping to refund.
	 * @return <code>Refund Object </code> containing the suggested refund.
	 * @throws ShopifyError if order is not found or there is some issue
	 */
	Refund calculateRefund(Long order_id, Refund_Line_Item[] lineItems, Shipping shipping) throws ShopifyError;
	

}
