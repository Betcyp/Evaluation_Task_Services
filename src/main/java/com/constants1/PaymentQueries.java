package com.constants1;

public class PaymentQueries {
	
	public static final String EMAILPASS_QUERY="SELECT * FROM REGISTER WHERE email=? AND password=?";
	
	public static final String PHONE_QUERY="SELECT * FROM REGISTER WHERE phoneNumber=?";
	
	public static final String LOGIN_QUERY="INSERT INTO LOGIN(email,password,session_id) VALUES (?, ?, ?)";
	
	public static final String EMAIL_QUERY="SELECT * FROM REGISTER WHERE email=?";
	
	public static final String ACCOUNT_BALANCE_QUERY="SELECT account_balance FROM BALANCE WHERE email=?";
	
	public static final String TRANSACTIONEMAIL_QUERY="SELECT * FROM TRANSACTION WHERE (sender=? AND (transaction_type = 'Send' OR transaction_type = 'Deposited')) OR (receiver=? AND transaction_type='Received') ORDER BY updated_at DESC";

	public static final String PASSWORD_UPDATE_QUERY = "UPDATE REGISTER SET password=? WHERE email=? ";
	
}

    		
