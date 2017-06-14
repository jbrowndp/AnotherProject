package com.datapak.shopify_java_api.interfaces.impl;



import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.datapak.shopify_java_api.ShopifyAPI;
import com.datapak.shopify_java_api.ShopifyInterface;
import com.datapak.shopify_java_api.interfaces.TransactionInterface;
import com.datapak.shopify_java_api.model.ArrayParser;
import com.datapak.shopify_java_api.model.ShopifyError;
import com.datapak.shopify_java_api.model.Transaction;



@SuppressWarnings("unchecked")
public class TransactionInterfaceImpl extends ShopifyInterface implements TransactionInterface {
	

	private Logger logger = LogManager.getLogger(TransactionInterface.class);
	

	public TransactionInterfaceImpl(ShopifyAPI shopify)
	{
		super.setShopify(shopify);
	}

	/** Retrieve a specific transaction.
	 * 
	 * 
	 * @param orderId        The order Id
	 * @param transactionId  The id of the transaction
	 * 
	 * @throws ShopifyError	
	 */
	public Transaction getTransaction(Long orderId, Long transactionId) throws ShopifyError 
	{
		logger.info("Getting transaction - Order ID: {}  TransactionID: {}", orderId, transactionId);
		
		String urlString = "orders/" + orderId + "/transactions/" + transactionId + ".json";	
		
		JSONObject response = null;
		try {
			response = getResponse(urlString);
		} catch (ShopifyError e) {
			
			if (e.getError_code()==404)
			{
				logger.error("Transaction ID: {} not found for Order ID: {}",transactionId, orderId);
				return null;
			}
			
			throw e;
		}
		
		Transaction transaction = ArrayParser.toTransactions((JSONArray) response.get("transactions"))[0];

		logger.info("Retrieved transaction: {}",transaction);
		
		return transaction;
	}
	

		
	
	public Transaction[] getTransactions(Long orderId) throws ShopifyError
	{
		
		
		logger.info("Getting transaction - Order ID: {}", orderId);
		
		String urlString= "orders/" + orderId + "/transactions.json";
				
		
		JSONObject response = null;
		try {
			response = getResponse(urlString);
		} catch (ShopifyError e) {
			
			if (e.getError_code()==404)
			{
				logger.error("Transactions not found for Order ID: {}", orderId);
				return null;
			}
			
			throw e;
		}
		
		
		Transaction[] transactions = (Transaction[]) ArrayParser.toTransactions((JSONArray) response.get("transactions"));
				
					
		//parser.convertTransactionfromJSON((JSONArray) parser.convertToJSONObject(response).get("transactions"));
		
		logger.info("Retrived transactions: {}",Arrays.toString(transactions));
		
		return transactions;
		
	}
	

	
	public Long getTransactionCount(Long orderId) throws ShopifyError
	{
		logger.info("Getting transaction Count- Order ID: {}", orderId);
		
		String urlString = "orders/" + orderId + "/transactions/count.json";
		
		
		JSONObject response = null;
		try {
			response = getResponse(urlString);
		} catch (ShopifyError e) {
			
			if (e.getError_code()==404)
			{
				logger.error("Count not available for Order ID: {}", orderId);
				return null;
			}
			
			throw e;
		}
		
		
		
		return (Long) response.get("count");
		
	}
	
	

	
	/* Capture a previously authorized order for the full amount */
	
	public Transaction captureTransaction(Long order_id) throws ShopifyError {
		
		logger.info("Getting transaction - Order ID: {}", order_id);
		
		JSONObject transactionObject = new JSONObject();
		transactionObject.put("kind", "capture");
		
		JSONObject json = new JSONObject();
		json.put("transaction", transactionObject);
		
		return captureTransaction(order_id, json);
			
	}

	
	private Transaction captureTransaction(Long order_id, JSONObject json) throws ShopifyError
	{
		String urlString = "orders/" + order_id + "/transactions.json";
		JSONObject response = null;
		Transaction transaction = new Transaction();
		
		try {
			response = doPost(urlString,json.toJSONString());
		} catch (ShopifyError e) {
			
			if (e.getMessage().contains("Order cannot be captured"))
			{
			logger.error("Cannot capture transaction for Order Id: {}", order_id);
			}
			
			else
			{
				throw e;
			}
			
		}
		
		transaction.fromJSON(response);
		
		logger.info("Retrieved transaction: {}",transaction);
		return transaction;
	}

	/*Capture a specified amount on a previously authorized order
	 * 
	 */
	public Transaction captureTransaction(Long order_id, double amount) throws ShopifyError
	{
		logger.info("Getting transaction - Order ID: {}, Amount: {}", order_id, amount);
	
		JSONObject json = new JSONObject();
		JSONObject transactionObject = new JSONObject();
		transactionObject.put("kind", "capture");
		transactionObject.put("amount", amount);
		
		json.put("transaction", transactionObject);
		
		return captureTransaction(order_id, json);
		
	}

	/** Get all money transfers on a given order 
	 *  after specified ID
	 * 
	 * @param order_id  = The id of Shopify order
	 * @param specified_id = The specified id
	 */
	@Override
	public Transaction[] getTransactionsSince(Long order_id, String specified_id) throws ShopifyError {
		
		logger.info("Getting transactions Since - Order ID: {}, SpecifiedID: {}", order_id, specified_id);
		
		
		String urlString = "orders/" + order_id + "tranactions.json?since_id=" + specified_id;
		JSONObject response = getResponse(urlString);
		
		Transaction[] transactions = ArrayParser.toTransactions((JSONArray) response.get("transactions"));
		
		//Transaction[] transactions = parser.convertTransactionfromJSON((JSONArray) parser.convertToJSONObject(response).get("transactions"));
		
		logger.info("Retrived transactions: {}",Arrays.toString(transactions));
		
		return transactions;
		
		
	}

	@Override
	public Transaction createTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}
}
			  	


	
	


