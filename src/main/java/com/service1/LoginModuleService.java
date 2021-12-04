package com.service1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.bussiness1.UserDetails;
import com.constants1.CommonConstants;


@WebServlet("/LoginModuleService")
public  class LoginModuleService extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String result=getRequestBody(request); 
		JSONObject jsonObject=new JSONObject(result);
		
		String email1=(String) jsonObject.get(CommonConstants.EMAIL);
		String pass=(String) jsonObject.get(CommonConstants.PASSWORD);
		
		boolean enter = false;
		
		try {
			enter=UserDetails.emailPassExists(email1, pass);
		
			if(enter != false) {
		
				HttpSession session=sessionCreation(email1, pass, request, response);	
				session=sessionValidation(request, response);
				String mySessionId=session.getId();
				UserDetails.loginDatabase(email1, pass, mySessionId);
			}
			else {
				jsonResponse.put(CommonConstants.STATUS,CommonConstants.INVALID_MSG);
				sendResponse(response,jsonResponse);
			}
			
		}
		catch (Exception e) {
			jsonResponse.put(CommonConstants.STATUS,CommonConstants.LOGIN_FAILED);
			sendResponse(response,jsonResponse);
		}
			
	}
}
		



