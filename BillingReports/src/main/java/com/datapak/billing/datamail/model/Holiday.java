package com.datapak.billing.datamail.model;

import org.xinj.xbean.XBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.xinj.xbean.PrimaryKey;

public class Holiday extends XBean {

	private String id;

	private String name;

	private Date holiday_date;


	public Holiday(String id) throws SQLException {
		super(new PrimaryKey("id", id), "holidays");
		DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");
		this.init(conn);
		conn.close();
	}

	public Holiday(String id, Connection conn) throws SQLException {
		super(new PrimaryKey("id", id), "holidays");
		this.init(conn);
	}

	public Holiday(ResultSet rs) throws SQLException {
		super(new PrimaryKey("id", rs.getString("id")), "holidays");
		this.initFields(rs);
	}

	protected void initFields(ResultSet rs) throws SQLException {
		this.id = rs.getString("id");
		this.name = rs.getString("name");
		this.holiday_date = rs.getTimestamp("holiday_date");

	}

	public void setName(String name, Connection conn) throws SQLException {
		this.setField("name", XBean.TYPE_STRING, name, conn);
		this.name = name;
	}

	public void setHolidayDate(Date holiday_date, Connection conn)
			throws SQLException {
		this.setField("holiday_date", XBean.TYPE_DATE, holiday_date, conn);
		this.holiday_date = holiday_date;
	}



	public String getId() {
		return this.id;

	}

	public String getName() {

		return this.name;
	}

	public Date getHolidayDate() {
		return this.holiday_date;
	}
	
	public String getHolidayDateParsed() {
		String s = "";
		try {

			SimpleDateFormat simpledateformat = new SimpleDateFormat("M-dd");
			s = simpledateformat.format(this.holiday_date);

		} catch (Exception e) {
			System.out.println("problem with parse holiday date " + e);
		}
		return s;

	}


	public static Holiday newInstance(String id, Connection conn)
			throws SQLException {
		Holiday.insertNew(new PrimaryKey("id", id), "holidays", conn);
		return new Holiday(id, conn);
	}

	public void delete(Connection conn) throws SQLException {
		Holiday.delete(new PrimaryKey("id", this.getId()), "holidays", conn);
	}

}