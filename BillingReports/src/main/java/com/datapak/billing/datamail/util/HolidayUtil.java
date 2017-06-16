package com.datapak.billing.datamail.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.xinj.xbean.util.DatabaseUtil;

import com.datapak.billing.datamail.model.Holiday;

public class HolidayUtil {


	public static List getAllHolidays(Connection conn){
	
		
		List lt = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String query = "select * from holidays";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Holiday holiday = new Holiday(rs);
				lt.add(holiday);
			}

			rs.close();
			pstmt.close();

		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("Exception: getAllHolidays " + ex.getMessage());
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

}
