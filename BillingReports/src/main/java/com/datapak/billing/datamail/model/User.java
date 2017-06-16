package com.datapak.billing.datamail.model;

import org.xinj.xbean.XBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.xinj.xbean.PrimaryKey;


public class User extends XBean {

	private String id;
	
	private String agent_id;

	private String name;

	private String access_code;

	private String password;

	private int grab_number;

	private String is_admin;

	private String is_moderator;

	public User(String id) throws SQLException {
		super(new PrimaryKey("id", id), "users");
		DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");
		this.init(conn);
		conn.close();
	}

	public User(String id, Connection conn) throws SQLException {
		super(new PrimaryKey("id", id), "users");
		this.init(conn);
	}

	public User(ResultSet rs) throws SQLException {
		super(new PrimaryKey("id", rs.getString("id")), "users");
		this.initFields(rs);
	}

	protected void initFields(ResultSet rs) throws SQLException {
		this.id = rs.getString("id");
		this.agent_id = rs.getString("agent_id");
		this.name = rs.getString("name");
		this.access_code = rs.getString("access_code");
		this.password = rs.getString("password");
		this.grab_number = rs.getInt("grab_number");
		this.is_admin = rs.getString("is_admin");
		this.is_moderator = rs.getString("is_moderator");

	}

	public void setName(String name, Connection conn) throws SQLException {
		this.setField("name", XBean.TYPE_STRING, name, conn);
		this.name = name;
	}

	public void setAgentId(String agent_id, Connection conn) throws SQLException {
		this.setField("agent_id", XBean.TYPE_STRING, agent_id, conn);
		this.agent_id = agent_id;
	}
	
	public void setAccessCode(String access_code, Connection conn)
			throws SQLException {
		this.setField("access_code", XBean.TYPE_STRING, access_code, conn);
		this.access_code = access_code;
	}

	public void setPassword(String password, Connection conn)
			throws SQLException {
		this.setField("password", XBean.TYPE_STRING, password, conn);
		this.password = password;
	}

	public void setGrabNumber(int grab_number, Connection conn)
			throws SQLException {
		this.setField("grab_number", XBean.TYPE_INT, new Integer(grab_number),
				conn);
		this.grab_number = grab_number;
	}

	public void setIsAdmin(String is_admin, Connection conn)
			throws SQLException {
		this.setField("is_admin", XBean.TYPE_STRING, is_admin, conn);
		this.is_admin = is_admin;
	}

	public void setIsModerator(String is_moderator, Connection conn)
			throws SQLException {
		this.setField("is_moderator", XBean.TYPE_STRING, is_moderator, conn);
		this.is_moderator = is_moderator;
	}

	public String getId() {
		return this.id;
	}
	
	public String getAgentId(){
		return this.agent_id;
	}

	public String getName() {
		return this.name;
	}

	public String getAccessCode() {
		return this.access_code;
	}

	public String getPassword() {
		return this.password;
	}

	public int getGrabNumber() {

		return this.grab_number;
	}

	public boolean isAdmin() {
		if ("Y".equals(this.is_admin) || "y".equals(this.is_admin)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isModerator() {
		if ("Y".equals(this.is_moderator) || "y".equals(this.is_moderator)) {
			return true;
		} else {
			return false;
		}
	}

	public static User newInstance(String id, Connection conn)
			throws SQLException {
		User.insertNew(new PrimaryKey("id", id), "users", conn);
		return new User(id, conn);
	}

}