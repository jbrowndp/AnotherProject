/*
 * Created on May 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.datapak.billing.datamail;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.activation.*;

import com.datapak.billing.datamail.model.Company;
import com.datapak.billing.datamail.model.EmailStore;
import com.datapak.billing.datamail.model.Holiday;
import com.datapak.billing.datamail.model.Project;
import com.datapak.billing.datamail.model.Reason;
import com.datapak.billing.datamail.model.User;
import com.datapak.billing.datamail.util.CompanyUtil;
import com.datapak.billing.datamail.util.EmailStoreUtil;
import com.datapak.billing.datamail.util.ProjectUtil;
import com.datapak.billing.datamail.util.ReasonUtil;
import com.datapak.billing.datamail.util.ReportsPDFUtil;
import com.datapak.billing.datamail.util.UserUtil;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.sax.SAXResult;
import org.apache.fop.apps.Driver;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import javax.mail.*;
import org.xinj.xbean.util.DatabaseUtil;
import javax.mail.internet.*;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DataMailReport {

//	private static String xmlfile_s = "/home/kdorf/javaPrograms/datamail/xml/report.xml";
//
//	private static String xsltfile_s = "/home/kdorf/javaPrograms/datamail/xml/report.xsl";
//
//	private static String pdffile_s = "/home/kdorf/javaPrograms/datamail/xml/report.pdf";
	
	private static String xmlfile_s = "C:/Users/jbrown/test/datamail/xml/report.xml";

	private static String xsltfile_s = "C:/Users/jbrown/test/datamail/xml/report.xsl";

	private static String pdffile_s = "C:/Users/jbrown/test/datamail/xml/report.pdf";
	
	private static Hashtable<String, String> BILLING_REPORT = new Hashtable<String,String>();
	
	private static String dataBaseURL = "jdbc:mysql://192.168.149.234:3306/datamail";
	
	private static String dataBaseUser = "root";
	
	private static String dataBasePassword = "pa55wd4MYSQLprod";
		
	
	
	public DataMailReport()
	{

		Connection conn = null;
		
		try {
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			conn = DriverManager.getConnection(dataBaseURL,dataBaseUser,dataBasePassword);

			LocalDate today = LocalDate.now();
			LocalDate start_date = today.minusDays(today.getDayOfWeek().getValue());
			LocalDate end_date = start_date.plusDays(7);
			
			makeXML(today, start_date, end_date, conn);
			writeFile();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ignore) {}
			}
		}

		System.out.println("reports done");
	}

	
	
	public static void writeFile() {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(new File("DATAMAIL_BILLING.TXT")), true);
			Enumeration<String> keys = BILLING_REPORT.keys();
			StringBuffer output = new StringBuffer();
			while (keys.hasMoreElements()) {
				String jobNum = keys.nextElement();
				String mins = BILLING_REPORT.get(jobNum);
				output.append(jobNum);
				output.append("\t");
				output.append(mins);
				output.append("\n");
			}
			out.print(output.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception ignore) {}
			}
		}
	}

	public static void makeXML(LocalDate today, LocalDate start_date, LocalDate end_date, Connection conn) {
		

			System.out.println("start " + new Date());
	
			Document reqDoc = new Document();
	
			// create root element
			Element root = new Element("report");
			reqDoc.setRootElement(root);
	
			DateTimeFormatter dFormat = DateTimeFormatter.ofPattern("M-dd-yyyy");
	
			String s_today = today.format(dFormat);
	
			// date
			Element date = new Element("date");
			date.setText(s_today);
			root.addContent(date);
	
			String s_start_date = start_date.format(dFormat);
	
			// start-date
			Element startdate = new Element("startdate");
			startdate.setText(s_start_date);
			root.addContent(startdate);
	
			String s_end_date = dFormat.format(end_date);
	
			// enddate
			Element enddate = new Element("enddate");
			enddate.setText(s_end_date);
			root.addContent(enddate);
	
			// projectheader
			Element projectreportheader = new Element("projectreport-header");
			root.addContent(projectreportheader);
	
			DecimalFormat df = new DecimalFormat("#######0.00");
			DecimalFormat dp = new DecimalFormat("########0.00%");
			
			List all_companies = CompanyUtil.getAllCompanies(conn);
			List all_store = EmailStoreUtil.getAllStores(conn);
			List all_reasons = ReasonUtil.getAllReasons(conn);
			List all_users = UserUtil.getAllUsers(conn);
	
			int total_emails = 0;
			float total_answertime = 0;
			int count = 0;
			float total_time = 0;
			for (Iterator it = all_companies.iterator(); it.hasNext();) {
				Company this_company = (Company) it.next();
				List companies_projects = ProjectUtil.getProjectsForCompany(this_company.getId(), conn);
				
				for (Iterator iit = companies_projects.iterator(); iit.hasNext();) {
					
					Project this_project = (Project) iit.next();
					
					System.out.println("Going through projects - " + this_project.getProjectName());
	
					/* July 24, 2008 - kpb
					 * Changing this to speed up report time
					 
					total_emails += ReportsPDFUtil.getTotalNumberOfEmailForProject(this_project.getId(), start_date, end_date);
					total_answertime += ReportsPDFUtil.getTotalAnswerTimeForProject2(this_project.getId(),start_date, end_date);
					total_time += ReportsPDFUtil.getTotalBillingTimeForProject(this_project.getId(), start_date, end_date);
					*/
					
					System.out.println("Getting number of emails");
					int emails = ReportsPDFUtil.getTotalNumberOfEmailForProject(this_project.getId(), start_date, end_date, conn);
					
					System.out.println("Getting total answer time");
					float anstime =  ReportsPDFUtil.getTotalAnswerTimeForProject2(this_project.getId(),start_date, end_date, conn);
					
					System.out.println("Getting total time billed");
					float time = ReportsPDFUtil.getTotalBillingTimeForProject(this_project.getId(), start_date, end_date, conn);
					
					total_emails += emails;
					total_answertime += anstime;
					total_time += time;
					
					count++;
					
					Element projectreport = new Element("projectreport");
					Element job = new Element("job");
					job.setText(this_project.getBillingNumber());
					projectreport.addContent(job);
					Element project = new Element("project");
					project.setText(this_project.getProjectName());
					projectreport.addContent(project);
					Element numberofemails = new Element("numberofemails");
	
					numberofemails.setText(String.valueOf(emails));
					projectreport.addContent(numberofemails);
					Element answertime = new Element("answertime");
					answertime.setText(String.valueOf(df.format(anstime)));
					projectreport.addContent(answertime);
					Element billingtime = new Element("billingtime");
					billingtime.setText(String.valueOf(df.format(time)));
					projectreport.addContent(billingtime);
					root.addContent(projectreport);
					
					if (BILLING_REPORT.contains(this_project.getBillingNumber())) {
						System.out.print("We have a match - ");
						float oldTime = Float.parseFloat((String)BILLING_REPORT.get(this_project.getBillingNumber()));
						System.out.println("Old time is " + oldTime + " new time is " + time);
						BILLING_REPORT.put(this_project.getBillingNumber(), String.valueOf(df.format(time + oldTime)));
					} else {
						BILLING_REPORT.put(this_project.getBillingNumber(), String.valueOf(df.format(time)));
					}
					
	
				}
			}
	
			// projectfooter
			Element projectreporttotalfooter = new Element(
					"projectreporttotal-footer");
			Element jobf = new Element("job");
			jobf.setText("Totals");
			projectreporttotalfooter.addContent(jobf);
			Element projectf = new Element("project");
			projectreporttotalfooter.addContent(projectf);
			Element numberofemailsf = new Element("numberofemails");
			numberofemailsf.setText(String.valueOf(total_emails));
			projectreporttotalfooter.addContent(numberofemailsf);
			Element totalanswertime = new Element("totalanswertime");
	
			totalanswertime.setText(String.valueOf(df.format(total_answertime)));
			projectreporttotalfooter.addContent(totalanswertime);
			Element billingtimef = new Element("billingtime");
			billingtimef.setText(String.valueOf(df.format(total_time)));
			projectreporttotalfooter.addContent(billingtimef);
			root.addContent(projectreporttotalfooter);
	
			// projectfooter average
			Element projectreportaveragefooter = new Element(
					"projectreportaverage-footer");
			Element job_average_footer = new Element("job");
			job_average_footer.setText("Averages");
			projectreportaveragefooter.addContent(job_average_footer);
			Element project_average_footer = new Element("project");
			projectreportaveragefooter.addContent(project_average_footer);
			Element numberofemails_average_footer = new Element("numberofemails");
			numberofemails_average_footer.setText("N/A");
			projectreportaveragefooter.addContent(numberofemails_average_footer);
			Element averageanswertime = new Element("averageanswertime");
	
			averageanswertime.setText(String.valueOf(df.format(total_answertime
					/ total_emails)));
			projectreportaveragefooter.addContent(averageanswertime);
			Element billingtime_average_footer = new Element("billingtime");
			billingtime_average_footer.setText(String.valueOf(df.format(total_time
					/ total_emails)));
			projectreportaveragefooter.addContent(billingtime_average_footer);
			root.addContent(projectreportaveragefooter);
	
			// unread
			int total_unread = 0;
			for (Iterator it = all_store.iterator(); it.hasNext();) {
	
				EmailStore this_store = (EmailStore) it
						.next();
				int total_for_store = ReportsPDFUtil.getOutstandingForStore(
						this_store.getId(), start_date, end_date, conn);
				total_unread += total_for_store;
				Element unread = new Element("unread");
				Element store = new Element("store");
				store.setText(this_store.getStoreName());
				unread.addContent(store);
				Element unreadtotal = new Element("unreadtotal");
				unreadtotal.setText(String.valueOf(total_for_store));
				unread.addContent(unreadtotal);
				root.addContent(unread);
			}
	
			// unread footer
			Element unreadfooter = new Element("unread-footer");
			Element unreadtotaltotal = new Element("unreadtotal-total");
			unreadtotaltotal.setText(String.valueOf(total_unread));
			unreadfooter.addContent(unreadtotaltotal);
			root.addContent(unreadfooter);
			// received-header
			Element receivedheader = new Element("received-header");
			root.addContent(receivedheader);
			// received
			int total_received = 0;
			for (Iterator it = all_store.iterator(); it.hasNext();) {
	
				EmailStore this_store = (EmailStore) it
						.next();
				int total_for_store = ReportsPDFUtil.getReceivedMessagesForStore(
						this_store.getId(), start_date, end_date, conn);
				total_received += total_for_store;
				Element received = new Element("received");
				Element received_store = new Element("received_store");
				received_store.setText(this_store.getStoreName());
				received.addContent(received_store);
				Element receivedtotal = new Element("receivedtotal");
				receivedtotal.setText(String.valueOf(total_for_store));
				received.addContent(receivedtotal);
				root.addContent(received);
			}
	
			// received footer
			Element receivedfooter = new Element("received-footer");
			Element receivedtotaltotal = new Element("receivedtotaltotal");
			receivedtotaltotal.setText(String.valueOf(total_received));
			receivedfooter.addContent(receivedtotaltotal);
			root.addContent(receivedfooter);
			
			
			/*
			 * July 17, 2008 kpb
			 * We've changed the acceptable time to be answering emails
			 * within 8 hours.
			 */
			
			// with24-header
			Element within24header = new Element("within24-header");
			root.addContent(within24header);
			
			/*
			 * July 24, 2008 kpb
			 * It looks like we call this function a few times.  Going to
			 * just call it once and assign its value to a variable.
			 */
			
			int totalRepliedEmails = ReportsPDFUtil.getTotalRepliedEmails(start_date, end_date, conn);
	
			// within24
			Element within24 = new Element("within24");
			Element answered24 = new Element("answered24");
			answered24.setText(String.valueOf(ReportsPDFUtil.getEmailsAnsweredIn8Hours(start_date, end_date, conn)));
			within24.addContent(answered24);
			Element total24 = new Element("total24");
			total24.setText(String.valueOf(totalRepliedEmails));
			within24.addContent(total24);
			root.addContent(within24);
	
			// within24-footer
			Element within24footer = new Element("within24-footer");
			root.addContent(within24footer);
	
			// reasonreport-header
			Element reasonreportheader = new Element("reasonreport-header");
			root.addContent(reasonreportheader);
	
			// reasonreport
			int total_emails_reason = totalRepliedEmails;
			for (Iterator it = all_reasons.iterator(); it.hasNext();) {
	
				Reason this_reason = (Reason) it.next();
				int emails_by_reason = ReportsPDFUtil.getEmailsByReasonId(this_reason.getReasonId(), start_date, end_date, conn);
	
				Element reasonreport = new Element("reasonreport");
				Element reason = new Element("reason");
				reason.setText(this_reason.getReason());
				reasonreport.addContent(reason);
				Element qnty = new Element("qnty");
				qnty.setText(String.valueOf(emails_by_reason));
				reasonreport.addContent(qnty);
				Element percent = new Element("percent");
	
				String emails_by_reason_s = String.valueOf(emails_by_reason);
				String total_emails_reason_s = String.valueOf(total_emails_reason);
	
				percent.setText(String.valueOf(dp.format(Float
						.parseFloat(emails_by_reason_s)
						/ Float.parseFloat(total_emails_reason_s))));
				reasonreport.addContent(percent);
				root.addContent(reasonreport);
			}
			// reasonreport-footer
			Element reasonreportfooter = new Element("reasonreport-footer");
			Element total_r = new Element("total");
			total_r.setText(String.valueOf(total_emails_reason));
			reasonreportfooter.addContent(total_r);
			root.addContent(reasonreportfooter);
	
			// userreport-header
			Element userreportheader = new Element("userreport-header");
			Element name_h = new Element("name");
			name_h.setText("User");
			userreportheader.addContent(name_h);
			Element qnty_h = new Element("qnty");
			qnty_h.setText("Qnty");
			userreportheader.addContent(qnty_h);
			Element percent_h = new Element("percent");
			percent_h.setText("Percent");
			userreportheader.addContent(percent_h);
			root.addContent(userreportheader);
	
			// userreport
			int total_emails_user = totalRepliedEmails;
			for (Iterator it = all_users.iterator(); it.hasNext();) {
	
				User this_user = (User) it.next();
				int emails_by_user = ReportsPDFUtil.getEmailsByUserId(this_user
						.getId(), start_date, end_date, conn);
				Element userreport = new Element("userreport");
				Element name = new Element("name");
				name.setText(this_user.getName());
				userreport.addContent(name);
				Element qnty_r = new Element("qnty");
				qnty_r.setText(String.valueOf(emails_by_user));
				userreport.addContent(qnty_r);
				Element percent_r = new Element("percent");
				String emails_by_user_s = String.valueOf(emails_by_user);
				String total_emails_user_s = String.valueOf(total_emails_user);
				percent_r.setText(String.valueOf(dp.format(Float
						.parseFloat(emails_by_user_s)
						/ Float.parseFloat(total_emails_user_s))));
				userreport.addContent(percent_r);
				root.addContent(userreport);
			}
			// userreport-footer
			Element userreportfooter = new Element("userreport-footer");
			Element userreporttotal = new Element("userreport-total");
			userreporttotal.setText(String.valueOf(total_emails_user));
			userreportfooter.addContent(userreporttotal);
			root.addContent(userreportfooter);
	
			Element holiday_used_header = new Element("holidays-used-header");
			root.addContent(holiday_used_header);
	
			List holidays_used = ReportsPDFUtil.getCurrentHoldaysUsed(conn);
			for (Iterator it = holidays_used.iterator(); it.hasNext();) {
				Holiday holiday = (Holiday) it.next();
				Element holiday_used = new Element("holidays-used");
				Element name = new Element("name");
				name.setText(holiday.getName());
				holiday_used.addContent(name);
				root.addContent(holiday_used);
	
			}
	
			XMLOutputter outputter = new XMLOutputter();
			String xml = outputter.outputString(reqDoc);
	
			try {
				OutputStream streamOut = new FileOutputStream(xmlfile_s);
				streamOut.write(xml.getBytes());
				streamOut.close();
			} catch (Exception e) {
	
				System.out.println("e " + e);
			}
	
			System.out.println("done");
			System.out.println("end " + new Date());


	}

	public static void sendEmails() {
		String host = "outgoing.datapak.local";// "outgoing.datapak.local";
		String from = "datamail@datapakservices.com";

		Properties props = System.getProperties(); // Setup
		props.put("mail.smtp.host", host); // Get session
		Session sessionm = Session.getInstance(props, null); // Define

		try {

			MimeMessage message = new MimeMessage(sessionm);
			message.setFrom(new InternetAddress(from));

			message.addRecipients(Message.RecipientType.BCC, InternetAddress
					.parse("kbewersdorf@datapakservices.com"));
			
			message.addRecipients(Message.RecipientType.BCC, InternetAddress
					.parse("avelasquez@datapakservices.com"));	
			
			message.setSubject("HelpDesk Reports");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText("");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(pdffile_s);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("Results.pdf");
			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			message.setContent(multipart);
			Transport.send(message);

		} catch (Exception e) {
			System.out.println("problem emailing pdf " + e);

		}
	}

	public static void makePDF() {
		try {

			// Setup directories

			// Setup input and output files
			File xmlfile = new File(xmlfile_s);
			File xsltfile = new File(xsltfile_s);
			File pdffile = new File(pdffile_s);

			// Construct driver
			Driver driver = new Driver();

			// Setup Renderer (output format)
			driver.setRenderer(Driver.RENDER_PDF);

			// Setup output
			OutputStream out = new java.io.FileOutputStream(pdffile);
			out = new java.io.BufferedOutputStream(out);

			try {
				driver.setOutputStream(out);

				// Setup XSLT
				TransformerFactory factory = TransformerFactory.newInstance();
				Transformer transformer = factory
						.newTransformer(new StreamSource(xsltfile));

				// Set the value of a <param> in the stylesheet
				transformer.setParameter("versionParam", "2.0");

				// Setup input for XSLT transformation
				Source src = new StreamSource(xmlfile);

				// Resulting SAX events (the generated FO) must be piped through
				// to FOP
				Result res = new SAXResult(driver.getContentHandler());

				// Start XSLT transformation and FOP processing
				transformer.transform(src, res);
			} finally {
				out.close();
			}

			System.out.println("Success!");
		} catch (Exception e) {
			System.out.println("make pdf " + e);

		}

	}

	public static boolean hasTaskRanToday(Calendar cal) {
		boolean flag = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");

			String query = "select * from pdf_task_log";
			pstmt = conn.prepareStatement(query);
			// pstmt.setInt(1, 1);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				Date date = rs.getTimestamp(1);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				if (c.get(Calendar.DAY_OF_YEAR) == cal
						.get(Calendar.DAY_OF_YEAR)) {
					flag = true;
				}
			}

			rs.close();
			pstmt.close();

			conn.close();
		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			//System.err.println("SQLException: getAnswersAA " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception excp) {
					System.out.println("unexpected exception " + excp);
				}
			}
		}

		return flag;

	}

	public static void setRanToday(Calendar cal) {

		Date date = cal.getTime();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			conn = DriverManager.getConnection("jdbc:mysql://192.168.149.234:3306/datamail","root", "pa55wd4MYSQLprod");

			String query = "update pdf_task_log set last_ran_date = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setTimestamp(1, new java.sql.Timestamp(
					((java.util.Date) date).getTime()));
			System.out.println("Updated ran today: " + pstmt.executeUpdate());

			pstmt.close();

			conn.close();
		} catch (Exception ex) {
			DatabaseUtil.handleSQLException(conn, pstmt);
			//System.err.println("SQLException: getAnswersBB " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception excp) {
					System.out.println("unexpected exception " + excp);
				}
			}
		}

	}
}