package com.datapak.shopify_java_api.model;

import org.json.simple.JSONObject;

import com.datapak.shopify_java_api.interfaces.JSONInterface;

public enum TransactionKind implements JSONInterface {
	REFUND, VOID, CAPTURE, SALE, AUTHORIZATION;

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
