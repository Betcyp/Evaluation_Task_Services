package com.service1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import com.bussiness1.UserDetails;
import com.constants1.CommonConstants;


public class BalanceService extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		HttpSession session=sessionValidation(request, response);
		String myEmail=(String) session.getAttribute(CommonConstants.EMAIL);
				
		try {
			JSONObject objBalance=UserDetails.getBalance(myEmail);
			resp.setStatus(CommonConstants.SUCCESS);
			resp.setData(objBalance);
			sendResp(response);
		} 
		catch (Exception e) {
			resp.setStatus(CommonConstants.PRBLMS_MSG);
			resp.setData(j);
			sendResp(response);
			}
		}
}


/*{"status": "success , 
"accountBalance":210"}*/

