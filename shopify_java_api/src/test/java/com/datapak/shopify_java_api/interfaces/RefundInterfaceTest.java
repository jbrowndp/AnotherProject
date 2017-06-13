package com.datapak.shopify_java_api.interfaces;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.datapak.shopify_java_api.ShopifyAPI;
import com.datapak.shopify_java_api.TestOrderFactory;
import com.datapak.shopify_java_api.model.Order;
import com.datapak.shopify_java_api.model.Refund;
import com.datapak.shopify_java_api.model.Refund_Line_Item;
import com.datapak.shopify_java_api.model.Shipping;
import com.datapak.shopify_java_api.model.ShopifyError;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RefundInterfaceTest {
	
	private ShopifyAPI shopify = new ShopifyAPI();
	
	private static Order order1;
	private static Order order2;
	
	private static Refund refund1;
	private static Refund refund2; 
	

	@BeforeClass
	public static void setup() throws ShopifyError
	{
		order1 = TestOrderFactory.getOrderforTesting("paid");
		order2 = TestOrderFactory.getOrderforTesting("paid");

		assertNotNull(order1);
		assertNotNull(order2);
	}
	
	
	@Test
	public void test1_CalculateRefund_LineItemAndShipping()
	{
		Shipping shipping = new Shipping();
		shipping.setFull_refund(true);
		
		
		
		try {
			
			Refund_Line_Item[] refund_line_items = TestOrderFactory.getRefundLineItemsforOrder(order1);
			assertNotNull("Refund_Line_Items are null", refund_line_items);
			
			
			refund1 = shopify.getRefundManager().calculateRefund(order1.getId(), refund_line_items, shipping);
			
			assertNotNull("Suggested_refund is null", refund1);
			assertNotNull("Shipping is null", refund1.getShipping());
			assertNotNull("Refund_Line_Items are null", refund1.getRefund_line_items());
			assertNotNull("Transactions are null", refund1.getTransactions());
			
			Refund response = shopify.getRefundManager().createRefund(order1.getId(), refund1);
			assertNotNull("Created refund is null", response);
			
		} catch (ShopifyError e) {
			
			assertNotEquals(e.getMessage(), e.getMessage().contains("error"));
			assertNotEquals(e.getMessage(), 422, e.getError_code());
			
		}
	}
	
	@Test
	public void test2_GetRefunds()
	{
		Refund[] response;
		try {
			response = shopify.getRefundManager().getRefunds(order1.getId());
			assertNotNull("Response is null", response);
			assertNotEquals(0,response.length);
		} catch (ShopifyError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

	@Test
	public void test3_CalculateRefund_PartialAmountShipping()
	{
		Shipping shipping = new Shipping();
		shipping.setAmount(Double.valueOf(TestOrderFactory.getShippingAmount(order2)));
		
		Refund_Line_Item[] refund_line_items = TestOrderFactory.getRefundLineItemsforOrder(order2);

		try {
			
			refund2 = shopify.getRefundManager().calculateRefund(order2.getId(), refund_line_items, shipping);
			assertNotNull("Response is null", refund2);
			assertNotNull("Shipping is null", refund2.getShipping());
			assertNotNull("Refund_Line_Items are null", refund2.getRefund_line_items());
			assertNotNull("Transactions are null", refund2.getTransactions());
		} catch (ShopifyError e) {
			
			assertNotEquals(e.getMessage(), e.getMessage().contains("error"));
			assertNotEquals(e.getMessage(), 422, e.getError_code());
			
		}
		
	
	}
	
	

	

	@Test
	public void test4_CreateRefund_SpecificAmount()
	{
		
		
		
		try {
			
			assertNotNull("Refund is null", refund2);
			assertNotNull("Order is null", order2);
			Refund response = shopify.getRefundManager().createRefund(order2.getId(), refund2);
			assertNotNull("Response is null", response);
		} catch (ShopifyError e) {
			
			assertNotEquals(e.getMessage(), e.getMessage().contains("error"));
			assertNotEquals(e.getMessage(), 422, e.getError_code());
		}
		
	}
	
	
	
	@Test
	public void test5_GetRefunds()
	{
		Refund[] response;
		try {
			response = shopify.getRefundManager().getRefunds(order2.getId());
			assertNotNull("Response is null", response);
			assertNotEquals(0,response.length);
		} catch (ShopifyError e) {
			
			assertNotEquals(e.getMessage(), e.getMessage().contains("error"));
		}
		
	}
	


	
	}
	
  
