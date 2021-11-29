package com.service1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.bussiness1.UserDetails;
import com.constants1.CommonConstants;
/**
 * Servlet implementation class RegisterModule
 */
@WebServlet("/Register")
public class RegisterModuleService extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result=getRequestBody(request); 
		JSONObject jsonObject=new JSONObject(result);
		
		PrintWriter resp=sendResponse(request, response);
		String phoneNumber=(String) jsonObject.get(CommonConstants.PHONENUMBER);
		String email=(String) jsonObject.get(CommonConstants.EMAIL);
		
		boolean check = false;
		try {
			check=UserDetails.phoneEmailExists(phoneNumber,email);
			//check=UserDetails.registerDatabase(firstName,lastName,phoneNumber,email,password1, accountBalance);
			if(check != false)
			{
				resp.print("{\"status\":\"Phone number/email already registered try with different one\"}");
			}
			else {
				String firstName=(String) jsonObject.get(CommonConstants.FIRSTNAME);
				String lastName=(String) jsonObject.get(CommonConstants.LASTNAME);
				String pass=(String) jsonObject.get(CommonConstants.PASSWORD);
			
				if(pass.length()<8) {
					resp.print("{\"status\":\"Password must contain minimum of 8 characters and one special characters\"}");
					String password1=(String) jsonObject.get(CommonConstants.PASSWORD);
				}
				else {
					String password1=pass;
					double accountBalance=0;
					UserDetails.registerDatabase(firstName,lastName,phoneNumber,email,password1, accountBalance);
					resp.print("{\"status\":\"User successfully registered\"}");
				}
				
			}
		}
		catch (Exception e) {
				resp.print("{\"status\":\"Something went wrong...Registration failed!!\"}");
		}
	}
}
		
		

		
	
	
         



