package com.project.hikes.testdb;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		try{
			String jdbcUrl = "jdbc:mysql://localhost:3306/famousHikes";
			String user = "root";
			String pass = "root"; 
			
			System.out.println("Connection to database: "+jdbcUrl);
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection Successful");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
