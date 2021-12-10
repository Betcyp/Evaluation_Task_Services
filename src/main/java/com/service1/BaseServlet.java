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
	JSONObject j=new JSONObject();
    Response resp=new Response();
    
	protected JSONObject getRequestBody(HttpServletRequest request) {
		
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
		
		JSONObject jsonObject=new JSONObject(result);
		return jsonObject;
	}
	
	protected void sendResp(HttpServletResponse response) throws IOException {
		 
		
		 JSONObject data=new JSONObject();
		 String key =CommonConstants.STATUS;
		 data=resp.getData();
		 data.put(key, resp.getStatus());
		 
		 response.setContentType("application/json");
		 response.setCharacterEncoding("UTF-8");
	     response.getWriter().print(data);
		 
	}
	/* protected void doPost1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException { 
	 
		try {
			BaseRequest baseReq=null;
			Response resp=null;
			
			String result=getRequestBody(request);
			
			baseReq=new BaseRequest(result);
			
			resp=new Response(baseReq);
			
		}
		catch(Exception e) {
			log.error(e);
		}
	 }
	 
	abstract protected void process(BaseRequest baseReq, Response resp,HttpServletResponse response) throws Exception;
	 */
	 

		
	protected HttpSession sessionValidation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session=request.getSession(false);
		
		if(session == null){
			resp.setStatus(CommonConstants.INVALID_SESSION_MSG);
			resp.setData(j);
			sendResp(response);
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
		resp.setData(j);
		sendResp(response);
		
		session.setMaxInactiveInterval(5*60);
		session.setAttribute("email", email);
		session.setAttribute("password", password);
		
		Cookie ck  =new Cookie("email",email);
		//ck.setMaxAge(5*60);
	    response.addCookie(ck);
		
		return session;
		
	}
}	