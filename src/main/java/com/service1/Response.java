package com.service1;

import org.json.JSONObject;

public class Response extends JSONObject{

	
	private JSONObject jsonResObj;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String pass;
	private JSONObject status;
	
	public Response() {
		
	}
	public JSONObject getStatus() {
		return status;
	}
	public void setStatus(JSONObject status) {
		this.status = status;
	}
	
	
	/*public void responseAdding(String key,Object value) {
		this.jsonResObj.put(key, value);
	}*/
	
	

	


}
