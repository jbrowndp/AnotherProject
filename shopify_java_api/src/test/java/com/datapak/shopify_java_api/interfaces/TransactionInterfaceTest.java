package com.datapak.shopify_java_api.interfaces;

import static org.junit.Assert.*;

import org.junit.Test;

import com.datapak.shopify_java_api.ShopifyAPI;
import com.datapak.shopify_java_api.TestOrderFactory;
import com.datapak.shopify_java_api.model.Order;
import com.datapak.shopify_java_api.model.ShopifyError;
import com.datapak.shopify_java_api.model.Transaction;


public class TransactionInterfaceTest {
	
	public ShopifyAPI shopify = new ShopifyAPI();
	private Transaction response;
	private String order_id = "4613298949";
	private Long order_id_bad = new Long("999999999"); // Does not exist
	private double amount = 7.42;
	private String specified_id = "4613298949";
	
	private Long transaction_no = Long.valueOf("5453854213");
	
	@Test
	public void testCaptureTransaction_PreviousOrderFullAmount(){
		
		Order order = TestOrderFactory.getOrderbyTagforTesting("capture");
		assertNotNull("No order available to capture", order);
		
		try {
			response = shopify.getTransactionManager().captureTransaction(order.getId());
			assertNotNull("Response is null", response);
			assertEquals(response.getOrder_id(), order.getId());
		} catch (ShopifyError e) {
			
			
			assertFalse(e.getMessage(),e.getMessage().contains("error"));
			
		}
	
	}
	
	@Test
	public void testCaptureTransaction_PreviousOrderSpecifiedAmount() {
		
			Order order = TestOrderFactory.getOrderforTesting("authorized");
			assertNotNull("No order available to capture", order);
		
			try {
				response = shopify.getTransactionManager().captureTransaction(order.getId(), amount);
				assertNotNull("Response is null", response);
				assertEquals(order_id, response.getOrder_id());
				assertEquals(amount, response.getAmount());
				
			} catch (ShopifyError e) {
				
				assertFalse(e.getMessage(),e.getMessage().contains("error"));
			}
			
			
	}
	
	@Test
	public void test_getTransaction_Found() {
		
		
		Order order = TestOrderFactory.getOrderforTesting("paid");
		assertNotNull("No orders available with transactions", order);
		
		Transaction transaction = TestOrderFactory.getTransactionforTesting(order.getId());
		assertNotNull("No transactions available for Order ID :" + order.getId(), transaction);
		
		Transaction response;
		try {
			response = shopify.getTransactionManager().getTransaction(order.getId(), transaction.getId());
			assertNotNull("Response is null", response);
			
			assertEquals(transaction.getId(), response.getId());
		} catch (ShopifyError e) {
			assertFalse(e.getMessage(),e.getMessage().contains("error"));
		}
		
		
	}
	
	@Test
	public void test_getTransaction_NotFound()
	{
		
		Transaction response;
		try {
			response = shopify.getTransactionManager().getTransaction(order_id_bad, transaction_no);
			assertNull("Response is not null", response);
		} catch (ShopifyError e) {
			assertTrue(e.getMessage(),e.getMessage().contains("Not Found"));
		}
		
	}

	@Test
	public void test_getTransactionCount() {
		
		Long order_id = TestOrderFactory.getOrderforTesting("paid").getId();
		Long response;
		try {
			response = shopify.getTransactionManager().getTransactionCount(order_id);
			assertNotNull("Response is null", response);
			assertTrue(response.longValue()>=0);
		} catch (ShopifyError e) {
			assertFalse(e.getMessage(),e.getMessage().contains("error"));
		}
	}
	
	
	@Test
	public void test_getTransactionCount_Transaction_NotFound()  {
		
		Long response;
		try {
			response = shopify.getTransactionManager().getTransactionCount(order_id_bad);
			assertNull("Response is not null", response);
		} catch (ShopifyError e) {
			assertTrue(e.getMessage(),e.getMessage().contains("Not Found"));
		}
		
	}


	@Test
	public void test_GetTransactions()  {
		
		Long order_id = TestOrderFactory.getOrderforTesting("paid").getId();
		Transaction[] response;
		try {
			response = shopify.getTransactionManager().getTransactions(order_id);
			assertNotNull("Response is null", response);
		} catch (ShopifyError e) {
			assertFalse(e.getMessage(),e.getMessage().contains("error"));
		}
			
	}
	
	@Test
	public void testGetTransactions_NotFound() {
		
		Transaction[] response;
		try {
			response = shopify.getTransactionManager().getTransactions(order_id_bad);
			assertNull("Response is not null", response);
		} catch (ShopifyError e) {
			assertTrue(e.getMessage(),e.getMessage().contains("Not Found"));
		}
		
		
	}

	//FIXME: Need a order with multiple transactions in order to test this functionality.
	@Test
	public void test_getTransactionsAfterSpecifiedID()  {
		
		Long order_id= TestOrderFactory.getOrderforTesting("paid").getId();
		Transaction[] response;
		try {
			response = shopify.getTransactionManager().getTransactionsSince(order_id,specified_id);
			assertNotNull("Response is null", response);	
		} catch (ShopifyError e) {
			assertFalse(e.getMessage(),e.getMessage().contains("error"));
		}
			
	}
	
	@Test
	public void test_getTransactionsAfterSpecifiedID_NotFound() {
		
		Object response;
		try {
			response = shopify.getTransactionManager().getTransactions(order_id_bad);
			assertNull("Response is not null", response);
		} catch (ShopifyError e) {
			assertTrue(e.getMessage(),e.getMessage().contains("Not Found"));
		}
		
		
	}
	
	@Test
	public void test_createTransaction()
	{
		Transaction transaction = new Transaction();
		Transaction response = shopify.getTransactionManager().createTransaction(transaction);
		
		assertNotNull(response);
	}

}
