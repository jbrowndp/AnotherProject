package com.datapak.shopify_java_api.model;

import java.util.Arrays;

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
	 * @param discount_codes array containing discount codes
	 * @return JSONArray containing discount codes in JSON format
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
	 * @param jsonArray  jsonArray containing discount codes in JSON format
	 * @return DiscountCode[] containing discount code
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
	 * @return JSONArray containing refunds in JSON format
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
	 *  @param jsonArray jsonArray containing refunds in JSON format
	 *  @return Refund[] containing refunds
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
	 * @param shippingLines array containing shippingLines
	 * @return JSONArray containing shippingLines
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
	 *  @param JSONArray containing shipping lines in JSON format
	 *  @return ShippingLine[] containing shipping lines.
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
	 * @param taxLines array containing taxlines
	 * @return JSONArray containing taxLines in JSON format
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
	 * @param jsonArray  array containing taxLines in JSON format
	 * @return TaxLine[] containing taxLines
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
	
	
	/** Convert from Fulfillment[] to JSONArray
	 * @param fulfillments array containing  fulfillments
	 * @return JSONArray containing fulfillments in JSON format
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

	/** Convert from JSONArray to FulFillment[]
	 * @param jsonArray array containing fulfillments in JSON format
	 * @return JSONArray containing fulfillments in JSON format
	 */
	public static FulFillment[] toFulfillments(JSONArray jsonArray) {
		
		logger.entry(jsonArray);
		if (jsonArray==null) return null;
		
		FulFillment[] fullfilments = new FulFillment[jsonArray.size()];
		
		for (int index=0; index<jsonArray.size(); index++)
		{
			FulFillment fulfillment = new FulFillment();
			fulfillment.fromJSON((JSONObject) jsonArray.get(index));
			fullfilments[index]= fulfillment;
		}
		
		logger.traceExit(Arrays.toString(fullfilments));
		
		return fullfilments;
		
	}
	
	
	/** Converts from LineItem[] into JSONArray
	 * 
	 * @param line_items array of line items
	 * @return JSONArray containing line items in JSON
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

	/** Converts from JSONArray to LineItem[]
	 * 
	 * @param jsonArray array of line items in JSON format
	 * @return LineItem[] containing line items
	 */
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
	
	
	/** Converts from PaymentDetail[] into JSONArray
	 * 
	 * @param payment_details array of payment details
	 * @return JSONArray containing payments details in JSON
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
	
	
	/** Converts from JSONArray into PaymentDetail[]
	 * 
	 * @param jsonArray array of payment details in JSON
	 * @return PaymentDetail[] containing payment details
	 */
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
	
	
	/** Converts from ShippingAddress[] into JSONArray
	 * 
	 * @param shippingAddresses array of shipping addresses
	 * @return JSONArray containing shipping addresses in JSON
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
	
	
	/** Converts from JSONArray into ShippingAddress[]
	 * 
	 * @param jsonArray array containing shipping addresses in JSON format
	 * @return ShippingAddress[] containing shipping addresses 
	 */
	public static ShippingAddress[] toShippingAddress(JSONArray jsonArray) {
		
		logger.entry(jsonArray);
		
		if (jsonArray==null) return null;
		
		ShippingAddress[] shippingAddresses= new ShippingAddress[jsonArray.size()];
		
		for (int index=0; index<jsonArray.size(); index++)
		{
			ShippingAddress shippingAddress = new ShippingAddress();
			shippingAddress.fromJSON((JSONObject) jsonArray.get(index));
			shippingAddresses[index]= shippingAddress;
		}
		
		logger.traceExit(Arrays.toString(shippingAddresses));
		
		return shippingAddresses;
	}
	
	
	/** Converts from Refund_Line_item[] into JSONArray
	 * 
	 * @param refund_line_item array of refund_line_items
	 * @return JSONArray containing refund_line_items in JSON
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
	
	/** Converts from JSONArray to Refund_Line_Item[]
	 * 
	 * @param jsonArray array of refund_line_items in JSON format
	 * @return JSONArray containing refund_line_items
	 */
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
	
	/** Converts from JSONArray to Transaction[]
	 * 
	 * @param transactions array of transactions
	 * @return JSONArray containing payments details in JSON
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

	/** Converts from Transaction[] into JSONArray
	 * 
	 * @param jsonArray array of transactions in JSON format
	 * @return Transaction[] containing transactions
	 */
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

	/** Converts from Order[] to JSONArray
	 * 
	 * @param orders array of orders
	 * @return JSONArray containing orders in JSON
	 */
	public static JSONArray toJSON(Order[] orders)
	{
		JSONArray order_array = new JSONArray();
		
		for (Order order: orders)
		{
				JSONObject orderObject = order.toJSON();
				order_array.add(orderObject);
			
		}
		
		return order_array;
	}
	
	/** Converts from JSONArray to Order[]
	 * 
	 * @param jsonArray array containing orders in JSON
	 * @return Order[] containing orders
	 */
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
	
	/** Converts from customer[] to JSONArray
	 * 
	 * @param customers array of customers
	 * @return JSONArray containing customers in JSON
	 */
	public static JSONArray toJSON(Customer[] customers) {
		JSONArray customer_array = new JSONArray();
		
		for (Customer customer: customers)
		{
				JSONObject customerObject = customer.toJSON();
				customer_array.add(customerObject);
			
		}
		
		return customer_array;
	}
	
	
	/** Converts from JSONArray to customer[]
	 * 
	 * @param jsonArray array containing customers in JSON
	 * @return customer[] containing customers
	 */
	public static Customer[] toCustomers(JSONArray jsonArray) {
	logger.entry(jsonArray);
		
		if (jsonArray==null) return null;
		
		Customer[] customers= new Customer[jsonArray.size()];
		
		for (int index=0; index<jsonArray.size(); index++)
		{
			Customer customer = new Customer();
			customer.fromJSON((JSONObject) jsonArray.get(index));
			customers[index]= customer;
		}
		
		logger.traceExit(Arrays.toString(customers));
		
		return customers;
	}
	
	/** Converts from ClientDetail[] to JSONArray
	 * 
	 * @param clientdetails array of client details
	 * @return JSONArray containing client details in JSON
	 */
	public static JSONArray toJSON(ClientDetails[] clientdetails) {
		JSONArray clientdetail_array = new JSONArray();
		
		for (ClientDetails clientdetail: clientdetails)
		{
				JSONObject clientdetailObject = clientdetail.toJSON();
				clientdetail_array.add(clientdetailObject);
			
		}
		
		return clientdetail_array;
	}
	
	
	/** Converts from JSONArray to ClientDetail[]
	 * 
	 * @param jsonArray array containing client details in JSON
	 * @return ClientDetail[] containing client details
	 */
	public static ClientDetails[] toClientDetails(JSONArray jsonArray) {
	logger.entry(jsonArray);
		
		if (jsonArray==null) return null;
		
		ClientDetails[] clientdetails= new ClientDetails[jsonArray.size()];
		
		for (int index=0; index<jsonArray.size(); index++)
		{
			ClientDetails clientdetail = new ClientDetails();
			clientdetail.fromJSON((JSONObject) jsonArray.get(index));
			clientdetails[index]= clientdetail;
		}
		
		logger.traceExit(Arrays.toString(clientdetails));
		
		return clientdetails;
	}




	
	



}
