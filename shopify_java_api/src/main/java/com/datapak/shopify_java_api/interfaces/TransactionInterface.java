package com.datapak.shopify_java_api.interfaces;

import com.datapak.shopify_java_api.model.ShopifyError;
import com.datapak.shopify_java_api.model.Transaction;

public interface TransactionInterface {

	Long getTransactionCount(Long order_id) throws ShopifyError;

	Transaction[] getTransactions(Long order_id) throws ShopifyError;

	Transaction captureTransaction(Long order_id) throws ShopifyError;
	
	Transaction captureTransaction(Long order_id, double amount) throws ShopifyError;
	
	Transaction getTransaction(Long order_id, Long transactionId) throws ShopifyError;

	Transaction[] getTransactionsSince(Long order_id, String specified_id) throws ShopifyError;

	Transaction createTransaction(Transaction transaction);


}