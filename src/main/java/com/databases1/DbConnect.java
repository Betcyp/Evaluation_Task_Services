package com.databases1;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbConnect {
	 Connection connection;
	 private static InitialContext context;
	 private static DataSource datasource;
	 private static DbConnect instance;
	 
	 public static void initDatasource(){
			try {
				context=new InitialContext();
				datasource=(DataSource) context.lookup("java:/comp/env/jdbc/MobilePaymentDB");
				
			}
			catch (Exception e) {
				e.printStackTrace();
			} 
	 }
	 
	 public Connection getConnection() throws SQLException{
			
			connection=datasource.getConnection();
			return connection;
		
	}
	 
	 public static DbConnect getInstance() throws SQLException {
	        if (instance == null) {
	           instance = new DbConnect();
	        }
	        return instance;
	 }
}
