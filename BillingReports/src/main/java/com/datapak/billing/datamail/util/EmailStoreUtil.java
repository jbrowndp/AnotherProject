/*
 * Created on Apr 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.datapak.billing.datamail.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import org.xinj.xbean.util.DatabaseUtil;

import com.datapak.billing.datamail.model.EmailStore;


/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class EmailStoreUtil {

  public static final String[] be_stores = { "1", "2", "3", "6", "21", "22", "29" };

	public static List getAllStores(Connection conn) {

		List lt = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			String query = "select * from stores order by store_name";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmailStore store = new EmailStore(rs);
				lt.add(store);
			}
			rs.close();
			pstmt.close();

		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.out.println("SQLException: getAllStores " + ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception excp) {
					System.out.println("unexpected exception " + excp);
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception excp) {
					System.out.println("unexpected exception " + excp);
				}
			}
		}
		return lt;
	}

	public static boolean isBEStore(String id) {
		boolean flag = false;
		for (int i = 0; i < be_stores.length; i++) {
			String store_id = be_stores[i];
			if (id.equals(store_id)) {
				flag = true;
				break;
			}
		}
		return flag;

	}

	public static List getAllBEsStores() {

		List lt = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");

			String query = "select * from stores";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmailStore store = new EmailStore(rs);
				if (isBEStore(store.getId())) {
					lt.add(store);
				}
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.out.println("SQLException: getAllBEStores " + ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception excp) {
					System.out.println("unexpected exception " + excp);
				}
			}
		}
		return lt;
	}

	public static List getAllUsersStores(String user_id) {

		List lt = new ArrayList();
		List matches = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");

			String query = "select store_id from users_stores where user_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				matches.add(rs.getString("store_id"));
			}

			rs.close();
			pstmt.close();

			conn.close();
		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.out.println("SQLException: getAllUsersStores " + ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception excp) {
					System.out.println("unexpected exception " + excp);
				}
			}
		}
		if (!matches.isEmpty()) {
			lt = getStoresById(matches);
		}
		return lt;

	}

	public static String getStoreName(String store_id) {

		String s = "N/A";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");

			String query = "select * from stores where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, store_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				s = rs.getString("store_name");
			}

			rs.close();
			pstmt.close();

			conn.close();
		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err
					.println("SQLException: getStoresById " + ex.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception excp) {
					System.out.println("unexpected exception " + excp);
				}
			}
		}

		return s;

	}

	public static List getStoresById(List matches) {

		List lt = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");
			for (Iterator it = matches.iterator(); it.hasNext();) {
				String store_id = (String) it.next();
				String query = "select * from stores where id = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, store_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					EmailStore store = new EmailStore(rs);
					lt.add(store);
				}
			}
			rs.close();
			pstmt.close();

			conn.close();
		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err
					.println("SQLException: getStoresById " + ex.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception excp) {
					System.out.println("unexpected exception " + excp);
				}
			}
		}

		return lt;

	}

}