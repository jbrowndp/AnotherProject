/*
 * Created on Apr 15, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.datapak.billing.datamail.model;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.xinj.xbean.PrimaryKey;
import org.xinj.xbean.XBean;
import org.xinj.xbean.util.DatabaseUtil;
/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Company extends XBean {

		private String id;

		private String company_number;

		private String company_name;


		public Company(String id) throws SQLException {
			super(new PrimaryKey("id", id), "companies");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");
			this.init(conn);
			conn.close();
		}

		public Company(String id, Connection conn) throws SQLException {
			super(new PrimaryKey("id", id), "companies");
			this.init(conn);
		}

		public Company(ResultSet rs) throws SQLException {
			super(new PrimaryKey("id", rs.getString("id")), "companies");
			this.initFields(rs);
		}

		protected void initFields(ResultSet rs) throws SQLException {
			this.id = rs.getString("id");
			this.company_number = rs.getString("company_number");
			this.company_name = rs.getString("company_name");
		}

		public void setCompanyNumber(String company_number, Connection conn)
				throws SQLException {
			this.setField("company_number", XBean.TYPE_STRING, company_number, conn);
			this.company_number = company_number;
		}

		public void setCompanyName(String company_name, Connection conn)
				throws SQLException {
			this.setField("company_name", XBean.TYPE_STRING, company_name, conn);
			this.company_name = company_name;
		}



		public String getId() {

			return this.id;
		}

		public String getCompanyNumber() {

			return this.company_number;
		}

		public String getCompanyName() {

			return this.company_name;
		}



		public static Company newInstance(String id, Connection conn)
				throws SQLException {
			Company.insertNew(new PrimaryKey("id", id), "companies", conn);
			Company company = new Company(id, conn);
			return new Company(id, conn);
		}

		public void delete(Connection conn) throws SQLException {
			Company.delete(new PrimaryKey("id", this.getId()), "companies", conn);
		}

	}

