package com.datapak.shopify_java_api.model;

import org.json.simple.JSONObject;

/** 
 * Represents a BillingAddress 
 * 
 * The mailing address associated with the payment method. This address is an optional field that will not be
 * be available on orders that do not require one. 
 * 
 * @author jbrown
 * @version 1.0
 *
 */
public class BillingAddress extends ShopifyObject {

	
	private String address1;
	private String address2;
	private String city;
	private String company;
	private String country;
	private String first_name;
	private Long id;
	private String last_name;
	private String phone;
	private String province;
	private String zip;
	private String name;
	private String province_code;
	private String country_code;
	private Boolean is_default;
	
	
	/** 
	 * Gets the street address of the billing address
	 * @return A string containing the street address of the billing address
	 */
	public String getAddress1() {
		return address1;
	}
	
	/**
	 * Sets the street address of the billing address
	 *
	 * @param address1 A string containing the street address of the billing address.
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	/** 
	 * Gets the optional additional field for the street address of the billing address.
	 * @return A string containing the optional additional field for the street address of the billing address
	 */
	public String getAddress2() {
		return address2;
	}
	
	/**
	 * Sets the optional additional field for the street address of the billing address.
	 *
	 * @param address2 A string containing the street address of the billing address.
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	/** 
	 * Sets the city of the billing address.
	 * 
	 * @return A string containing the city of the billing address
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city of the billing address.
	 *
	 * @param city A string containing the city of the billing address
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	
	/** Gets the company of the person associated with the billing address.
	 * 
	 * @return A String containing the company of the person associated with the billing address.
	 */
	public String getCompany() {
		return company;
	}
	
	/** Sets the company of the person associated with the billing address.
	 * 
	 * @param company A String containing the company of the person associated with the billing address.
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	/** Gets the name of the country of the billing address
	 * 
	 * @return A String containing the name of the country of the billing address.
	 */
	public String getCountry() {
		return country;
	}
	
	/** Sets the name of the country of the billing address
	 * 
	 * @param country A String containing the name of the country of the billing address.
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/** Gets the first name of the person associated with the payment method.
	 * 
	 * @return A String object containing the first name of the person associated with the payment method.
	 */
	public String getFirst_name() {
		return first_name;
	}
	
	
	/** Sets the first name of the person associated with the payment method.
	 * 
	 * @param first_name A String object containing the first name of the person associated with the payment method.
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	
	/** Gets the id of the billing address
	 * 
	 * @return A Long object containing the id of the billing address.
	 */
	public Long getId() {
		return id;
	}
	
	/** Sets the id of the billing address 
	 * 
	 * @param id A Long object containing the id of the billing address.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/** Get the last name of the person associated with the payment method 
	 * 
	 * @return A String object containing the last name of the person associated with the payment method.
	 */
	public String getLast_name() {
		return last_name;
	}
	
	
	/** Sets the last name of the person associated with the payment method.
	 * 
	 * @param last_name A String object containing the last name of the person associated with the payment method.
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	/** Gets the phone number of the billing address 
	 * 
	 * @return A String object containing the phone number of the billing address.
	 */
	public String getPhone() {
		return phone;
	}
	
	/** Sets the phone number of the billing address
	 * 
	 * @param phone A String object containing the phone number of the billing address.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	/** Gets the name of the state or province of the billing address 
	 * 
	 * @return A string containing the name of the state or province of the billing address.
	 */
	public String getProvince() {
		return province;
	}
	
	/** Sets the name of the state or province of the billing address
	 * 
	 * @param province A String containing the name of the state or province of the billing address.
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	
	/** Gets the zip or postal code of the billing address 
	 * 
	 * @return A String containing the zip or postal code of the billing address.
	 */
	public String getZip() {
		return zip;
	}
	
	/** Sets the zip or postal code of the billing address.
	 * 
	 * @param zip A String containing the zip or postal code of the billing address.
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	/** Gets the full name of the person associated with the payment method.
	 * 
	 * @return A string containing the full name of the person associated with the payment method.
	 */
	public String getName() {
		return name;
	}
	
	/** Sets the full name of the person associated with the payment method.
	 * 
	 * @param name A String containing the full name of the person associated with the payment method.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/** Gets the two-letter abbreviation of the state or province of the billing address.
	 * 
	 * @return A String containing the two-letter abbreviation of the state or province of the billing address.
	 */
	public String getProvince_code() {
		return province_code;
	}
	
	/** Sets the two-letter abbreviation of the state or province of the billing address.
	 * 
	 * @param province_code A String containing the two-letter abbreviation of the state of province of the billing address.
	 */
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	
	
	/** Gets the two -letter code(ISO 3166-2 alpha-2 two-letter country code)
	 *  for the country of the billing address. 
	 * @return A String containing the two-letter code(ISO 3166-2 alpha-2 two-letter country code)
	 *  for the country of the billing address. 
	 */
	public String getCountry_code() {
		return country_code;
	}
	
	/** Gets the two -letter code(ISO 3166-2 alpha-2 two-letter country code)
	 *  for the country of the billing address. 
	 * @param country_code A String containing the two-letter code(ISO 3166-2 alpha-2 two-letter country code)
	 *  for the country of the billing address. 
	 */
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	
	/** Gets whether billing address is default
	 * 
	 * @return true or false
	 */
	public Boolean isIs_default() {
		return is_default;
	}
	
	/** Sets whether billing address is default 
	 * 
	 * @param is_default   true or false (Set by Shopify)
	 */
	public void setIs_default(Boolean is_default) {
		this.is_default = is_default;
	}
	
	/** @see ShopifyObject
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {

		JSONObject obj = new JSONObject();
		
		obj.put("address1", address1);
		obj.put("address2", address2);
		obj.put("city", city);
		obj.put("company", company);
		obj.put("country", country);
		obj.put("first_name", first_name);
		obj.put("id", id);
		obj.put("last_name", last_name);
		obj.put("phone", phone);
		obj.put("province", province);
		obj.put("zip", zip);
		obj.put("name", name);
		obj.put("province_code", province_code);
		obj.put("country_code", country_code);
		obj.put("default", is_default);
				
		return obj;
	}
	
	/** @see ShopifyObject
	 */
	@Override
	public void fromJSON(JSONObject json) {
		
		if (json==null) { return; }
		
		this.setAddress1((String) json.get("address1"));
		this.setAddress2((String) json.get("address2"));
		this.setCity((String) json.get("city"));
		this.setCompany((String) json.get("company"));
		this.setCountry((String) json.get("country"));
		this.setFirst_name((String) json.get("first_name"));
		this.setId((Long) json.get("id"));
		this.setLast_name((String) json.get("last_name"));
		this.setPhone((String) json.get("phone"));
		this.setProvince((String) json.get("province"));
		this.setZip((String) json.get("zip"));
		this.setName((String) json.get("name"));
		this.setProvince_code((String) json.get("provice_code"));
		this.setCountry_code((String) json.get("country_code"));
		this.setIs_default((Boolean) json.get("default"));
		

		
	}

	@Override
	public String toString() {
		return "BillingAddress [address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", company="
				+ company + ", country=" + country + ", first_name=" + first_name + ", id=" + id + ", last_name="
				+ last_name + ", phone=" + phone + ", province=" + province + ", zip=" + zip + ", name=" + name
				+ ", province_code=" + province_code + ", country_code=" + country_code + ", is_default=" + is_default
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
		result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((country_code == null) ? 0 : country_code.hashCode());
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((is_default == null) ? 0 : is_default.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((province_code == null) ? 0 : province_code.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillingAddress other = (BillingAddress) obj;
		if (address1 == null) {
			if (other.address1 != null)
				return false;
		} else if (!address1.equals(other.address1))
			return false;
		if (address2 == null) {
			if (other.address2 != null)
				return false;
		} else if (!address2.equals(other.address2))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (country_code == null) {
			if (other.country_code != null)
				return false;
		} else if (!country_code.equals(other.country_code))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (is_default == null) {
			if (other.is_default != null)
				return false;
		} else if (!is_default.equals(other.is_default))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (province_code == null) {
			if (other.province_code != null)
				return false;
		} else if (!province_code.equals(other.province_code))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}
	
	
	
	
}
