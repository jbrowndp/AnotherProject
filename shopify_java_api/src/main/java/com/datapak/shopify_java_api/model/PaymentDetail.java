package com.datapak.shopify_java_api.model;

import org.json.simple.JSONObject;

/** An object containing information about the payment
 * 
 * @author jbrown
 * @version 1.0
 *
 */
public class PaymentDetail extends ShopifyObject {
	
	private Character avs_result_code;
	private String credit_card_bin;
	private String credit_card_company;
	private String credit_card_number;
	private String cvv_result_code;
	
	
	/** Gets the response code from AVS the address verification system.
	 * 
	 * @return char object containing the response code from AVS
	 */
	public Character getAvs_result_code() {
		return avs_result_code;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentDetail [avs_result_code=" + avs_result_code + ", credit_card_bin=" + credit_card_bin
				+ ", credit_card_company=" + credit_card_company + ", credit_card_number=" + credit_card_number
				+ ", cvv_result_code=" + cvv_result_code + "]";
	}

	/** Gets the response code from AVS the address verification system.
	 * 
	 * @param avs_result_code The response code from AVS
	 */
	public void setAvs_result_code(Character avs_result_code) {
		this.avs_result_code = avs_result_code;
	}
	
	
	/**Gets the issuer identification number(IIN) of the customer's credit card. This is made up of the first few digits of 
	 * credit card number.
	 * @return A String containing the issuer identification number(IIN) of the customer's credit card.
	 */
	public String getCredit_card_bin() {
		return credit_card_bin;
	}
	
	/**Sets the issuer identification number(IIN) of the customer's credit card. This is made up of the first few digits of 
	 * credit card number.
	 * @param credit_card_bin A String containing the issuer identification number(IIN) of the customer's credit card.
	 */
	public void setCredit_card_bin(String credit_card_bin) {
		this.credit_card_bin = credit_card_bin;
	}
	
	
	/** Gets the name of the company who issued the customer's credit card
	 * 
	 * @return A String containing the name of the company who issues the customer's credit card
	 */
	public String getCredit_card_company() {
		return credit_card_company;
	}
	
	/** Sets the name of the company who issued the customer's credit card
	 * 
	 * @param credit_card_company The name of the company who issues the customer's credit card
	 */
	public void setCredit_card_company(String credit_card_company) {
		this.credit_card_company = credit_card_company;
	}
	
	/** Gets the customer credit card number, which most of the leading digits redacted with Xs.
	 * 
	 * @return A String containing the customer credit card number, which most of the leading digits redacted with Xs.
	 */
	public String getCredit_card_number() {
		return credit_card_number;
	}
	
	/** Sets the customer credit card number, which most of the leading digits redacted with Xs.
	 * 
	 * @param credit_card_number  The customer credit card number, which most of the leading digits redacted with Xs.
	 */
	public void setCredit_card_number(String credit_card_number) {
		this.credit_card_number = credit_card_number;
	}
	
	/** Gets the response code from the credit card company indicating whether the customer enter the card security code correctly. 
	 * 
	 * @return A STring object containing the response code from the credit card company indicating whether the customer enter the card 
	 * security code correctly.
	 */
	public String getCvv_result_code() {
		return cvv_result_code;
	}
	
	
	/** Sets the response code from the credit card company indicating whether the customer enter the card security code correctly. 
	 * 
	 * @param cvv_result_code The response code from the credit card company indicating whether the customer enter the card 
	 * security code correctly.
	 */
	public void setCvv_result_code(String cvv_result_code) {
		this.cvv_result_code = cvv_result_code;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	protected JSONObject toJSON() {
	
		JSONObject obj = new JSONObject();
		
		obj.put("avs_result_code", avs_result_code);
		obj.put("credit_card_bin", credit_card_bin);
		obj.put("credit_card_company", credit_card_company);
		obj.put("credit_card_number", credit_card_number);
		obj.put("cvv_result_code", cvv_result_code);
		
		return obj;
		
	}

	@Override
	protected void fromJSON(JSONObject json) {
		
		
		if (json==null) { 

			return; }
		
		this.setCredit_card_company((String) json.get("credit_card_company"));
		this.setCredit_card_bin((String) json.get("credit_card_bin"));
		this.setAvs_result_code((Character) json.get("avs_result_code"));
		this.setCvv_result_code((String) json.get("cvv_result_code"));
		this.setCredit_card_number((String) json.get("credit_card_number"));
		
	}

	

}
