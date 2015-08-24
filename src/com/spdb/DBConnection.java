package com.spdb;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
	
	private static DBConnection instance = null;
	private static Connection conn = null;
	private final String SCHEMA_NAME = "spdb";
	private final String USERID = "root";
	private final String PASSWORD = "rutgerscs336";
	
	public static DBConnection getInstance()
	{
		if (instance == null){
			instance = new DBConnection();
			return instance;
		}
		else
			return instance;
	}
	
	public Connection openConnection() throws SQLException, ClassNotFoundException
	{
		if (conn == null)
		{
			//Load driver and connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + SCHEMA_NAME
					+ "?user=" + USERID + "&password=" + PASSWORD);
			return conn;
		}
		return conn;
	}
	
	public Connection closeConnection()
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn = null;
	}

}
