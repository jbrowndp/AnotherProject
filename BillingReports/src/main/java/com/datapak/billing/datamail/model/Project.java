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
public class Project extends XBean {

	private String id;

	private String company_id;

	private String project_number;

	private String project_name;

	private String email;

	private String bcc;

	private String default_from;

	private String billing_number;

	public Project(String id) throws SQLException {
		super(new PrimaryKey("id", id), "projects");
		DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");
		this.init(conn);
		conn.close();
	}

	public Project(String id, Connection conn) throws SQLException {
		super(new PrimaryKey("id", id), "projects");
		this.init(conn);
	}

	public Project(ResultSet rs) throws SQLException {
		super(new PrimaryKey("id", rs.getString("id")), "projects");
		this.initFields(rs);
	}

	protected void initFields(ResultSet rs) throws SQLException {
		this.id = rs.getString("id");
		this.company_id = rs.getString("company_id");
		this.project_number = rs.getString("project_number");
		this.project_name = rs.getString("project_name");
		this.email = rs.getString("email");
		this.bcc = rs.getString("bcc");
		this.default_from = rs.getString("default_from");
		this.billing_number = rs.getString("billing_number");
	}

	public void setCompanyId(String company_id, Connection conn)
			throws SQLException {
		this.setField("company_id", XBean.TYPE_STRING, company_id, conn);
		this.company_id = company_id;
	}

	public void setProjectNumber(String project_number, Connection conn)
			throws SQLException {
		this
				.setField("project_number", XBean.TYPE_STRING, project_number,
						conn);
		this.project_number = project_number;
	}

	public void setEmail(String email, Connection conn) throws SQLException {
		this.setField("email", XBean.TYPE_STRING, email, conn);
		this.email = email;
	}

	public void setBcc(String bcc, Connection conn) throws SQLException {
		this.setField("bcc", XBean.TYPE_STRING, bcc, conn);
		this.bcc = bcc;
	}

	public void setProjectName(String project_name, Connection conn)
			throws SQLException {
		this.setField("project_name", XBean.TYPE_STRING, project_name, conn);
		this.project_number = project_name;
	}

	public void setDefaultFrom(String default_from, Connection conn)
			throws SQLException {
		this.setField("default_from", XBean.TYPE_STRING, default_from, conn);
		this.default_from = default_from;
	}

	public void setBillingNumber(String billing_number, Connection conn)
			throws SQLException {
		this
				.setField("billing_number", XBean.TYPE_STRING, billing_number,
						conn);
		this.billing_number = billing_number;
	}

	public String getId() {

		return this.id;
	}

	public String getCompanyId() {

		return this.company_id;
	}

	public String getProjectNumber() {

		return this.project_number;
	}

	public String getProjectName() {

		return this.project_name;
	}

	public String getEmail() {

		return this.email;
	}

	public String getBcc() {

		return this.bcc;
	}

	public String getDefaultFrom() {

		return this.default_from;
	}
	public String getBillingNumber(){
		
		return this.billing_number;
	}

	public static Project newInstance(String id, Connection conn)
			throws SQLException {
		Project.insertNew(new PrimaryKey("id", id), "projects", conn);
		Project project = new Project(id, conn);
		return new Project(id, conn);
	}

	public void delete(Connection conn) throws SQLException {
		Company.delete(new PrimaryKey("id", this.getId()), "projects", conn);
	}

}

