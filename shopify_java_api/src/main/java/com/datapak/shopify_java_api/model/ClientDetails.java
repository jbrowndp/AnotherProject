package com.datapak.shopify_java_api.model;

import org.json.simple.JSONObject;

/** An object containing information about the client
 * 
 * @author jbrown
 * @version 1.0
 *
 */
@SuppressWarnings("unchecked")
public class ClientDetails extends ShopifyObject{
	
	private String accept_language;
	private String browser_height;
	private String browser_ip;
	private String browser_width;
	private String session_hash;
	private String user_agent;
	
	
	/**
	 * 
	 * @return A String containing the accept language
	 */
	public String getAccept_language() {
		return accept_language;
	}
	/**
	 * 
	 * @param accept_language 
	 */
	public void setAccept_language(String accept_language) {
		this.accept_language = accept_language;
	}
	
	/** 
	 * Gets the browser screen height in pixels, if available.
	 * @return A String containing the browser screen height in pixels.
	 */
	public String getBrowser_height() {
		return browser_height;
	}
	
	/** Sets the browser screen height in pixels, if available.
	 * @param browser_height The browser screen height in pixels.
	 */
	public void setBrowser_height(String browser_height) {
		this.browser_height = browser_height;
	}
	
	/**
	 *  Gets the browser IP address
	 * @return A string containing the browser IP address.
	 */
	public String getBrowser_ip() {
		return browser_ip;
	}
	/**
	 * Sets the browser IP address
	 * @param browser_ip The browser IP address.
	 */
	public void setBrowser_ip(String browser_ip) {
		this.browser_ip = browser_ip;
	}
	
	/**
	 * Gets the browser screen width in pixels. if available
	 * @return A String containing the browser screen height in pixels.
	 */
	public String getBrowser_width() {
		return browser_width;
	}
	
	/**
	 * Sets the browser screen width in pixels. if available
	 * @param browser_width The browser screen width in pixels.
	 */
	public void setBrowser_width(String browser_width) {
		this.browser_width = browser_width;
	}
	
	/** Gets the hash of the session
	 * @return A String containing the hash of the session.
	 */
	public String getSession_hash() {
		return session_hash;
	}
	
	/**
	 * Sets the hash of the session
	 * @param session_hash The hash of the session.
	 */
	public void setSession_hash(String session_hash) {
		this.session_hash = session_hash;
	}
	
	/**
	 * 
	 * @return A String object containing user_agent;
	 */
	public String getUser_agent() {
		return user_agent;
	}
	/**
	 * 
	 * @param user_agent The user_agent;
	 */
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	
	
	/** @see ShopifyObject
	 * 
	 */
	@Override
	public JSONObject toJSON() {

		JSONObject obj = new JSONObject();
		obj.put("accept_language", accept_language);
		obj.put("browser_height", browser_height);
		obj.put("browser_ip", browser_ip);
		obj.put("browser_width", browser_width);
		obj.put("session_hash", session_hash);
		obj.put("user_agent", user_agent);
		
		return obj;
	}
	/**
	 * @see ShopifyObject
	 */
	@Override
	public void fromJSON(JSONObject json) {
		
		
		if (json ==null) { return; }
		this.setAccept_language((String) json.get("accept_language"));
		this.setBrowser_height((String) json.get("browser_height"));
		this.setBrowser_ip((String) json.get("brownser_ip"));
		this.setBrowser_width((String) json.get("brownser_width"));
		this.setSession_hash((String) json.get("session_hash"));
		this.setUser_agent((String) json.get("user_agent"));
		
	}
	@Override
	public String toString() {
		return "ClientDetails [accept_language=" + accept_language + ", browser_height=" + browser_height
				+ ", browser_ip=" + browser_ip + ", browser_width=" + browser_width + ", session_hash=" + session_hash
				+ ", user_agent=" + user_agent + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accept_language == null) ? 0 : accept_language.hashCode());
		result = prime * result + ((browser_height == null) ? 0 : browser_height.hashCode());
		result = prime * result + ((browser_ip == null) ? 0 : browser_ip.hashCode());
		result = prime * result + ((browser_width == null) ? 0 : browser_width.hashCode());
		result = prime * result + ((session_hash == null) ? 0 : session_hash.hashCode());
		result = prime * result + ((user_agent == null) ? 0 : user_agent.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientDetails other = (ClientDetails) obj;
		if (accept_language == null) {
			if (other.accept_language != null)
				return false;
		} else if (!accept_language.equals(other.accept_language))
			return false;
		if (browser_height == null) {
			if (other.browser_height != null)
				return false;
		} else if (!browser_height.equals(other.browser_height))
			return false;
		if (browser_ip == null) {
			if (other.browser_ip != null)
				return false;
		} else if (!browser_ip.equals(other.browser_ip))
			return false;
		if (browser_width == null) {
			if (other.browser_width != null)
				return false;
		} else if (!browser_width.equals(other.browser_width))
			return false;
		if (session_hash == null) {
			if (other.session_hash != null)
				return false;
		} else if (!session_hash.equals(other.session_hash))
			return false;
		if (user_agent == null) {
			if (other.user_agent != null)
				return false;
		} else if (!user_agent.equals(other.user_agent))
			return false;
		return true;
	}
	
	
	

	
	
	
	
	

	
	
	
	
	

}
