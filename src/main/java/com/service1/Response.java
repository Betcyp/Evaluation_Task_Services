package com.service1;
import org.json.JSONObject;

public class Response extends JSONObject{

	
	private JSONObject jsonResObj;
	String status;

	public Response() {
	
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public void setData(JSONObject jsonResObj) {
		this.jsonResObj=jsonResObj;
	}
	
	public String getStatus() {
		return this.status;
	}
	public JSONObject getData() {
		return this.jsonResObj;
	}
}
	


