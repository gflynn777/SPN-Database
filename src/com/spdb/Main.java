package com.spdb;

import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		DBManager.generateSpns("555", 3);
	}

}
