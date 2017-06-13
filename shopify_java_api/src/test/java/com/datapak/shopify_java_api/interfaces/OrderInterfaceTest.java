package com.datapak.shopify_java_api.interfaces;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.datapak.shopify_java_api.ShopifyAPI;
import com.datapak.shopify_java_api.TestOrderFactory;
import com.datapak.shopify_java_api.model.Order;
import com.datapak.shopify_java_api.model.Refund;
import com.datapak.shopify_java_api.model.ShopifyError;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderInterfaceTest {
	
	private ShopifyAPI shopify = new ShopifyAPI();
	private Order order;
	private Order badOrder;

	
	@Before
	public void setup() throws ShopifyError
	{
		order = TestOrderFactory.getOrderforTesting("paid");
		assertNotNull(order);
		
		badOrder = new Order();
		badOrder.setId(new Long("123456"));
	}
	
	

	@Test
	public void test1_cancelOrder()
	{
		
		boolean cancelled;
		try {
			cancelled = shopify.getOrderManager().cancelOrder(order.getId());
			assertTrue(cancelled);
		} catch (ShopifyError e) {
		
			assertFalse(e.getMessage(), e.getMessage().contains("Not Found"));
		}
		
	}
	
	@Test
	public void test2_cancelOrder_NotFound()
	{
		Boolean cancelled;
		try {
			cancelled = shopify.getOrderManager().cancelOrder(badOrder.getId());
			assertFalse(cancelled);
		} catch (ShopifyError e) {
			
			assertTrue(e.getMessage(), e.getMessage().contains("Not Found"));
		}
		
	}
	
	@Test
	public void test3_cancelandRefundOrderUsingAmount()
	{
		
		Order order = TestOrderFactory.getOrderbyTagforTesting("cancelRefund");
		
		String amount = TestOrderFactory.getAmount(order);
		
		boolean cancelled;
		try {
			cancelled = shopify.getOrderManager().cancelOrder(order.getId(), amount,  "Test Refund");
			assertTrue(cancelled);
		} catch (ShopifyError e) {
			
			assertFalse(e.getMessage(),e.getMessage().contains("error"));
		}
		
	}
	
	@Test
	public void test4_cancelandRefundOrderUsingAmount_NotFound()
	{
		
		
		Long order_id = new Long("123456");
		boolean cancelled;
		try {
			cancelled = shopify.getOrderManager().cancelOrder(order_id, "10.00",  "Broke in shipping");
			assertFalse(cancelled);
		} catch (ShopifyError e) {
			
			assertTrue(e.getMessage(), e.getMessage().contains("Not Found"));
		}
		
	}
	

	@Test
	public void test5_cancelandRefundOrderwithRefundParameter() 
	{
		Refund refund;
		try {
			refund = TestOrderFactory.calculateRefundforOrder(order);
			assertNotNull("Refund is null", refund);
			boolean cancelled = shopify.getOrderManager().cancelOrder(order.getId(), refund);;
			assertTrue(cancelled);
		} catch (ShopifyError e) {
			
			assertFalse(e.getMessage(),e.getMessage().contains("error"));
		}
		
	}
	

	public void test6_cancelOrder_withRefundParameter_NotFound()
	{
		
		Refund refund;
		try {
			refund = TestOrderFactory.calculateRefundforOrder(badOrder);
			Long order_id = new Long("123456");
			boolean cancelled = shopify.getOrderManager().cancelOrder(order_id, refund);
			assertFalse(cancelled);
		} catch (ShopifyError e) {
			
			assertTrue(e.getMessage(), e.getMessage().contains("Not Found"));
		}
		
	
	}
	
	

}
