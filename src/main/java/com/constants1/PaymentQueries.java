package com.constants1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bussiness1.UserDetails;

public class PaymentQueries {
	
	public static final String REGISTER_QUERY="INSERT INTO REGISTER(firstName,lastName,phoneNumber,email,password) VALUES (?, ?, ?, ?, ?)";
	
	public static final String EMAILPASS_QUERY="SELECT * FROM REGISTER WHERE email=? AND password=?";
	
	public static final String PHONE_QUERY="SELECT * FROM REGISTER WHERE phoneNumber=?";
	
	public static final String LOGIN_QUERY="INSERT INTO LOGIN(email,password,session_id) VALUES (?, ?, ?)";
	
	public static final String EMAIL_QUERY="SELECT * FROM REGISTER WHERE email=?";
	
	public static final String BALANCE_QUERY="INSERT INTO BALANCE(email,account_balance) VALUES (?, ?)";
	
	public static final String ACCOUNTEMAIL_QUERY="SELECT account_balance FROM BALANCE WHERE email=?";
	
	public static final String BALANCEUPDATE_QUERY = "UPDATE BALANCE SET account_balance=? WHERE email=? ";
	
	public static final String TRANSACTION_QUERY="INSERT INTO TRANSACTION(sender,receiver,transaction_type,amount) VALUES (?, ?, ?, ?)";
	
	//public static final String TRANSACTIONEMAIL_QUERY="SELECT * FROM TRANSACTION WHERE sender=? OR receiver=? ORDER BY updated_at DESC";
	
	//	public static final String TRANSACTIONEMAIL_QUERY="SELECT * FROM TRANSACTION WHERE (sender=? AND (transaction_type = 'Send' OR transaction_type = 'Deposited' , transaction_type='Received') ORDER BY updated_at DESC";

	public static final String TRANSACTIONEMAIL_QUERY="SELECT * FROM TRANSACTION WHERE (sender=? AND (transaction_type = 'Send' OR transaction_type = 'Deposited')) OR (receiver=? AND transaction_type='Received') ORDER BY updated_at DESC";
	
	///public static final String TRANSACTIONEMAIL_QUERY="SELECT * FROM TRANSACTION  WHERE (sender=? AND (transaction_type = 'Send' OR transaction_type = 'Deposited')) OR SELECT sender,transaction_type,amount,updated_at FROM TRANSACTION   WHERE (receiver=? AND transaction_type='Received') ORDER BY updated_at DESC";

	///public static final String TRANSACTIONEMAIL_QUERY="SELECT receiver,transaction_type,amount,updated_at FROM TRANSACTION  WHERE (sender=? AND (transaction_type = 'Send' OR transaction_type = 'Deposited')) OR SELECT sender,transaction_type,amount,updated_at FROM TRANSACTION   WHERE (receiver=? AND transaction_type='Received') ORDER BY updated_at DESC";
	/*public static final String TRANSACTIONEMAIL_QUERY=
			 "(SELECT receiver,transaction_type,amount,updated_at FROM TRANSACTION WHERE (sender=? AND (transaction_type = 'Send' OR transaction_type = 'Deposited')))"
			+ "OR"
			+ "(SELECT sender,transaction_type,amount,updated_at FROM TRANSACTION WHERE (receiver=? AND transaction_type='Received'))"
			+ "ORDER BY updated_at DESC";*/
}







/*protected void sendMoneyChecking(HttpServletRequest request, HttpServletResponse response, double money, String myEmail, String email) throws ServletException, IOException {
		
		PrintWriter resp =sendResponse(request, response);
		try {
		boolean receiverEmailCheck = false;
		receiverEmailCheck=UserDetails.emailExists(email);
		if(receiverEmailCheck != false) {
			//if(money <= currentBalance) {
				//double total=currentBalance - money;                                                                                                                                                                                                                                 
				//double totalOfReceiver=currentBalanceOfReceiver + money;
				
				//UserDetails.balanceUpdate(total,myEmail);
				//UserDetails.balanceUpdate(totalOfReceiver,email);
				//UserDetails.updateBalanceAndTransactions(total,myEmail, from1, to1, transactionType, money);
				//resp.print("{\"status\":\"Successfully Sended!!\"}");
				try {
				String from1=myEmail;
			    String to1=email;
			    String transactionType=CommonConstants.TRANSACTION_SEND;
				UserDetails.transactionDatabase(myEmail,from1,to1,transactionType,money);
			   // UserDetails.updateBalanceAndTransactions(myEmail, from1, to1, transactionType, money);
				
				String fromRec=myEmail;
				String toRec=email;
				String transactionTypeRec=CommonConstants.TRANSACTION_RECEIVED;
				//UserDetails.transactionDatabase(fromRec,toRec,transactionTypeRec,money);
				UserDetails.updateBalanceAndTransactions(myEmail, fromRec, toRec, transactionTypeRec, money);
				//UserDetails.updateBalanceAndTransactions(myEmail, from1, to1, transactionType, money);
				//UserDetails.transactionDatabase(myEmail,fromRec,toRec,transactionTypeRec,money);
				resp.print("{\"status\":\"Successfully Sended!!\"}");
			//}
			//else {
				//resp.print("{\"status\":\"Transaction cancelled due to insufficient balance\"}");
				//}
			}
				catch(SQLException e) {
					resp.print("{\"status\":\"Transaction cancelled due to insufficient balance!!..\"}");
					//log.error(e);
				}
		}
		else {
			resp.print("{\"status\":\"Not a Registered User!!..\"}");
			}
		}
		catch(Exception e) {
			resp.print("{\"status\":\"Something went wrong!!..\"}");
			//log.error(e);
		}
	}
	*/
    		
