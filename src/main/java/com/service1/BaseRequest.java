package com.service1;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

public class BaseRequest extends JSONObject {
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String pass;
	private JSONObject reqObj;
	private JSONObject data;
	private String request;
	
	
	/*public BaseRequest(String firstName , String lastName , String email, String phoneNumber,String pass) {
		  this.firstName = firstName;
		  this.lastName = lastName;
		  this.email = email;
		  this.phoneNumber = phoneNumber;
		  this.pass = pass;
		  
		 }*/
	
	public  BaseRequest(String request) {
		
		this.request=request;
		this.reqObj=this.getJSONObject(request);
		
	}
	

	public JSONObject setData() {
		return this.data = this.getJSONObject(request);
	}
	public JSONObject getData() {
		return this.data;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public void setReqObj(JSONObject reqObj) {
		this.reqObj = reqObj;
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
	public JSONObject getReqObj() {
		return this.reqObj;
	}
	
}
