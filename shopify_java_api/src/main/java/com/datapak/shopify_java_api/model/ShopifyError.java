package com.datapak.shopify_java_api.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.datapak.shopify_java_api.interfaces.JSONInterface;

/** Handles exceptions thrown by Shopify Interface 
 *  @author jbrown
 *  @version 1.0
 */
public class ShopifyError extends Exception implements JSONInterface {
	
	private String message;
	private int error_code;
	private Logger logger = LogManager.getLogger();
	
	public ShopifyError(String message) {
		this.message = message;
	}

	public ShopifyError() {
		// TODO Auto-generated constructor stub
	}

	
	/** Sets the error message
	 * 
	 * @param message A String containing the error message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	
	
	/** Gets the error message
	 * 
	 * @return A String containing the error message
	 */
	@Override
	public String getMessage()
	{
		return message;
		
	}

	/** @see JSONInterface
	 * 
	 */
	@Override
	public JSONObject toJSON() {
		
		return null;
	}

	/** @see JSONInterface
	 * 
	 */
	@Override
	public void fromJSON(JSONObject json) {
		
		logger.traceEntry(json.toJSONString());
		
		StringBuilder messageString = new StringBuilder();

		if (json.get("base")!=null)
		{
			JSONArray errors = (JSONArray) json.get("base");
			for (int index=0; index<json.size(); index++)
			{
			messageString.append(errors.get(index));
			}
		}
		else if (json.get("error")!=null)
		{
			
			messageString.append(json.get("error"));
			
		}	
		else
		{
			messageString.append(json.get("errors"));
		}
		
		message= messageString.toString();
		
		logger.traceExit(this);
	}

	/** Gets the error code
	 * 
	 * @return int containing the error code
	 */
	public int getError_code() {
		return error_code;
	}

	/** Sets the error code
	 * 
	 * @param error_code int containign the error code
	 */
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

}
