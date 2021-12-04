package com.service1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.bussiness1.UserDetails;
import com.constants1.CommonConstants;


@WebServlet("/AddMoneyService")
public  class AddMoneyService extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    		HttpSession session=sessionValidation(request, response);
    		String result=getRequestBody(request); 
    		JSONObject jsonObject=new JSONObject(result);
			String myEmail=(String) session.getAttribute(CommonConstants.EMAIL);
			Double money=Double.valueOf ( (String) jsonObject.get(CommonConstants.ADD_MONEY));
					
			try {
				String from1=myEmail;
				String to1=myEmail;
				String transactionType=CommonConstants.TRANSACTION_DEPO;
				UserDetails.updateBalanceAndTransactions(myEmail, from1, to1, transactionType, money);
				jsonResponse.put(CommonConstants.STATUS, CommonConstants.DEPOSITED_MSG);
				sendResponse(response,jsonResponse);
			} 
			catch (Exception e) {
				jsonResponse.put(CommonConstants.STATUS, CommonConstants.PRBLMS_MSG);
				sendResponse(response,jsonResponse);
			}
		}
}



   