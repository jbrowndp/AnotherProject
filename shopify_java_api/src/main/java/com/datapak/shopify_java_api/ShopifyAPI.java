package com.datapak.shopify_java_api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datapak.shopify_java_api.interfaces.OrderInterface;
import com.datapak.shopify_java_api.interfaces.RefundInterface;
import com.datapak.shopify_java_api.interfaces.TransactionInterface;
import com.datapak.shopify_java_api.interfaces.impl.OrderInterfaceImpl;
import com.datapak.shopify_java_api.interfaces.impl.RefundInterfaceImpl;
import com.datapak.shopify_java_api.interfaces.impl.TransactionInterfaceImpl;


public class ShopifyAPI {
	
	private Logger logger = LogManager.getLogger(ShopifyAPI.class);
	private String api_key;
	private String password;
	private String shop_name;
	private TransactionInterface transactionManager;
	private RefundInterface refundManager;
	private OrderInterface orderManager;
	
	/**
	 * Connects to Shopify Service. User provides properties via classpath file. 
	 *  
	 *  shopify.properties
	 *  
	 * 
	 */
	public ShopifyAPI()
	{
		InputStream input =null; 
		Properties prop = new Properties();
	    input = ShopifyAPI.class.getClassLoader().getResourceAsStream("shopify.properties");
	 	try {
	 		
	 		if (input==null)
			{
				logger.fatal("No credentials specified.");
				System.exit(0);
			}
	 		
			prop.load(input);
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.exit(0);
		}
	 	
	 	
	 	api_key = prop.getProperty("api_key");
	 	password =prop.getProperty("password");
	 	shop_name= prop.getProperty("shop_name");
	 	
	 	
	}
	
	/**
	 * Connects to Shopify Service. User must provide credentials.
	 *  
	 *  @param api_key The api_key from Shopify
	 *  @param password The password given by Shopify
	 *  
	 *  @param shop_name The name of the store you wish to connect.
	 *  
	 *  
	 *  
	 */
	public ShopifyAPI(String api_key, String password, String shop_name) {
		
		this.api_key = api_key;
		this.password = password;
		this.shop_name= shop_name;

	}
	
	/**
	 * Allows access to Shopify's Transaction Interface
	 *  
	 *  
	 *  
	 *  @return TransactionInterface
	 */
	public TransactionInterface getTransactionManager() {
		
		if (transactionManager==null)
		{
			transactionManager = new TransactionInterfaceImpl(this);
		}
		return transactionManager;
	}

	
	/**
	 * Allow access to Shopify's Refund Interface
	 *  
	 *  
	 *  @return <code>RefundInterface</code>
	 */
	public RefundInterface getRefundManager() {
		
		if (refundManager==null)
		{
			refundManager = new RefundInterfaceImpl(this);
		}
		
		return refundManager;
	}

	
	/** Gets the api_key used to connect to the Shopify service
	 * 
	 * @return A String containing the api_key for connecting to the Shopify service.
	 */
	protected String getApi_key() {
		return api_key;
	}


	
	/** Gets the password used to connect to the Shopify service
	 * 
	 * @return A String containing the password used to connect to the Shopify service.
	 */
	protected String getPassword() {
		return password;
	}

	
	/** Gets the shop name used to connect to the Shopify service.
	 * 
	 * @return A String containing the shop name used to connect to the Shopify service.
	 */
	protected String getShop_name() {
		return shop_name;
	}

	
	/** Allows access to Shopify's Order Interface
	 * 
	 * @return <code>OrderInterface</code>
	 */
	public OrderInterface getOrderManager() {

		if (orderManager==null) 
		{
			orderManager = new OrderInterfaceImpl(this);
		}
		
		return orderManager;
	}

	
}
