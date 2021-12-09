package com.service1;
import org.json.JSONObject;

public class Response extends JSONObject{

	
	private JSONObject jsonResObj;
	
	String status;

	
	public Response() {
		
		/*if(baseReq!=null) {
			JSONObject jr=responseAdding(status, "abc");
			return jr;
		}
		return baseReq;*/
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
	
	
	/*public JSONObject responseAdding(String key,Object value) {
		this.jsonResObj.put(key, value);
		return this.jsonResObj;
	}*/
	
	

}
