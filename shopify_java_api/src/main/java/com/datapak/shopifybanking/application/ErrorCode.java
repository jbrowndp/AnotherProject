package com.datapak.shopifybanking.application;

import org.json.simple.JSONObject;

import com.datapak.shopify_java_api.interfaces.JSONInterface;

public enum ErrorCode implements JSONInterface {
	
	incorrect_number, invalid_number, invalid_expiry_date, invalid_cvc, expired_card, incorrect_cvc, 
	incorrect_zip, incorrect_address, card_declined, processing_error, call_issuer, pick_up_card;

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
