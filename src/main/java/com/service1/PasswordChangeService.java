package com.service1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.bussiness1.UserDetails;
import com.constants1.CommonConstants;

public class PasswordChangeService extends BaseServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=sessionValidation(request, response);
		String result=getRequestBody(request); 
		JSONObject jsonObject=new JSONObject(result);
		String myEmail=(String) session.getAttribute(CommonConstants.EMAIL);
		String newPass=(String) jsonObject.get(CommonConstants.NEW_PASSWORD);
		String confirmNewPass=(String) jsonObject.get(CommonConstants.CONFIRM_NEW_PASSWORD);
		
		try {
			if(newPass.length()<8) {
				resp.setStatus(CommonConstants.PASSWORD_LENGTH);
				resp.setData(j);
				sendResp(response);
			}
			else {
				//passwordChecking(response,myEmail,newPass,confirmNewPass);
				if(newPass.equals(confirmNewPass)) {
					UserDetails.updatePasswordInReg(newPass,myEmail);
					resp.setStatus(CommonConstants.PASSWORD_CHANGE);
					resp.setData(j);
					sendResp(response);
					
				}
				else {	
					resp.setStatus(CommonConstants.PASSWORD_CHANGE_INCORRECT);
					resp.setData(j);
					sendResp(response);
				}
			}
		}
		catch(Exception e) {
			resp.setStatus(CommonConstants.PRBLMS_MSG);
			resp.setData(j);
			sendResp(response);
		}
	}
}
