package com.service1;

import org.json.JSONObject;

public class BaseRequest extends JSONObject {

	public static final String REQUEST = "request";
	public static final String DATA = "data";
	
	private JSONObject jsonReqObj;
	
	public BaseRequest(String request) {
		
		jsonReqObj=new JSONObject(request);
	
	}


}
