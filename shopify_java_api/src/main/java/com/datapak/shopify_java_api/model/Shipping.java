package com.datapak.shopify_java_api.model;

import org.json.simple.JSONObject;

/** Object to specify how much shipping to refund
 * 
 * @author msjac
 * @version 1.0
 */
public class Shipping extends ShopifyObject {
		
		private Double amount;
		private Boolean full_refund;
		private Double tax;
		private Double maximum_refundable;

		public Double getAmount() {
			// TODO Auto-generated method stub
			return amount;
		}
		/**
		 * 
		 * @param amount - Set specific amount of shipping to refund. Takes precedence over full_refund.
		 */
		public void setAmount(Double amount)
		{
			this.amount = amount;
		}

		public boolean isFull_refund() {
			return full_refund;
		}

		/**
		 * 
		 * @param full_refund - Boolean, set to true to refund all remaining shipping
		 */
		public void setFull_refund(boolean full_refund) {
			this.full_refund = full_refund;
		}

		public Double getTax() {
			return tax;
		}

		public void setTax(Double tax) {
			this.tax = tax;
		}

		public Double getMaximum_refundable() {
			return maximum_refundable;
		}

		public void setMaximum_refundable(Double maximum_refundable) {
			this.maximum_refundable = maximum_refundable;
		}



		@Override
		public String toString() {
			return "Shipping [amount=" + amount + ", full_refund=" + full_refund + ", tax=" + tax
					+ ", maximum_refundable=" + maximum_refundable + "]";
		}

		@SuppressWarnings("unchecked")
		@Override
		public JSONObject toJSON() {
			// TODO Auto-generated method stub
			
			JSONObject obj = new JSONObject();
			obj.put("amount", amount);
			obj.put("full_refund", full_refund);
			obj.put("tax", tax);
			obj.put("maximum_refundable", maximum_refundable);
			return obj;
		}

		@Override
		public void fromJSON(JSONObject json) {

			if (json==null) 
			{
				return;
			}
			
			this.setAmount(Double.valueOf((String) json.get("amount")));
			
			if (json.get("maximum_refundable")!=null){
			this.setMaximum_refundable(Double.valueOf((String) json.get("maximum_refundable")));
			}
			this.setTax(Double.valueOf((String) json.get("tax")));
				
		}

		
			
	}


