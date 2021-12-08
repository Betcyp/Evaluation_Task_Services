package com.service1;
import org.json.JSONObject;

public class Response extends JSONObject{

	
	private JSONObject jsonResObj;
	
	String status;
	
	/*public Response(BaseRequest baseReq) {
		
		if(baseReq!=null) {
			JSONObject jr=responseAdding(status, "abc");
			return jr;
		}
		return baseReq;
	}*/
	
	public void setStatus(String status) {
		this.status = status;
	
	}
	public String getStatus() {
		return this.status;
	
	}
	
	/*public JSONObject responseAdding(String key,Object value) {
		this.jsonResObj.put(key, value);
		return this.jsonResObj;
	}*/
	
	

}
