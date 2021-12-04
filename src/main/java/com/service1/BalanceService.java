package com.service1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.bussiness1.UserDetails;
import com.constants1.CommonConstants;
import com.constants1.PaymentQueries;
@WebServlet("/BalanceService")
public class BalanceService extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		HttpSession session=sessionValidation(request, response);
		String myEmail=(String) session.getAttribute(CommonConstants.EMAIL);
				
		try {
			JSONObject objBalance=UserDetails.getBalance(myEmail);
			//jsonResponse.put(CommonConstants.STATUS, objBalance);
			sendResponse(response,objBalance);
		} 
		catch (Exception e) {
			jsonResponse.put(CommonConstants.STATUS,CommonConstants.PRBLMS_MSG);
			sendResponse(response,jsonResponse);
			}
		}
}
			


