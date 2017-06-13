package com.datapak.shopify_java_api.model;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;

/** /** Converts object from/to Shopify service and Java API
 * 
 * @author jbrown
 * @version 1.0
 *
 */
public abstract class ShopifyObject {
	
	
	
	/** Converts object instance into JSON
	 * 
	 * @return <code>JSONObject</code> containing parameters of object's instance
	 */
	public abstract JSONObject toJSON();
	
	
	/** Converts JSONObject into object instance
	 * @param json    JSONObject to convert
	 * 
	 */
	public abstract void fromJSON(JSONObject json);

	/** Converts dates received from Shopify Service into ISO8601 format
	 * @param ISO8601DateString  A string containing a date in ISO8601 format.
	 * @return <code>Date</code> containing date from Shopify Service
	 */
	public Date ISODate(String ISO8601DateString)
	{
		
		if (ISO8601DateString==null || ISO8601DateString.isEmpty()) { return null; }
		
		Calendar calendar = DatatypeConverter.parseDateTime(ISO8601DateString);
		
		return calendar.getTime();
	}
	
	
}
