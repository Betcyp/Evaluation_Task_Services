package com.service1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.bussiness1.UserDetails;
import com.constants1.CommonConstants;

public class RegisterModuleService extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String result=getRequestBody(request); 
		JSONObject jsonObject=new JSONObject(result);
		String phoneNumber=(String) jsonObject.get(CommonConstants.PHONENUMBER);
		String email=(String) jsonObject.get(CommonConstants.EMAIL);
		
		//Response resp=new Response();
		boolean check = false;
		try {
			check=UserDetails.phoneEmailExists(phoneNumber,email);
			if(check != false)
			{
				jsonResponse.put(CommonConstants.STATUS, CommonConstants.ALREADY_REG);
			//	resp.setStatus(jsonResponse);
				sendResponse(response,jsonResponse);
			}
			else {
				String firstName=(String) jsonObject.get(CommonConstants.FIRSTNAME);
				String lastName=(String) jsonObject.get(CommonConstants.LASTNAME);
				String pass=(String) jsonObject.get(CommonConstants.PASSWORD);
				if(pass.length()<8) {
					jsonResponse.put(CommonConstants.STATUS,CommonConstants.PASSWORD_LENGTH);
					sendResponse(response,jsonResponse);
					String password1=(String) jsonObject.get(CommonConstants.PASSWORD);
				}
				else {
					String password1=pass;
					double accountBalance=0;
					UserDetails.registerDatabase(firstName,lastName,phoneNumber,email,password1, accountBalance);
					jsonResponse.put(CommonConstants.STATUS,CommonConstants.USER_SUCCESS);
					sendResponse(response,jsonResponse);
				}
				
			}
		}
		catch (Exception e) {
			jsonResponse.put(CommonConstants.STATUS,CommonConstants.REG_FAILED);
			sendResponse(response,jsonResponse);
		}
	}
	
}
		
		

		
	
	
         



