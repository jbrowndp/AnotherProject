package com.datapak.shopify_java_api.interfaces;

import com.datapak.shopify_java_api.model.ShopifyError;
import com.datapak.shopify_java_api.model.Transaction;

/** Allows access to Shopify's Refund Interface
 * 
 * @author jbrown
 * @version 1.0
 */
public interface TransactionInterface {

	/** Retrieve count of all a given order's money transfer
	 * 
	 * @param order_id The order Id
	 * @return Long object containing a count of all transactions for the order
	 * @throws ShopifyError if order not found or there is some issue.
	 */
	Long getTransactionCount(Long order_id) throws ShopifyError;

	
	/** Retrieve all money transfers on a given order
	 * 
	 * @param order_id The order Id
	 * @return Transaction[] containing all transaction for the order
	 * @throws ShopifyError if order not found or there is some issue.
	 */
	Transaction[] getTransactions(Long order_id) throws ShopifyError;

	
	/** Capture a previously authorized order for the full amount
	 * 
	 * @param order_id The order Id
	 * @return Transaction object with captured transaction details
	 * @throws ShopifyError if order not found or there is some issue.
	 */
	Transaction captureTransaction(Long order_id) throws ShopifyError;
	
	
	/** Capture a specified amount on a previously authorized order
	 * 
	 * @param order_id The order Id
	 * @param amount The amount to capture
	 * @return Transaction object with captured transaction details
	 * @throws ShopifyError if order not found or there is some issue.
	 */
	Transaction captureTransaction(Long order_id, double amount) throws ShopifyError;
	
	
	
	/** Retrieve a specific transaction.
	 * 
	 * 
	 * @param order_id        The order Id
	 * @param transactionId  The id of the transaction
	 * @return Transaction object containing transaction details
	 * @throws ShopifyError	if order/transaction not found or there is some issue
	 */
	Transaction getTransaction(Long order_id, Long transactionId) throws ShopifyError;

	
	
	/** Get all money transfers on a given order 
	 *  after specified ID
	 * 
	 * @param order_id  = The id of Shopify order
	 * @param specified_id = The specified id
	 * @return Transaction[] containing all transactions since specified ID
	 * @throws ShopifyError if order/transaction not found or there is some issue
	 */
	Transaction[] getTransactionsSince(Long order_id, String specified_id) throws ShopifyError;


}