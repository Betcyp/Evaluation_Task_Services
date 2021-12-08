package com.service1;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import com.constants1.CommonConstants;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(BaseServlet.class);   
	//Gson gson = new Gson();
  
   Response resp=new Response();
	protected String getRequestBody(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		String result= null;
		try {
		    BufferedReader reader = request.getReader();
		    String line;
		    while ((line = reader.readLine()) != null)
		      sb.append(line);
		} 
		catch (Exception e) { 
			 log.error(e);
			
		}
		result=sb.toString();
		return result;
		
	}
	
	 /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	 
		try {
			BaseRequest baseReq=null;
			Response resp=null;
			//String base=null;
			String result=getRequestBody(request);
			
			baseReq=new BaseRequest(result);
			resp=new Response(baseReq);
			
			//resp.setStatus(CommonConstants.STATUS);
			
			baseReq.setFirstName(CommonConstants.FIRSTNAME);
			baseReq.setLastName(CommonConstants.LASTNAME);
			baseReq.setPhoneNumber(CommonConstants.PHONENUMBER);
			baseReq.setEmail(CommonConstants.EMAIL);
			baseReq.setPass(CommonConstants.PASSWORD);
			//process(baseReq,resp);
			//sendResp(response, msg);
		}
		catch(Exception e) {
			log.error(e);
		}
	 }
	 
	//abstract protected void process(BaseRequest baseReq, Response resp) throws Exception;
	 */
	 protected void sendResp(HttpServletResponse response,String msg) throws IOException {
		 
		 //Gson g=new Gson();
		 JSONObject obj=new JSONObject();
		 String key =CommonConstants.STATUS;
         obj.put(key, msg);
		 response.setContentType("application/json");
		 response.setCharacterEncoding("UTF-8");
	     response.getWriter().print(obj);
	 }

		
	protected HttpSession sessionValidation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session=request.getSession(false);
		
		if(session == null){
			resp.setStatus(CommonConstants.INVALID_SESSION_MSG);
			sendResp(response,resp.getStatus());
		}
	
		else {
			String email = (String) session.getAttribute("email");
		}
	
		String email1 = null;
		String sessionID = null;
		
		Cookie[] cks = request.getCookies();
		if(cks !=null){
			for(Cookie cookie : cks){
				if(cookie.getName().equals("email")) {
					email1= cookie.getValue();
				}
				if(cookie.getName().equals("JSESSIONID")) {
					sessionID = cookie.getValue();
				}
			}
		}
		return session;
		
	}

	protected HttpSession sessionCreation(String email,String password,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		resp.setStatus(CommonConstants.LOGIN_MSG);
		sendResp(response,resp.getStatus());

		session.setMaxInactiveInterval(5*60);
		session.setAttribute("email", email);
		session.setAttribute("password", password);
		
		Cookie ck  =new Cookie("email",email);
		//ck.setMaxAge(5*60);
	    response.addCookie(ck);
		
		return session;
		
	}
}	