/*
 * Created on Apr 15, 2005
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
public class Reason extends XBean {

	private String id;

	private String reason_id;

	private String reason;

	public Reason(String id) throws SQLException {
		super(new PrimaryKey("id", id), "reasons");
		DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");
		this.init(conn);
		conn.close();
	}

	public Reason(String id, Connection conn) throws SQLException {
		super(new PrimaryKey("id", id), "reasons");
		this.init(conn);
	}

	public Reason(ResultSet rs) throws SQLException {
		super(new PrimaryKey("id", rs.getString("id")), "reasons");
		this.initFields(rs);
	}

	protected void initFields(ResultSet rs) throws SQLException {
		this.id = rs.getString("id");
		this.reason_id = rs.getString("reason_id");
		this.reason = rs.getString("reason");

	}

	public void setReasonId(String reason_id, Connection conn)
			throws SQLException {
		this.setField("reason_id", XBean.TYPE_STRING, reason_id, conn);
		this.reason_id = reason_id;
	}

	public void setReason(String reason, Connection conn) throws SQLException {
		this.setField("reason", XBean.TYPE_STRING, reason, conn);
		this.reason = reason;
	}

	public String getId() {

		return this.id;
	}

	public String getReasonId() {

		return this.reason_id;
	}

	public String getReason() {

		return this.reason;
	}

	public static Reason newInstance(String id, Connection conn)
			throws SQLException {
		Reason.insertNew(new PrimaryKey("id", id), "reasons", conn);
		Reason reason = new Reason(id, conn);
		return new Reason(id, conn);
	}

	public void delete(Connection conn) throws SQLException {
		Reason.delete(new PrimaryKey("id", this.getId()), "reasons", conn);
	}

}