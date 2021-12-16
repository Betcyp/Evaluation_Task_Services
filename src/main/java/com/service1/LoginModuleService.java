package com.service1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import com.bussiness1.UserDetails;
import com.constants1.CommonConstants;

public  class LoginModuleService extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject jsonObject = getRequestBody(request);
		String email=(String) jsonObject.get(CommonConstants.EMAIL);
		String pass=(String) jsonObject.get(CommonConstants.PASSWORD);
		
		boolean enter = false;
		
		try {
			enter=UserDetails.emailPassExists(email, pass);
		
			if(enter != false) {
		
				HttpSession session=sessionCreation(email, pass, request, response);	
				session=sessionValidation(request, response);
				String mySessionId=session.getId();
				UserDetails.loginDatabase(email, pass, mySessionId);
			}
			else {
				resp.setStatus(CommonConstants.INVALID_MSG);
				resp.setData(j);
				sendResp(response);
			}
			
		}
		catch (Exception e) {
			resp.setStatus(CommonConstants.LOGIN_FAILED);
			resp.setData(j);
			sendResp(response);
		}
			
	}
}
		



