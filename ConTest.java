package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConTest {

	public static void main(String args[]) {
		Connection con = null;
		Statement st = null;
		String query = null;
		ResultSet rs = null;
		try {
			// register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("Done");

			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle", "system", "manager");

			// create statement object
			if (con != null)
				st = con.createStatement();

			// prepare sql query
			query = "select * from student";

			// execute the query
			if (st != null)
				rs = st.executeQuery(query);

			// process the result set
			if (rs != null) {
				while (rs.next() != false) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
				}
			}

		} /*catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}*/ catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close jdbc objects
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
         }//finally
	}//main

}//class
