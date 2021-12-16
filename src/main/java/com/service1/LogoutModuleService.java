package com.service1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.constants1.CommonConstants;

public class LogoutModuleService extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessions=sessionValidation(request, response);
		String myEmail=(String) sessions.getAttribute(CommonConstants.EMAIL);
		
		if(sessions != null){
			sessions.invalidate();
			resp.setStatus(CommonConstants.LOGOUT_MSG);
			resp.setData(j);
			sendResp(response);
    	}
    }
}
