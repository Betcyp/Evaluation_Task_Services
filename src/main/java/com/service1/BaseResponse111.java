package com.service1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 * Servlet implementation class baseResponse
 */
@WebServlet("/baseResponse")
public class BaseResponse111 extends JSONObject {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(BaseResponse111.class);      
   
	protected String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static final String RESPONSE = "response";
	
	//public static final String RESPONSE_PHONEPASS = "Phone number/email already registered try with different one";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
}
