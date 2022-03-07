package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-03-one-to-many";
		String userId = "hbstudent";
		String password = "hbstudent";

		try {
			System.out.println("Connecting to DB : ");

			Connection myConnection = DriverManager.getConnection(jdbcUrl, userId, password);
			System.out.println("Connection Succesful");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
