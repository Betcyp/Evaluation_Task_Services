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


@WebServlet("/ViewTransactionHistory")
public class ViewTransactionHistory extends BaseServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=sessionValidation(request, response);
		String myEmail=(String) session.getAttribute(CommonConstants.EMAIL);
		String email=(String) session.getAttribute(CommonConstants.EMAIL);
		PrintWriter resp =sendResponse(request, response);
		
		
		try {
			if(UserDetails.getTransactionDetails(myEmail,email)==null) {
				resp.print("{\"status\":\"No transactions made\"}");
			}
			else {
				resp.print(UserDetails.getTransactionDetails(myEmail,email));
			}
		}
		catch (Exception e) {
			resp.print("{\"status\":\"Something went wrong\"}");
		}
	} 
}


