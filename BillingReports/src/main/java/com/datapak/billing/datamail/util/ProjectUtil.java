/*
 * Created on Apr 18, 2005
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

import com.datapak.billing.datamail.model.Project;

import java.util.*;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class ProjectUtil {

	public static List getProjectsForCompany(String comp_number, Connection conn) {

		List lt = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			String query = "select * from projects where company_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comp_number);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Project project = new Project(rs);
				lt.add(project);

			}

			rs.close();
			pstmt.close();

		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getProjects " + ex.getMessage());
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

	public static Project getProjectById(String id) {

		Project project = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");

			String query = "select * from projects where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				project = new Project(rs);

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
		return project;
	}

}