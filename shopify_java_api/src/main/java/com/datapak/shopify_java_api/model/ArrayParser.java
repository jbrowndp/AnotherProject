package com.datapak.shopify_java_api.model;

import java.util.Arrays;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@SuppressWarnings("unchecked")
/** Manages the conversion of Array objects to/from ShopifyInterfaces
 * 
 * @author jbrown
 * @version 1.0
 *
 */
public class ArrayParser {
	
	private static Logger logger = LogManager.getLogger(ArrayParser.class);
	
	
	/** Converts from DiscountCode[] into JSONArray
	 * 
	 * @param discount_code array containing discount codes
	 * @return JSONArray containing discount codes
	 */
	public static JSONArray toJSON(DiscountCode[] discount_codes)
	{
		logger.entry(Arrays.toString(discount_codes));
		JSONArray discount_code_array = new JSONArray();
		
		for (DiscountCode discount_code: discount_codes)
		{
				JSONObject discount_codeObject = discount_code.toJSON();
				discount_code_array.add(discount_codeObject);
			
		}
		
		logger.traceExit(discount_code_array);
		return discount_code_array;
		
	}
	
	
	/** Converts from JSONArray into DiscountCode[]
	 * 
	 */
	public static DiscountCode[] toDiscountCodes(JSONArray jsonArray)
	{
		logger.entry(jsonArray);
		if (jsonArray==null) return null;
		DiscountCode[] discountCodes = new DiscountCode[jsonArray.size()];
		
		for (int index=0; index<jsonArray.size(); index++)
		{
			DiscountCode discountCode = new DiscountCode();
			discountCode.fromJSON((JSONObject) jsonArray.get(index));
			discountCodes[index]= discountCode;
		}
		
		logger.traceExit(Arrays.toString(discountCodes));
		
		return discountCodes;
	}
	
	
	/** Converts from Refund[] into JSONArray
	 * 
	 * @param refunds array containing refunds
	 * @return
	 */
	public static JSONArray toJSON(Refund[] refunds)
	{
		logger.traceEntry(Arrays.toString(refunds));
		JSONArray refund_array = new JSONArray();
		
		for (Refund refund: refunds)
		{
				JSONObject refundObject = refund.toJSON();
				refund_array.add(refundObject);
			
		}
		logger.traceExit(refund_array);
		return refund_array;
	}
	
	/** Converts from JSONArray into Refund[]
	 *  
	 */
	public static Refund[] toRefund(JSONArray jsonArray)
	{
		logger.entry(jsonArray);
		if (jsonArray==null) return null;
		Refund[] refunds= new Refund[jsonArray.size()];
		
		for (int index=0; index<jsonArray.size(); index++)
		{
			Refund refund = new Refund();
			refund.fromJSON((JSONObject) jsonArray.get(index));
			refunds[index]= refund;
		}
		
		logger.traceExit(Arrays.toString(refunds));
		
		return refunds;
	}
	
	
	/** Converts from ShippingLine[] into JSONArray
	 * 
	 * @param shippingLines array cntaining shippingLines
	 * @return
	 */
	public static JSONArray toJSON(ShippingLine[] shippingLines)
	{
		
		logger.traceEntry(Arrays.toString(shippingLines));
		JSONArray shippingLine_array = new JSONArray();
		
		for (ShippingLine shippingLine: shippingLines)
		{
				JSONObject shippingLineObject = shippingLine.toJSON();
				shippingLine_array.add(shippingLineObject);
			
		}
		
		logger.traceExit(shippingLine_array);
		return shippingLine_array;
	}
	
	/** Converts from JSONArray into ShippingLine[]
	 * 
	 */
	public static ShippingLine[] toShippingLines(JSONArray jsonArray)
	{
		logger.entry(jsonArray);
		if (jsonArray==null) return null;
		ShippingLine[] shippingLines = new ShippingLine[jsonArray.size()];
		
		for (int index=0; index<jsonArray.size(); index++)
		{
			ShippingLine shippingLine = new ShippingLine();
			shippingLine.fromJSON((JSONObject) jsonArray.get(index));
			shippingLines[index]= shippingLine;
		}
		
		logger.traceExit(Arrays.toString(shippingLines));
		
		return shippingLines;
	}
	
	/** Converts from TaxLine[] into JSONArray
	 * 
	 * @param taxLine array containing taxLines
	 * @return JSONArray containing taxLines
	 */
	public static JSONArray toJSON(TaxLine[] taxLines)
	{
		JSONArray taxLine_array = new JSONArray();
		
		for (TaxLine taxLine: taxLines)
		{
				JSONObject taxLineObject = taxLine.toJSON();
				taxLine_array.add(taxLineObject);
			
		}
		return taxLine_array;
	}

	
	/** Converts from JSONArray into TaxLine[]
	 * 
	 * @param fulfillments
	 * @return
	 */
	public static TaxLine[] toTaxLines(JSONArray jsonArray)
	{
		logger.entry(jsonArray);
		if (jsonArray==null) return null;
		TaxLine[] taxLines = new TaxLine[jsonArray.size()];
		
		for (int index=0; index<jsonArray.size(); index++)
		{
			TaxLine taxLine = new TaxLine();
			taxLine.fromJSON((JSONObject) jsonArray.get(index));
			taxLines[index]= taxLine;
		}
		
		logger.traceExit(Arrays.toString(taxLines));
		
		return taxLines;
	}
	
	
	/** Convert from JSONArray to FulFillment[]
	 * @param fulfillments
	 * @return
	 */
	public static JSONArray toJSON(FulFillment[] fulfillments) {
		JSONArray fulfillment_array = new JSONArray();
		
		for (FulFillment fulfillment: fulfillments)
		{
				JSONObject fulfillmentObject = fulfillment.toJSON();
				fulfillment_array.add(fulfillmentObject);
			
		}
		return fulfillment_array;
	}

	
	
	
	
	/** Converts from LineItem[] into JSONArray
	 * 
	 * @param line_items array of line items
	 * @return JSONArray containing line items
	 */
	public static JSONArray toJSON(LineItem[] line_items) {
		
		JSONArray line_item_array = new JSONArray();
		
		for (LineItem line_item: line_items)
		{
				JSONObject line_itemObject = line_item.toJSON();
				line_item_array.add(line_itemObject);
			
		}
		return line_item_array;
	}

	/** Converts from PaymentDetail[] into JSONArray
	 * 
	 * @param payment_details array of payment details
	 * @return JSONArray containing payments details
	 */
	public static JSONArray toJSON(PaymentDetail[] payment_details) {

		JSONArray payment_detail_array = new JSONArray();
		
		for (PaymentDetail payment_detail: payment_details)
		{
				JSONObject payment_detailObject = payment_detail.toJSON();
				payment_detail_array.add(payment_detailObject);
			
		}
		return payment_detail_array;
	}
	
	
	/** Converts from ShippingAddress[] into JSONArray
	 * 
	 * @param shippingAddresses array of shipping addresses
	 * @return JSONArray containing shipping addresses
	 */
	public static JSONArray toJSON(ShippingAddress[] shippingAddresses) {
		
		
		JSONArray shippingAddress_array = new JSONArray();
		for (ShippingAddress shippingAddress: shippingAddresses)
		{
				JSONObject shippingAddressObject = shippingAddress.toJSON();
				shippingAddress_array.add(shippingAddressObject);
			
		}
		return shippingAddress_array;
	}
	
	/** Converts from Refund_Line_item[] into JSONArray
	 * 
	 * @param refund_line_item array of payment details
	 * @return JSONArray containing payments details
	 */
	public static JSONArray toJSON(Refund_Line_Item[] refund_line_items)
	{
			JSONArray refund_line_item_array = new JSONArray();
		
		for (Refund_Line_Item refund_line_item: refund_line_items)
		{
				JSONObject refund_Line_itemObject = refund_line_item.toJSON();
				refund_line_item_array.add(refund_Line_itemObject);
			
		}
		return refund_line_item_array;
	}
	/** Converts from PaymentDetail[] into JSONArray
	 * 
	 * @param payment_details array of payment details
	 * @return JSONArray containing payments details
	 */
	public static JSONArray toJSON(Transaction[] transactions)
	{
			JSONArray transaction_array = new JSONArray();
		
		for (Transaction transaction: transactions)
		{
				JSONObject transactionObject = transaction.toJSON();
				transaction_array.add(transactionObject);
			
		}
		
		return transaction_array;
	}

	public static Transaction[] toTransactions(JSONArray jsonArray) {
		
		logger.entry(jsonArray);
		if (jsonArray==null) return null;
		
		Transaction[] transactions = new Transaction[jsonArray.size()];
		
		for (int index=0; index<jsonArray.size(); index++)
		{
			Transaction transaction = new Transaction();
			transaction.fromJSON((JSONObject) jsonArray.get(index));
			transactions[index]= transaction;
		}
		
		logger.traceExit(Arrays.toString(transactions));
		
		return transactions;
	}

	public static PaymentDetail[] toPaymentDetails(JSONArray jsonArray) {
		
		logger.entry(jsonArray);
		
		if (jsonArray==null) return null;
		
		PaymentDetail[] PaymentDetails= new PaymentDetail[jsonArray.size()];
		
		for (int index=0; index<jsonArray.size(); index++)
		{
			PaymentDetail PaymentDetail = new PaymentDetail();
			PaymentDetail.fromJSON((JSONObject) jsonArray.get(index));
			PaymentDetails[index]= PaymentDetail;
		}
		
		logger.traceExit(Arrays.toString(PaymentDetails));
		
		return PaymentDetails;
	}

	public static Order[] toOrders(JSONArray jsonArray) {
		
		logger.entry(jsonArray);
		
		if (jsonArray==null) return null;
		
		Order[] orders= new Order[jsonArray.size()];
		
		for (int index=0; index<jsonArray.size(); index++)
		{
			Order order = new Order();
			order.fromJSON((JSONObject) jsonArray.get(index));
			orders[index]= order;
		}
		
		logger.traceExit(Arrays.toString(orders));
		
		return orders;
		
		
	}
	
	public static LineItem[] toLineItems(JSONArray jsonArray) {

		logger.entry(jsonArray);
		
		if (jsonArray==null) return null;
		
		LineItem[] lineItems= new LineItem[jsonArray.size()];
		
		for (int index=0; index<jsonArray.size(); index++)
		{
			LineItem lineItem = new LineItem();
			lineItem.fromJSON((JSONObject) jsonArray.get(index));
			lineItems[index]= lineItem;
		}
		
		logger.traceExit(Arrays.toString(lineItems));
		
		return lineItems;
	}
	

	public static Refund_Line_Item[] toRefundLineItems(JSONArray jsonArray)
	{
		logger.entry(jsonArray);
		
		if (jsonArray==null) return null;
		
		Refund_Line_Item[] refund_line_Items= new Refund_Line_Item[jsonArray.size()];
		
		for (int index=0; index<jsonArray.size(); index++)
		{
			Refund_Line_Item refund_line_Item = new Refund_Line_Item();
			refund_line_Item.fromJSON((JSONObject) jsonArray.get(index));
			refund_line_Items[index]= refund_line_Item;
		}
		
		logger.traceExit(Arrays.toString(refund_line_Items));
		
		return refund_line_Items;
	}


	public static JSONArray toJSON(Customer[] customer) {
		// TODO Auto-generated method stub
		return null;
	}


	public static JSONArray toJSON(ClientDetails[] client_details) {
		// TODO Auto-generated method stub
		return null;
	}


	public static JSONArray toJSON(String[] payment_gateway_names) {
		// TODO Auto-generated method stub
		return null;
	}


	public static ClientDetails[] toClientDetails(Object object) {
		// TODO Auto-generated method stub
		return null;
	}


	public static Customer[] toCustomers(Object object) {
		// TODO Auto-generated method stub
		return null;
	}


	public static FulFillment[] toFulfillments(Object object) {
		// TODO Auto-generated method stub
		return null;
	}


	public static String[] toGatewayName(Object object) {
		// TODO Auto-generated method stub
		return null;
	}


	public static ShippingAddress[] toShippingAddress(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}
}
