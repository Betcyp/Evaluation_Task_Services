package com.service1;

import org.json.JSONObject;

public class BaseRequest extends JSONObject {
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String pass;
	private String request;
	private JSONObject reqObj;
	
	
	public  BaseRequest(String request) {
		//this.request=request;
		this.reqObj=this.getJSONObject(request);
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
