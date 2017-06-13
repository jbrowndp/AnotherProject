package com.datapak.shopifybanking.application;

import org.json.simple.JSONObject;

import com.datapak.shopify_java_api.interfaces.JSONInterface;

public enum Gateway  implements JSONInterface {
	
	BOGUS;

	@Override
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fromJSON(JSONObject json) {
		// TODO Auto-generated method stub
		
	}
	
	
}
