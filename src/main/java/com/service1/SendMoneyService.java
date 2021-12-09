package com.service1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.bussiness1.UserDetails;
import com.constants1.CommonConstants;


public class SendMoneyService extends BaseServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=sessionValidation(request, response);
		String result=getRequestBody(request); 
		JSONObject jsonObject=new JSONObject(result);
		String myEmail=(String) session.getAttribute(CommonConstants.EMAIL);
		String email=(String) jsonObject.get(CommonConstants.EMAIL);
		Double money=Double.valueOf ( (String) jsonObject.get(CommonConstants.SEND_MONEY));
		int e = 0;
		try {
			boolean receiverEmailCheck = false;
			receiverEmailCheck=UserDetails.emailExists(email);
			if(receiverEmailCheck != false) {
				String from1=myEmail;
				String to1=email;
				String transactionType=CommonConstants.TRANSACTION_SEND;
			    String transactionType1=CommonConstants.TRANSACTION_RECEIVED;
				e=UserDetails.sendMoneyUpdateTransactions(myEmail,from1,to1,transactionType,transactionType1,money);
				if(e==0) {
					resp.setStatus(CommonConstants.SENDED_MSG);
					resp.setData(j);
					sendResp(response);
				}
				else {
					resp.setStatus(CommonConstants.TRANS_CANCELLED_MSG);
					resp.setData(j);
					sendResp(response);
				}
			}
			else {
				resp.setStatus(CommonConstants.NOT_REG_USER_MSG);
				resp.setData(j);
				sendResp(response);
				}
		}
		catch(Exception e1) {
			resp.setStatus(CommonConstants.PRBLMS_MSG);
			resp.setData(j);
			sendResp(response);
		}
	}
}
