/*
 * Created on Apr 15, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.datapak.billing.datamail.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.xinj.xbean.util.DatabaseUtil;

import com.datapak.billing.datamail.model.Company;

import java.util.*;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class CompanyUtil {

	public static List getAllCompanies(Connection conn) {

		List lt = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			String query = "select * from companies";
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Company company = new Company(rs);
				lt.add(company);
			}

			rs.close();
			pstmt.close();

		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getAllCompanies " + ex.getMessage());
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

	public static Company getCompanyById(String id) {

		Company company = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");

			String query = "select * from companies where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				company = new Company(rs);

			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getCompany By Id "
					+ ex.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception excp) {
					System.out.println("unexpected exception " + excp);
				}
			}
		}
		return company;
	}

	public static Company getCompanyByCompanyNumber(String company_number) {

		Company company = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");

			String query = "select * from companies where company_number = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, company_number);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				company = new Company(rs);

			}

			rs.close();
			pstmt.close();

			conn.close();
		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getCompanyByCompanyNumber "
					+ ex.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception excp) {
					System.out.println("unexpected exception " + excp);
				}
			}
		}

		return company;

	}

}