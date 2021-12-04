package com.bussiness1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.constants1.CommonConstants;
import com.constants1.PaymentQueries;
import com.databases1.DbConnect;

public class UserDetails {
	
	static Logger log = Logger.getLogger(UserDetails.class);
	
	public static boolean emailExists(String email) throws SQLException {
		Connection connection = null;
        PreparedStatement ps = null; 
        ResultSet rs = null;
        boolean exists = false;
        try {
        	connection=DbConnect.getInstance().getConnection();
        	ps=connection.prepareStatement(PaymentQueries.EMAIL_QUERY);
			ps.setString(1,email);
			rs=ps.executeQuery();
			if(rs.next()) 
	         {
				exists = true;
	         }
        }
        finally {
			ps.close();
			rs.close();
			connection.close();
		}
		return exists;  
		
	}
     public static boolean phoneEmailExists( String phoneNumber, String email) throws SQLException{
    	 Connection connection = null;
         PreparedStatement ps = null; 
         ResultSet rs = null;
         boolean exists = false;
         try {
        	 connection=DbConnect.getInstance().getConnection();
         	ps=connection.prepareStatement(PaymentQueries.PHONE_QUERY);
 			ps.setString(1,phoneNumber);
 			rs=ps.executeQuery();
 			if(rs.next()) 
 	         {
 				exists = true;
 			 }
 			else {
 				emailExists(email);
 			}
		 }
         finally {
 			ps.close();
 			rs.close();
 			connection.close();
 		}
		return exists;
     }
	
	
	public static void loginDatabase(String email1, String pass, String mySessionId) throws SQLException {
		Connection connection = null;
        PreparedStatement ps = null;

		try {
			connection=DbConnect.getInstance().getConnection();
			ps=connection.prepareStatement(PaymentQueries.LOGIN_QUERY);
			ps.setString(1,email1);
            ps.setString(2,pass);
            ps.setString(3,mySessionId);
            ps.executeUpdate();
		}
		finally {
			ps.close();
			connection.close();
		}
		 
	}
     /*public static boolean loginDatabase(String email1, String pass, String mySessionId) throws SQLException {
 		Connection connection = null;
 		CallableStatement cs = null; 
 		boolean enter=false;
 		try {
 			connection=DbConnect.getInstance().getConnection();
 			cs= connection.prepareCall("{call login_procedure(?, ?, ?)}");
			cs.setString(1,email1);
			cs.setString(2,pass);
			cs.setString(3,mySessionId);
			cs.executeUpdate();	
 		}
 		catch(SQLException e) {
 			enter=true;
 			log.error(e);
 		}
 		finally {
 			cs.close();
 			connection.close();
 		}
		return enter;
 		 
 	}*/
     public static boolean emailPassExists(String email1, String pass) throws SQLException  {
    	 Connection connection = null;
    	 PreparedStatement ps = null; 
    	 ResultSet rs = null;
    	 boolean exists = false;
    	 try {
    	 	connection=DbConnect.getInstance().getConnection();
    	 	ps=connection.prepareStatement(PaymentQueries.EMAILPASS_QUERY);
    	 	ps.setString(1,email1);
    	 	ps.setString(2,pass);
    	 	rs=ps.executeQuery();
    	 	
    	 	if(rs.next()) 
    	      {
    	 			exists = true;
    	      }
    	 }
    	 finally {
    	 	ps.close();
    	 	rs.close();
    	 	connection.close();
    	 }
    	 return exists;  

    	 }
	
	
	

	public static JSONObject getBalance(String myEmail) throws SQLException {
		 Connection connection = null;
	   	 PreparedStatement ps = null; 
	   	 ResultSet rs = null;
	   
	   	 try {
	   	 	connection=DbConnect.getInstance().getConnection();
	   	 	ps=connection.prepareStatement(PaymentQueries.ACCOUNT_BALANCE_QUERY);
	   	 	ps.setString(1, myEmail);
	   	 	rs=ps.executeQuery();
	   	 	JSONObject jsonObject=new JSONObject();
	   	 	while(rs.next()) 
	   	      {
	   	 		jsonObject.put(CommonConstants.ACCOUNT_BALANCE,rs.getDouble(CommonConstants.ACCOUNT_BALANCE_TABLE));
	   	 		
	   	      }
			return jsonObject;
		}
	   	finally {
   	 	ps.close();
   	 	rs.close();
   	 	connection.close();
	   	}
		
	}

	public static int sendMoneyUpdateTransactions(String myEmail,String from1, String to1, String transactionType,String transactionType1,Double money) throws SQLException{
	
		Connection connection = null;
		CallableStatement cs = null; 
		int e=0;
	    try {
	    	connection=DbConnect.getInstance().getConnection();
	    	cs= connection.prepareCall("{call sendMoney_procedure(?, ?, ?, ?, ?, ?)}");
	    	cs.setString(1,myEmail);
			cs.setString(2,from1);
			cs.setString(3,to1);
			cs.setString(4,transactionType);
			cs.setString(5,transactionType1);
			cs.setDouble(6,money);
			cs.executeUpdate();	
		}
	    catch(SQLException e1) {
	    	e=1;
	    	//return e;
	    }
		
	    finally {
			cs.close();
			connection.close();
		}
		return e;
		
	}


	public static JSONObject getTransactionDetails(String myEmail,String email) throws SQLException {
		Connection connection = null;
	   	PreparedStatement stmnt = null; 
	   	ResultSet rs = null;
	   
	   	 try {
	   	 	connection=DbConnect.getInstance().getConnection();
	   	 	stmnt=connection.prepareStatement(PaymentQueries.TRANSACTIONEMAIL_QUERY);
	   	 	stmnt.setString(1, myEmail);
	   	 	stmnt.setString(2, email);
	   	 	rs=stmnt.executeQuery();
	   	 	JSONObject jsonObject=new JSONObject();
	   	 	JSONArray array=new JSONArray();
	   	 	while(rs.next()) 
	   	      {
	   	 		JSONObject record=new JSONObject(); 
	   	 		if(rs.getString(CommonConstants.RECEIVER).equals(myEmail)){
	   	 		 record.put(CommonConstants.SENDER,rs.getString(CommonConstants.SENDER));
	   	 		}
	   	 		else {
	   	 		 record.put(CommonConstants.RECEIVER,rs.getString(CommonConstants.RECEIVER));
	   	 		}
	   	 		 record.put(CommonConstants.TRANSACTION_TYPE,rs.getString(CommonConstants.TRANSACTION_TYPE_TABLE));
	   	 		 record.put(CommonConstants.AMOUNT,rs.getDouble(CommonConstants.AMOUNT));
	   	 		 record.put(CommonConstants.TIME,rs.getString(CommonConstants.UPDATED_AT));
	   	 		 array.put(record);
	   	 	 }
	   	 	if(array.isEmpty()) {
	   	 		return null;
	   	 	}
	   	 	else{
	   	 		jsonObject.put(CommonConstants.TRANSACTION_DETAILS, array);
	   	 		return jsonObject;
	   	 	}
	   	      
		}
	   	finally {
  	 	stmnt.close();
  	 	rs.close();
  	 	connection.close();
	   	}
	}
	
	public static void registerDatabase(String firstName, String lastName, String phoneNumber, String email, String password1, double accountBalance) throws SQLException {
        
		Connection connection = null;
		CallableStatement cs = null; 
        try {
        	connection=DbConnect.getInstance().getConnection();
        	cs= connection.prepareCall("{call registration(?, ?, ?, ?, ?, ?)}");
			cs.setString(1,firstName);
			cs.setString(2,lastName);
			cs.setString(3,phoneNumber);
			cs.setString(4,email);
			cs.setString(5,password1);
			cs.setDouble(6,accountBalance);
			cs.executeUpdate();		
		}
        finally {
			cs.close();
			connection.close();
		}
	}
	
	public static void updateBalanceAndTransactions(String myEmail,String from1,String to1, String transactionType,double money) throws SQLException {
        
		Connection connection = null;
		CallableStatement cs = null; 
        try {
        	connection=DbConnect.getInstance().getConnection();
        	cs= connection.prepareCall("{call addMoney_procedure(?, ?, ?, ?, ?)}");
			cs.setString(1,myEmail);
			cs.setString(2,from1);
			cs.setString(3,to1);
			cs.setString(4,transactionType);
			cs.setDouble(5,money);
			cs.executeUpdate();		
		}
        finally {
			cs.close();
			connection.close();
		}
}
	public static void updatePassword(String newPass1, String myEmail) throws SQLException {
		Connection connection = null;
        PreparedStatement ps = null;
        
        try {
        	connection=DbConnect.getInstance().getConnection();
        	ps=connection.prepareStatement(PaymentQueries.PASSWORD_UPDATE_QUERY);
        	ps.setString(1, newPass1);
        	ps.setString(2,myEmail);
        	ps.executeUpdate();
        	
        }
        finally {
        	ps.close();
			connection.close();
        }
		
	}
}
