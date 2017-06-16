/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.datapak.billing.datamail.util;

import java.sql.*;
import java.util.*;

import com.datapak.billing.datamail.model.Holiday;

import org.xinj.xbean.util.DatabaseUtil;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class ReportsPDFUtil {

	public synchronized static int getTotalNumberOfEmailForProject(
			String project_id, LocalDate start_date, LocalDate end_date, Connection conn) {

		int count = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			conn.setReadOnly(true);
			
			/*
			 * July 24, 2008 - kpb
			 * Making a change to this query statement.  If we are selecting the received_email_id's from replied emails,
			 * wouldn't this always equal the count of received_emails with those id's?  
			String query = "select count(*) from received_emails where id in (select received_email_id from replied_emails where date_replied >= ? and date_replied < ? and project_number = ?)";
			*/
			
			String query = "select count(*) from replied_emails where date_replied >= ? and date_replied < ? and project_number = ?";
			
			pstmt = conn.prepareStatement(query);

			pstmt.setTimestamp(1, Timestamp.valueOf(start_date.atStartOfDay()));

			pstmt.setTimestamp(2, Timestamp.valueOf(end_date.atStartOfDay()));

			pstmt.setString(3, project_id);

			rs = pstmt.executeQuery();
			conn.setReadOnly(false);
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getAnswers " + ex.getMessage());
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

		return count;

	}

	public synchronized static float getTotalAnswerTimeForProject2(
			String project_id, LocalDate start_date, LocalDate end_date, Connection conn) {

		float average = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			String query = "select t1.date_received, t2.date_replied, t1.id FROM received_emails t1, replied_emails t2 WHERE t1.id = t2.received_email_id and t2.date_replied >=  ? and t2.date_replied < ? and t2.project_number = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setTimestamp(1, Timestamp.valueOf(start_date.atStartOfDay()));

			pstmt.setTimestamp(2, Timestamp.valueOf(end_date.atStartOfDay()));

			pstmt.setString(3, project_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				long milliToHours = 1000 * 60 * 60;

				Date date_received = rs.getTimestamp(1);
				Date date_replied = rs.getTimestamp(2);
				long dif = countDifference(date_received.getTime(),
						date_replied.getTime(), conn);
				average += ((float) dif / (float) milliToHours);

			}

			rs.close();
			pstmt.close();

		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getAnswers " + ex.getMessage());
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

		return average;

	}

	public synchronized static long countDifference(long received, long replied, Connection conn) {

		long diff = 0;
		long millis_to_15_mins = 1000 * 60 * 15;
		while (received < replied) {
			if (!isHolidayDate(received, conn)) {
				diff += millis_to_15_mins;
			}
			received += millis_to_15_mins;
		}

		return diff;
	}

	public synchronized static boolean isHolidayDate(long received, Connection conn) {
		boolean flag = false;

		try {
			Date rec_date = new Date(received);
			String rec_date_s;
			SimpleDateFormat dFormat = new SimpleDateFormat("M-dd");
			rec_date_s = dFormat.format(rec_date);

			List holidays = HolidayUtil.getAllHolidays(conn);
			for (Iterator it = holidays.iterator(); it.hasNext();) {
				Holiday holiday = (Holiday) it.next();
				if (rec_date_s.equals(holiday.getHolidayDateParsed())) {

					flag = true;
					addToHolidayList(holiday, conn);
					break;

				}

			}

		} catch (Exception e) {
			System.out.println("problem with is holiday date " + e);
		}

		return flag;

	}

	public synchronized static List getCurrentHoldaysUsed(Connection conn){
		List lt = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{	
			String sql = "select * from holidays_used";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){	
				Holiday holiday = new Holiday(rs.getString("id"));
				lt.add(holiday);
			}
				
		} catch(Exception e){
			e.printStackTrace();
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
	
	public synchronized static void addToHolidayList(Holiday holiday, Connection conn) {

		boolean flag = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from holidays_used where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, holiday.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;

			}
			pstmt.close();
		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getAnswers " + ex.getMessage());
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

		if (!flag) {

			try {
				String sql = "insert into holidays_used set id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, holiday.getId());
				pstmt.executeUpdate();
			} catch (Exception ex) {
				DatabaseUtil.handleSQLException(conn, pstmt);
				System.err.println("SQLException: getAnswers "
						+ ex.getMessage());
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (Exception excp) {
						System.out.println("unexpected exception " + excp);
					}
				}
			}
		}
	}
	public synchronized static void clearHolidayList(Connection conn) {

		boolean flag = false;
		PreparedStatement pstmt = null;

		try {
			String sql = "delete from holidays_used";
			pstmt = conn.prepareStatement(sql);

			pstmt.execute();

			pstmt.close();
		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: clear holiday list " + ex.getMessage());
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception excp) {
					System.out.println("unexpected exception " + excp);
				}
			}
		}

		
	}
	public synchronized static float getTotalBillingTimeForProject(
			String project_id, LocalDate start_date, LocalDate end_date, Connection conn) {

		long time_spent = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		
			conn.setReadOnly(true);
			String query = "select t2.time_spent FROM received_emails t1, replied_emails t2 WHERE t1.id = t2.received_email_id and t2.date_replied >=  ? and t2.date_replied < ? and t2.project_number = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setTimestamp(1, Timestamp.valueOf(start_date.atStartOfDay()));

			pstmt.setTimestamp(2, Timestamp.valueOf(end_date.atStartOfDay()));

			pstmt.setString(3, project_id);

			rs = pstmt.executeQuery();
			conn.setReadOnly(false);
			while (rs.next()) {

				time_spent += rs.getInt(1);

			}

			rs.close();
			pstmt.close();

		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getAnswers " + ex.getMessage());
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

		float diff_min = 0;
		if (time_spent > 0) {
			String time_spent_s = String.valueOf(time_spent);
			float diff_sec = Float.parseFloat(time_spent_s) / 1000;
			diff_min = diff_sec / 60;

		}
		return diff_min;

	}

	public synchronized static int getOutstandingForStore(String store_id,
			LocalDate start_date, LocalDate end_date, Connection conn) {

		int count = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		
			conn.setReadOnly(true);
			String query = "select count(*) from received_emails where date_received < ? and store_id = ? and status = '1'";

			pstmt = conn.prepareStatement(query);

			pstmt.setTimestamp(1, Timestamp.valueOf(end_date.atStartOfDay()));

			pstmt.setString(2, store_id);

			rs = pstmt.executeQuery();
			conn.setReadOnly(false);
			if (rs.next()) {

				count = rs.getInt(1);

			}

			rs.close();
			pstmt.close();

		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getAnswers " + ex.getMessage());
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

		return count;

	}

	public synchronized static int getReceivedMessagesForStore(String store_id,
			LocalDate start_date, LocalDate end_date, Connection conn) {

		int count = 0;

		SimpleDateFormat dFormat = new SimpleDateFormat("M-dd-yyyy");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn.setReadOnly(true);
			String query = "select count(*) from received_emails where date_received >=  ? and date_received < ? and store_id = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setTimestamp(1, Timestamp.valueOf(start_date.atStartOfDay()));

			pstmt.setTimestamp(2, Timestamp.valueOf(end_date.atStartOfDay()));

			pstmt.setString(3, store_id);

			rs = pstmt.executeQuery();
			conn.setReadOnly(false);
			if (rs.next()) {

				count = rs.getInt(1);

			}

			rs.close();
			pstmt.close();

		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getAnswers " + ex.getMessage());
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

		return count;

	}

	public synchronized static int getTotalRepliedEmails(LocalDate start_date,
			LocalDate end_date, Connection conn) {

		int count = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn.setReadOnly(true);
			String query = "select count(*) FROM received_emails t1, replied_emails t2 WHERE t1.id = t2.received_email_id and t2.date_replied >=  ? and t2.date_replied < ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setTimestamp(1, Timestamp.valueOf(start_date.atStartOfDay()));

			pstmt.setTimestamp(2, Timestamp.valueOf(end_date.atStartOfDay()));

			rs = pstmt.executeQuery();
			conn.setReadOnly(false);
			if (rs.next()) {
				count += rs.getInt(1);
			}

			rs.close();
			pstmt.close();

		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getAnswers " + ex.getMessage());
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

		return count;

	}

	public synchronized static int getEmailsAnsweredIn8Hours(
			LocalDate start_date, LocalDate end_date, Connection conn) {

		int count = 0;
		int milliToDays = 1000 * 60 * 60 * 8;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		
			conn.setReadOnly(true);
			String query = "select t1.date_received, t2.date_replied FROM received_emails t1, replied_emails t2 WHERE t1.id = t2.received_email_id and t2.date_replied >=  ? and t2.date_replied < ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setTimestamp(1, Timestamp.valueOf(start_date.atStartOfDay()));

			pstmt.setTimestamp(2, Timestamp.valueOf(end_date.atStartOfDay()));

			rs = pstmt.executeQuery();
			conn.setReadOnly(false);
			while (rs.next()) {

				Date date_received = rs.getTimestamp(1);
				/*
				 * 3/5/2015
				 * If an email comes in after 8pm Mon - Fri set arrival time to next day at 8am
				 */
				Calendar calReceived = Calendar.getInstance();
				calReceived.setTimeInMillis(date_received.getTime());
				if (calReceived.get(Calendar.DAY_OF_WEEK) >= Calendar.MONDAY && calReceived.get(Calendar.DAY_OF_WEEK) <= Calendar.FRIDAY) {
					if (calReceived.get(Calendar.HOUR_OF_DAY) >= 20 && calReceived.get(Calendar.HOUR_OF_DAY) <= 23) {
						calReceived.add(Calendar.DAY_OF_MONTH, 1);
					}
					
					if (calReceived.get(Calendar.HOUR_OF_DAY) >= 0 && calReceived.get(Calendar.HOUR_OF_DAY) < 8) {
						if (calReceived.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
							calReceived.set(Calendar.HOUR_OF_DAY, 9);
						} else {
							calReceived.set(Calendar.HOUR_OF_DAY, 8);
						}
						calReceived.set(Calendar.MINUTE, 0);
						calReceived.set(Calendar.SECOND, 0);
					}
				}
				
				//If an email comes in on Saturday between 6:00pm - 11:59pm add a day
				if (calReceived.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
					if (calReceived.get(Calendar.HOUR_OF_DAY) >= 20 && calReceived.get(Calendar.HOUR_OF_DAY) <= 23) {
						calReceived.add(Calendar.DAY_OF_MONTH, 1);
					}
				}
				
				//If the email came on a Sunday we need to set it to Monday at 8am.
				if (calReceived.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					calReceived.add(Calendar.DAY_OF_MONTH, 1);
					calReceived.set(Calendar.HOUR_OF_DAY, 8);
					calReceived.set(Calendar.MINUTE, 0);
					calReceived.set(Calendar.SECOND, 0);
				}
				
				Date date_replied = rs.getTimestamp(2);
			
				long dif = countDifference(calReceived.getTimeInMillis(), date_replied.getTime(), conn);
				if (dif <= milliToDays) {
					count++;
				}
			}
			rs.close();
			pstmt.close();

		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getAnswers " + ex.getMessage());
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

		return count;
	}

	public synchronized static int getEmailsByReasonId(String reason_id,
			LocalDate start_date, LocalDate end_date, Connection conn) {

		int count = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn.setReadOnly(true);
			String query = "select count(*) FROM received_emails t1, replied_emails t2 WHERE t1.id = t2.received_email_id and t2.date_replied >=  ? and t2.date_replied < ? and t2.reason_id = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setTimestamp(1, Timestamp.valueOf(start_date.atStartOfDay()));

			pstmt.setTimestamp(2, Timestamp.valueOf(end_date.atStartOfDay()));

			pstmt.setString(3, reason_id);
			rs = pstmt.executeQuery();
			conn.setReadOnly(false);
			if (rs.next()) {
				count = rs.getInt(1);
			}

			rs.close();
			pstmt.close();

		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getAnswers " + ex.getMessage());
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

		return count;
	}

	public synchronized static int getEmailsByUserId(String user_id,
			LocalDate start_date, LocalDate end_date, Connection conn) {

		int count = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn.setReadOnly(true);
			String query = "select count(*) FROM received_emails t1, replied_emails t2 WHERE t1.id = t2.received_email_id and t2.date_replied >=  ? and t2.date_replied < ? and t2.user_id = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setTimestamp(1, Timestamp.valueOf(start_date.atStartOfDay()));

			pstmt.setTimestamp(2, Timestamp.valueOf(end_date.atStartOfDay()));

			pstmt.setString(3, user_id);
			rs = pstmt.executeQuery();
			conn.setReadOnly(false);
			if (rs.next()) {

				count = rs.getInt(1);

			}

			rs.close();
			pstmt.close();

		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			System.err.println("SQLException: getAnswers " + ex.getMessage());
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

		return count;
	}
}