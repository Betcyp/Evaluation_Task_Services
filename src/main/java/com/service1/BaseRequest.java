package com.service1;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.constants1.CommonConstants;

public class BaseRequest extends JSONObject {
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String pass;
	private HttpServletRequest request;
	private JSONObject reqObj;
	
	
	public  BaseRequest(String request) {
		//this.request=request;
		this.reqObj=this.getJSONObject(request);
		
	}
	public void setFirstName(String fName) {
		this.reqObj=this.getJSONObject(fName);
		String firstName=(String) reqObj.get(CommonConstants.FIRSTNAME);
		this.firstName = firstName;
	}
	public void setLastName(String lName) {
		this.reqObj=this.getJSONObject(lName);
		String lastName=(String) reqObj.get(CommonConstants.LASTNAME);
		this.lastName = lastName;
	}
	public void setPhoneNumber(String phNumber) {
		this.reqObj=this.getJSONObject(phNumber);
		String phoneNumber=(String) reqObj.get(CommonConstants.PHONENUMBER);
		this.phoneNumber = phoneNumber;
	}
	public void setEmail(String em) {
		this.reqObj=this.getJSONObject(em);
		String email=(String) reqObj.get(CommonConstants.EMAIL);
		this.email = email;
	}
	public void setPass(String p) {
		this.reqObj=this.getJSONObject(p);
		String pass=(String) reqObj.get(CommonConstants.PASSWORD);
		this.pass = pass;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public String getEmail() {
		return this.email;
	}
	public String getPass() {
		return this.pass;
	}
	
}
