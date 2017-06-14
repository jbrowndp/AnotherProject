package com.datapak.shopify_java_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.datapak.shopify_java_api.ShopifyAPI;
import com.datapak.shopify_java_api.model.ShopifyError;
import com.sun.jersey.core.util.Base64;

public abstract class ShopifyInterface {
	
	private Logger logger = LogManager.getLogger(ShopifyInterface.class);
	
	private ShopifyAPI shopify;
	
	
	
	/** Sends post request to Shopify Service
	 * 
	 * @param urlString The url of the REST service provided by Shopify
	 * @param postData  The data to send with the request.
	 * @return <code>JSONObject</code> containing the response from the Shopify Service
	 * @throws ShopifyError
	 */
	protected JSONObject doPost(String urlString, String postData) throws ShopifyError
	{
		logger.debug("URL String: {}, Data to Send: {}", urlString, postData);
		
		JSONObject response = null;
		
		String encodedAuthorizedUser = getAuthentication();
		HttpURLConnection conn=null;
		
			URL url;
			try {
				url = new URL(getAppURL() + urlString);
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "Basic " + encodedAuthorizedUser);
				conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				
				OutputStream out = conn.getOutputStream();
				out.write(postData.getBytes());
				out.flush();
				
				response = toJSON(processResponse(conn));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return response;
		}
			
	private String processResponse(HttpURLConnection conn) throws ShopifyError, IOException{
		
		int responseCode = conn.getResponseCode();
		
		logger.debug("Processing response from Shopify - Response Code: " + responseCode);
		
		StringBuilder response = new StringBuilder();
		
		// Request OK
					if (responseCode==200 || responseCode==201) 
					{
						BufferedReader br = new BufferedReader(new InputStreamReader(
								conn.getInputStream()));

								
										String output;
										while ((output = br.readLine()) != null) {
											response.append(output);
										}
										conn.disconnect();
					}
		
		
					else {
								
							// An error has occurred
							if (conn.getResponseCode()==422 || conn.getResponseCode()!=200)
							{
						
						
								ShopifyError error = new ShopifyError();
								error.setError_code(conn.getResponseCode());
						
								StringBuilder errorMessage = new StringBuilder();
						
								BufferedReader br = new BufferedReader(new InputStreamReader(
								conn.getErrorStream()));

								String output;
								while ((output = br.readLine()) != null) {
											errorMessage.append(output);
										}
								conn.disconnect();
								
								error.setMessage(errorMessage.toString());
								
								throw error;
							}
						}

			logger.debug("Response from Shopify: {}", response.toString());
		
			return response.toString();
		}

	
	
	/** Sends get request to Shopify Service
	 * 
	 * @param urlString  The url of the REST service provided by Shopify
	 * @return <code>JSONObject</code> containing the response from the ShopifyService
	 * @throws ShopifyError
	 */
	@SuppressWarnings("unchecked")
	protected JSONObject getResponse(String urlString) throws ShopifyError
	{
		
		String encodedAuthorizedUser = getAuthentication();
		
		
		StringBuilder response = new StringBuilder();
		logger.debug("Getting response from Shopify - URL: {}", getAppURL() + urlString);
		
		try {
			URL url = new URL(getAppURL() + urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Basic " + encodedAuthorizedUser);
			
			int responseCode = conn.getResponseCode();
			
			// Request OK
			if (responseCode==200) 
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));

						
								String output;
								while ((output = br.readLine()) != null) {
									response.append(output);
								}
								conn.disconnect();
			}
			else
			{
				
				ShopifyError e = new ShopifyError();
				e.setError_code(responseCode);
				StringBuilder errorMessage = new StringBuilder();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(
						conn.getErrorStream()));

						String output;
						while ((output = br.readLine()) != null) {
									errorMessage.append(output);
								}
						conn.disconnect();
				e.setMessage(errorMessage.toString());
				
				
				throw e;
				
			}

						
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.debug(response);
		
		
		JSONObject obj = new JSONObject();
		if (response.toString().contains("OK")) {
			
			
			obj.put("response", "OK");
		}
		else
		{
			obj =  toJSON(response.toString());
		}
		return obj;
	}

	
	private String getAuthentication()
	{
	
		   String auth = new  String(Base64.encode(shopify.getApi_key() + ":" + shopify.getPassword()));
		   return auth;
		         
	}
	
	private String getAppURL() {
		return "https://" + shopify.getApi_key() + ":" + shopify.getPassword() + "@" + shopify.getShop_name() + ".myshopify.com/admin/";
	}

	/** Sets access to Shopify service
	 * 
	 * @param shopify  Parameters used to connect to Shopify
	 */
	protected void setShopify(ShopifyAPI shopify) {
		this.shopify = shopify;
	}
	
	private JSONObject toJSON(String jsonString)
	{
		
		if (jsonString==null || jsonString.isEmpty()) { return null; }
				
		JSONParser parser = new JSONParser();
		
		logger.entry(jsonString);
		JSONObject obj=null;
		
		try {
		 obj =  (JSONObject) parser.parse(jsonString);
		 
		 logger.traceExit(obj);
		 
		 
		} catch (ParseException e) {
			
			logger.error("Unable to convert JSONString to Object.");
			
		}
		return obj;
	}
	
}
