package com.datapak.shopifybanking.application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


import com.datapak.shopify_java_api.model.PaymentDetail;
import com.datapak.shopify_java_api.model.Status;
import com.datapak.shopify_java_api.model.Transaction;
import com.datapak.shopify_java_api.model.TransactionKind;

public class JSONParser {
	
	private Logger logger = LogManager.getLogger(JSONParser.class);
	org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
	
	public JSONObject convertToJSONObject(String jsonString)
	{
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
	

	public PaymentDetail[] convertPaymentDetailsFromJSON(JSONArray jsonArray)
	{
		
		logger.entry(jsonArray);
		
		
		if (jsonArray ==null) { return null; }
		
		List<PaymentDetail> paymentDetails = new ArrayList<PaymentDetail>();
		JSONObject obj = new JSONObject();
			
			JSONArray transactionArray = (JSONArray) obj.get("payment_details");
			
			if (transactionArray==null) 
			{
				logger.traceExit(null);
				return null;
			}
			
			for (int index=0; index<transactionArray.size(); index++)
			{
				
				JSONObject payment_DetailObject = (JSONObject) transactionArray.get(index);
			
				PaymentDetail paymentDetail = new PaymentDetail();
				paymentDetail.setAvs_result_code((Character) payment_DetailObject.get("avs_result_code"));
				paymentDetail.setCredit_card_bin((String) payment_DetailObject.get("credit_card_bin"));
				paymentDetail.setCredit_card_company((String) payment_DetailObject.get("credit_card_company"));
				paymentDetail.setCredit_card_number((String) payment_DetailObject.get("credit_card_number"));
				paymentDetail.setCvv_result_code((String) payment_DetailObject.get("cvv_result_code"));
				
				
				
				
				paymentDetails.add(paymentDetail);
				
				logger.traceExit(paymentDetail.toString());
				
			}
			
		
		return (PaymentDetail[]) paymentDetails.toArray();
		
		
	}
	
	public Date parseDate_ISO8601(String ISO8601DateString)
	{
		if (ISO8601DateString==null || ISO8601DateString.isEmpty()) { return null; }
		
		
		Calendar calendar = DatatypeConverter.parseDateTime(ISO8601DateString);
		
		return calendar.getTime();
		
	}

//	public Refund_Line_Item[] convertRefund_Line_ItemfromJSON(JSONArray refund_Line_Item_Array) {
//		
//		List<Transaction> refund_line_item_list = new ArrayList<Transaction>();
//
//		for (int index=0; index<refund_Line_Item_Array.size(); index++)
//		{
//			
//			
//			JSONObject refundLineItemObject = (JSONObject) refund_Line_Item_Array.get(index);
//			
//			Refund_Line_Item refund_line_item = new Refund_Line_Item(); 
//		
//			refund_line_item.setId((Long) refundLineItemObject.get("id"));
//			refund_line_item.setLine_item((String) refundLineItemObject.get("line_item"));
//			refund_line_item.setLine_item_id((Long) refundLineItemObject.get("line_item_id"));
//			refund_line_item.setQuantity((Integer) refundLineItemObject.get("qty"));
//			
//			logger.traceExit(refund_line_item.toString());
//			
//		}
//		
//		Refund_Line_Item[] refund_line_items = new Refund_Line_Item[refund_line_item_list.size()];
//		refund_line_items = (Refund_Line_Item[]) refund_line_item_list.toArray(refund_line_items);
//		return refund_line_items;
//	}

//	public Refund[] convertRefundFromJSON(JSONArray refundArray)
//	{
//		List<Refund> refundList = new ArrayList<Refund>();
//
//		for (int index=0; index<refundArray.size(); index++)
//		{
//		
//			Refund refund = convertRefundFromJSON((JSONObject) refundArray.get(index));
//			refundList.add(refund);
//		}
//		
//			Refund[] refunds = new Refund[refundList.size()];
//			refunds = (Refund[]) refundList.toArray(refunds);
//			return refunds;
//		
//	}
	
//	public Refund convertRefundFromJSON(JSONObject refundObject)
//	{
//		
//		logger.entry(refundObject);
//		Refund refund = new Refund();
//		refund.setCreated_at(parseDate_ISO8601((String) refundObject.get("created_at")));
//		refund.setProcessed_at(parseDate_ISO8601((String) refundObject.get("processed_at")));
//		//refund.setShipping(convertShippingfromJSON((JSONObject) refundObject.get("shipping")));
//		
//		Shipping shipping = new Shipping();
//		shipping.fromJSON((JSONObject) refundObject.get("shipping"));
//		
//		refund.setShipping(shipping);
//		
//		
//		refund.setId((Long) refundObject.get("id"));
//		refund.setNote((String) refundObject.get("note"));
//		refund.setRefund_line_items(convertRefund_Line_ItemfromJSON((JSONArray) refundObject.get("refund_line_items")));
//		refund.setRestock((Boolean) refundObject.get("restock"));
//		refund.setTransactions(convertTransactionfromJSON((JSONArray) refundObject.get("transactions")));
//		refund.setUser_id((Long) refundObject.get("user_id"));
//		
//
//		logger.traceExit(refund.toString());
//		
//	return refund;
//	}
	
//	private Shipping convertShippingfromJSON(JSONObject shippingObject) {
//		
//		
//		if (shippingObject==null) return null;
//		
//		Shipping shipping = new Shipping();
//		shipping.setAmount((Double.valueOf((String) shippingObject.get("amount"))));
//		shipping.setMaximum_refundable(Double.valueOf((String) shippingObject.get("maximum_refundable")));
//		shipping.setTax(Double.valueOf((String) shippingObject.get("tax")));
//		
//		logger.traceExit(shipping.toString());
//		return shipping;
//		
//		
//	}


	public Transaction[] convertTransactionfromJSON(JSONArray transactionArray)
	{
		
		logger.entry(transactionArray);
		List<Transaction> transactionsList = new ArrayList<Transaction>();

		for (int index=0; index<transactionArray.size(); index++)
		{
			
			Transaction transaction = convertTransactionfromJSON((JSONObject) transactionArray.get(index));
			
			transactionsList.add(transaction);
			
			logger.traceExit(transaction.toString());
			
		}
		
		Transaction[] transactions = new Transaction[transactionsList.size()];
		transactions = (Transaction[]) transactionsList.toArray(transactions);
		
		logger.traceExit(Arrays.toString(transactions));
		return transactions;
	}
	
	private TransactionKind convertTransactionKindfromJSON(String transactionKind) {
		
		switch(transactionKind)
		{
		case "authorization":
			return TransactionKind.AUTHORIZATION;
		case "sale":
			return TransactionKind.SALE;
		case "capture":
			return TransactionKind.CAPTURE;
		case "void":
			return TransactionKind.VOID;
		case "refund":
			return TransactionKind.REFUND;
		default:
			return null;
		}
	}

	public Transaction convertTransactionfromJSON(JSONObject transactionObject) {
		
		
		logger.entry(transactionObject);
		
		Transaction transaction = new Transaction();
		transaction.setAmount((String) transactionObject.get("amount"));
		transaction.setAuthorization((String)transactionObject.get("authorization"));
		transaction.setCreated_at(parseDate_ISO8601((String) transactionObject.get("created_at")));
		transaction.setDevice_id((String) transactionObject.get("device_id"));
		transaction.setGateway((String) transactionObject.get("gateway"));
		transaction.setSource_name("source_web");
		transaction.setId((Long) transactionObject.get("id"));
		transaction.setKind(convertTransactionKindfromJSON(transactionObject.get("kind").toString()));
		transaction.setOrder_id((Long) transactionObject.get("order_id"));
		
		//FIXME: Returns JSONObject need to parse this
		transaction.setReceipt((String) transactionObject.get("recieipt"));
		//transaction.setPayment_details(convertPaymentDetailsFromJSON((JSONObject) transactionObject.get("payment_details")));
		

		
		
		//FIXME: Returning null here
		
		//transaction.setError_code(convertErrorCodefromJSON(transactionObject.get("error_code").toString()));
		
		
		
		
		
		transaction.setStatus(convertStatusfromJSON(transactionObject.get("status")));
		transaction.setTest((Boolean) transactionObject.get("test"));
		transaction.setUser_id((String) transactionObject.get("user_id"));
		transaction.setCurrency((String) transactionObject.get("currency"));
		
		
		logger.traceExit(transaction);
		return transaction;
	}

	private Status convertStatusfromJSON(Object object) {
		// TODO Auto-generated method stub
		return null;
	}


	


//	@SuppressWarnings("unchecked")
//	public JSONObject convertToJSON(Refund refund) {
//		
//		logger.entry(refund);
//		
//		JSONObject obj = new JSONObject();
//		
//		obj.put("created_at", refund.getCreated_at());
//		obj.put("processed_at", refund.getProcessed_at());
//		obj.put("id", refund.getId());
//		obj.put("note", refund.getNote());
//		obj.put("refund_line_items", convertToJSON(refund.getRefund_line_items()));
//		obj.put("restock", refund.isRestock());
//		obj.put("transactions",  convertToJSON(refund.getTransactions()));
//		obj.put("user_id", refund.getUser_id());
//		
//		logger.traceExit(obj);	
//		
//		return obj;
//	}

	@SuppressWarnings("unchecked")
	public JSONArray convertToJSON(Transaction[] transactions) {
		
		
		logger.entry(Arrays.toString(transactions));
		
		JSONArray arr = new JSONArray();
		
		if (transactions==null) return null;
		
		for (Transaction transaction: transactions)
		{
			JSONObject obj = new JSONObject();
			
			obj.put("amount", transaction.getAmount());
			obj.put("authorization", transaction.getAuthorization());
			obj.put("created_at", transaction.getCreated_at());
			obj.put("device_id", transaction.getDevice_id());
			obj.put("gateway", transaction.getGateway());
			obj.put("source_name", transaction.getSource_name());
			obj.put("payment_details", convertToJSON(transaction.getPayment_details()));
			obj.put("id", transaction.getId());
			obj.put("kind", transaction.getKind());
			obj.put("order_id",  transaction.getOrder_id());
			obj.put("receipt", transaction.getReceipt());
			obj.put("error_code", transaction.getError_code());
			obj.put("status", transaction.getStatus());
			obj.put("test", transaction.getTest());
			obj.put("user_id", transaction.getUser_id());
			obj.put("currency",transaction.getCurrency());
			
			arr.add(obj);
			
		}	
		return arr;
	}

	@SuppressWarnings("unchecked")
	public JSONArray convertToJSON(PaymentDetail[] payment_details) {
			
		logger.entry(Arrays.toString(payment_details));
		
		JSONArray arr = new JSONArray();
		
		if (payment_details==null) return null;
		
		
		for (PaymentDetail payment_detail: payment_details)
		{
			JSONObject obj = new JSONObject();
			
			
			obj.put("avs_result_code", payment_detail.getAvs_result_code());
			obj.put("credit_card_bin", payment_detail.getCredit_card_bin());
			obj.put("credit_card_company", payment_detail.getCredit_card_company());
			obj.put("credit_card_number", payment_detail.getCredit_card_number());
			obj.put("cvv_result_code", payment_detail.getCvv_result_code());
			
				arr.add(obj);
					
		}
		return arr;
	}

//	@SuppressWarnings("unchecked")
//	public JSONArray convertToJSON(Refund_Line_Item[] refund_line_items) {
//
//		logger.entry(Arrays.toString(refund_line_items));
//		
//		JSONArray arr = new JSONArray();
//		
//		for (Refund_Line_Item refund_line_item: refund_line_items)
//		{
//			JSONObject obj = new JSONObject();
//			
//			obj.put("id", refund_line_item.getId());
//			obj.put("line_item", refund_line_item.getLine_item());
//			obj.put("line_item_id", refund_line_item.getLine_item_id());
//			obj.put("quantity", refund_line_item.getQuantity());
//			
//			
//			arr.add(obj);
//		}
//		
//		logger.traceExit(arr);
//		return arr;
//	}


//	public ShopifyError convertErrorfromJSON(JSONObject errorObject) {
//		
//		logger.entry(errorObject);
//		StringBuilder message = new StringBuilder();
//
//		JSONArray errors = (JSONArray) errorObject.get("base");
//		for (int index=0; index<errorObject.size(); index++)
//		{
//			message.append(errors.get(index));
//		}
//		
//		logger.traceExit(message.toString());
//		return new ShopifyError(message.toString());
//		
//	}


//	@SuppressWarnings("unchecked")
//	public JSONObject converttoJSON(Shipping shipping) {
//		
//		logger.entry(shipping.toString());
//		
//		JSONObject obj = new JSONObject();
//		
//		if (shipping.getAmount()!=null)
//		{
//			obj.put("amount", shipping.getAmount());
//			
//		}
//		else if (shipping.isFull_refund())
//		{
//			obj.put("full_refund", true);
//		}
//		else
//		{
//			
//		}
//		
//
//		logger.traceExit(obj);
//		return obj;
//	}



	
}
