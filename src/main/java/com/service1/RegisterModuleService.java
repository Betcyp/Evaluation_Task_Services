package com.service1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.bussiness1.UserDetails;
import com.constants1.CommonConstants;
import com.google.gson.JsonObject;

public class RegisterModuleService extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		String result=getRequestBody(request); 
		JSONObject jsonObject=new JSONObject(result);
		String phoneNumber=(String) jsonObject.get(CommonConstants.PHONENUMBER);
		String email=(String) jsonObject.get(CommonConstants.EMAIL);
		
		try {
			boolean check = false;
			check=UserDetails.phoneEmailExists(phoneNumber,email);
			
			if(check != false)
			{
				resp.setStatus(CommonConstants.ALREADY_REG);
				sendResp(response,resp.getStatus());
			}
			else {
				String firstName=(String) jsonObject.get(CommonConstants.FIRSTNAME);
				String lastName=(String) jsonObject.get(CommonConstants.LASTNAME);
				String pass=(String) jsonObject.get(CommonConstants.PASSWORD);
					if(pass.length()<8) {
						resp.setStatus(CommonConstants.PASSWORD_LENGTH);
						sendResp(response,resp.getStatus());
						
					}
					else {
						double accountBalance=0;
						UserDetails.registerDatabase(firstName,lastName,phoneNumber,email,pass, accountBalance);
						resp.setStatus(CommonConstants.USER_SUCCESS);
						sendResp(response,resp.getStatus());
					}
			}
		}
		catch (Exception e) {
			//log.error(e);
			resp.setStatus(CommonConstants.REG_FAILED);
			sendResp(response,resp.getStatus());
		}
	}
}
	


		
		

		
	
	
         



