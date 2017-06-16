/*
 * Created on Apr 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.datapak.billing.datamail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.xinj.xbean.PrimaryKey;
import org.xinj.xbean.XBean;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EmailStore extends XBean {

	private String id;

	private String store_name;

	private String server_name;

	private String user_name;

	private String password;

	private String address;
	
	private String outgoingServer;
	
	private String smtpAuth;

	public EmailStore(String id) throws SQLException {
		super(new PrimaryKey("id", id), "stores");
		DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");
		this.init(conn);
		conn.close();
	}

	public EmailStore(String id, Connection conn) throws SQLException {
		super(new PrimaryKey("id", id), "stores");
		this.init(conn);
	}

	public EmailStore(ResultSet rs) throws SQLException {
		super(new PrimaryKey("id", rs.getString("id")), "stores");
		this.initFields(rs);
	}

	protected void initFields(ResultSet rs) throws SQLException {
		this.id = rs.getString("id");
		this.store_name = rs.getString("store_name");
		this.server_name = rs.getString("server_name");
		this.user_name = rs.getString("user_name");
		this.password = rs.getString("password");
		this.address = rs.getString("address");
		this.outgoingServer = rs.getString("outgoing_server");
		this.smtpAuth = rs.getString("smtp_auth");

	}

	public void setStoreName(String store_name, Connection conn)
			throws SQLException {
		this.setField("store_name", XBean.TYPE_STRING, store_name, conn);
		this.store_name = store_name;
	}

	public void setServerName(String server_name, Connection conn)
			throws SQLException {
		this.setField("server_name", XBean.TYPE_STRING, server_name, conn);
		this.server_name = server_name;
	}

	public void setUserName(String user_name, Connection conn)
			throws SQLException {
		this.setField("user_name", XBean.TYPE_STRING, user_name, conn);
		this.user_name = user_name;
	}

	public void setPassword(String password, Connection conn)
			throws SQLException {
		this.setField("password", XBean.TYPE_STRING, password, conn);
		this.password = password;
	}

	public void setAddress(String address, Connection conn) throws SQLException {
		this.setField("address", XBean.TYPE_STRING, address, conn);
		this.address = address;
	}
	
	public void setOutgoingServer(String outgoingServer, Connection conn) throws SQLException {
		this.setField("outgoing_server", XBean.TYPE_STRING, outgoingServer, conn);
		this.outgoingServer = outgoingServer;
	}
	
	public void setSmtpAuth(String smtpAuth, Connection conn) throws SQLException {
		this.setField("outgoing_server", XBean.TYPE_STRING, smtpAuth, conn);
		this.smtpAuth = smtpAuth;
	}

	public String getId() {

		return this.id;
	}

	public String getStoreName() {

		return this.store_name;
	}

	public String getServerName() {

		return this.server_name;
	}

	public String getUserName() {

		return this.user_name;
	}

	public String getPassword() {

		return this.password;
	}
	
	public String getAddress(){
		
		return this.address;
	}
	
	public String getOutgoingServer() {
		return this.outgoingServer;
	}
	
	public String getSmtpAuth() {
		return this.smtpAuth;
	}

	public static EmailStore newInstance(String id, Connection conn)
			throws SQLException {
		EmailStore.insertNew(new PrimaryKey("id", id), "stores", conn);
		return new EmailStore(id, conn);
	}

	public void delete(Connection conn) throws SQLException {
		EmailStore.delete(new PrimaryKey("id", this.getId()), "stores", conn);
	}

}