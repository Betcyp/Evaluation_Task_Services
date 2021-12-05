package com.service1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import com.bussiness1.UserDetails;
import com.constants1.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/BaseServlet")
public  class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(BaseServlet.class);   
	//Gson gson = new Gson();
   JSONObject jsonResponse=new JSONObject();

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
		
		/*JSONObject jsonObject=new JSONObject(sb.toString());
		String phoneNumber1=(String) jsonObject.get(CommonConstants.PHONENUMBER);
		String email1=(String) jsonObject.get(CommonConstants.EMAIL);
		String firstName1=(String) jsonObject.get(CommonConstants.FIRSTNAME);
		String lastName1=(String) jsonObject.get(CommonConstants.LASTNAME);
		String pass1=(String) jsonObject.get(CommonConstants.PASSWORD);
		return jsonObject;*/
	}
	protected void sendResponse(HttpServletResponse response,JSONObject jsonResponse) throws ServletException,IOException  {
		
		//Gson gson = new Gson();
		Response resp=new Response();
		resp.setStatus(jsonResponse);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(resp.getStatus());
		
	}
	
	/* protected void doPost(HttpServletRequest request, HttpServletResponse response, BaseRequest baseResp, JSONObject jsonResponse) throws ServletException, IOException { 
	 
		
		BaseRequest baseReq=null;
		Response resp=null;
		
		String stringReq=getRequestBody(request);
		
		baseReq=new BaseRequest(stringReq);
		resp=new Response(baseReq);
		/*try {
			process(baseReq,resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendResponse(baseReq,resp,response,jsonResponse);
	}
	
	abstract protected void process(BaseRequest baseReq, Response resp) throws Exception;
	
	*/
	protected HttpSession sessionValidation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session=request.getSession();
		
		//allow access only if session exists
		if(session.getAttribute("email") == null){
			jsonResponse.put(CommonConstants.STATUS,CommonConstants.LOGIN_FAILED_MSG);
			sendResponse(response,jsonResponse);
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
	
	protected HttpSession sessionCreation(String email1,String password,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		jsonResponse.put(CommonConstants.STATUS,CommonConstants.LOGIN_MSG);
		sendResponse(response,jsonResponse);

		session.setMaxInactiveInterval(5*60);
		session.setAttribute("email", email1);
		session.setAttribute("password", password);
		
		Cookie ck  =new Cookie("email",email1);
		//ck.setMaxAge(5*60);
	    response.addCookie(ck);
		
		return session;
		
	}
	
	protected void sendMoneyChecking(HttpServletRequest request, HttpServletResponse response, double money, String myEmail, String email) throws ServletException, IOException {
		
		try {
				int e = 0;
				String from1=myEmail;
				String to1=email;
				String transactionType=CommonConstants.TRANSACTION_SEND;
			    String transactionType1=CommonConstants.TRANSACTION_RECEIVED;
				e=UserDetails.sendMoneyUpdateTransactions(myEmail,from1,to1,transactionType,transactionType1,money);
				if(e==0) {
					jsonResponse.put(CommonConstants.STATUS,CommonConstants.SENDED_MSG);
					sendResponse(response,jsonResponse);
				}
				else {
					jsonResponse.put(CommonConstants.STATUS,CommonConstants.TRANS_CANCELLED_MSG);
					sendResponse(response,jsonResponse);
				}
		}
		catch(Exception e) {
			jsonResponse.put(CommonConstants.STATUS,CommonConstants.PRBLMS_MSG);
			sendResponse(response,jsonResponse);
		}
	}
	
	protected void requestChecking(HttpServletRequest request, HttpServletResponse response) {
		
		String result=getRequestBody(request); 
		JSONObject jsonObject=new JSONObject(result);
		String firstName=(String) jsonObject.get(CommonConstants.FIRSTNAME);
		String lastName=(String) jsonObject.get(CommonConstants.LASTNAME);
		String pass=(String) jsonObject.get(CommonConstants.PASSWORD);
		String phoneNumber=(String) jsonObject.get(CommonConstants.PHONENUMBER);
		String email=(String) jsonObject.get(CommonConstants.EMAIL);
	}
	
	protected void passwordChecking(HttpServletRequest request, HttpServletResponse response, String myEmail, String newPass, String confirmNewPass) throws ServletException, IOException {
		
		try {
			if(newPass.equals(confirmNewPass)) {
				jsonResponse.put(CommonConstants.STATUS, CommonConstants.PASSWORD_CHANGE);
				sendResponse(response,jsonResponse);
				UserDetails.updatePasswordInReg(newPass,myEmail);
			}
			else {
				jsonResponse.put(CommonConstants.STATUS, CommonConstants.PASSWORD_CHANGE_INCORRECT);
				sendResponse(response,jsonResponse);
			}
			
		}
		catch(Exception e) {
			jsonResponse.put(CommonConstants.STATUS, CommonConstants.PRBLMS_MSG);
			sendResponse(response,jsonResponse);
		}
	}
	
	protected void regChecking(HttpServletRequest request,HttpServletResponse response, String phoneNumber, String email, JSONObject jsonObject) throws ServletException, IOException {
		
		try {
				String firstName=(String) jsonObject.get(CommonConstants.FIRSTNAME);
				String lastName=(String) jsonObject.get(CommonConstants.LASTNAME);
				String pass=(String) jsonObject.get(CommonConstants.PASSWORD);
				if(pass.length()<8) {
					jsonResponse.put(CommonConstants.STATUS,CommonConstants.PASSWORD_LENGTH);
					sendResponse(response,jsonResponse);
					String password1=(String) jsonObject.get(CommonConstants.PASSWORD);
				}
				else {
					String password1=pass;
					double accountBalance=0;
					UserDetails.registerDatabase(firstName,lastName,phoneNumber,email,password1, accountBalance);
					jsonResponse.put(CommonConstants.STATUS,CommonConstants.USER_SUCCESS);
					sendResponse(response,jsonResponse);
				}
				
		}
		catch (Exception e) {
			jsonResponse.put(CommonConstants.STATUS,CommonConstants.REGFAIL_AGAIN_FAILED);
			sendResponse(response,jsonResponse);
			log.error(e);
		}
	}
	
}
/*protected void sendMoneyChecking(HttpServletRequest request, HttpServletResponse response, double money, String myEmail, String email) throws ServletException, IOException {

try {
	boolean receiverEmailCheck = false;
	receiverEmailCheck=UserDetails.emailExists(email);
	if(receiverEmailCheck != false) {
			int e = 0;
			String from1=myEmail;
		    String to1=email;
		    String transactionType=CommonConstants.TRANSACTION_SEND;
		    String transactionType1=CommonConstants.TRANSACTION_RECEIVED;
			e=UserDetails.sendMoneyUpdateTransactions(myEmail,from1,to1,transactionType,transactionType1,money);
			if(e==0) {
				jsonResponse.put(CommonConstants.STATUS,CommonConstants.SENDED_MSG);
				sendResponse(response,jsonResponse);
			}
			else {
				jsonResponse.put(CommonConstants.STATUS,CommonConstants.TRANS_CANCELLED_MSG);
				sendResponse(response,jsonResponse);
			}
	}	
			
	else {
		jsonResponse.put(CommonConstants.STATUS,CommonConstants.NOT_REG_USER_MSG);
		sendResponse(response,jsonResponse);
		}
}
catch(Exception e) {
	jsonResponse.put(CommonConstants.STATUS,CommonConstants.PRBLMS_MSG);
	sendResponse(response,jsonResponse);
}
}*/