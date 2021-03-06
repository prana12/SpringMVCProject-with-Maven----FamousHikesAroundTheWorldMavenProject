package com.project.hikes.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testJdbcDB
 */
@WebServlet("/testJdbcDB")
public class testJdbcDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set up connection variables
		System.out.println("Testing with Servlets...");
		String jdbcUrl = "jdbc:mysql://localhost:3306/famousHikes";
		String user = "root";
		String pass = "root";

		String driver = "com.mysql.jdbc.Driver";

		//get connection to database
		try{
			PrintWriter out = response.getWriter();
			out.println("Connecting to database 'famousHikes'"+jdbcUrl);

			Class.forName(driver);

			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			out.println("SUCCESS!!!");

			myConn.close();
		}
		catch(Exception e){
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
