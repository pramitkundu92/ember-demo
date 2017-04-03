package com.test.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.test.services.UserService;
import com.test.util.Utils;
import com.mongodb.BasicDBObject;
import com.test.rest.RestURIs;

@Path(RestURIs.delete)
public class DeleteResource {
	
	@Context 
	private HttpServletRequest request;
	
	@Context
	private HttpServletResponse response;
	
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	@Path(RestURIs.users)	
	public String deleteUser(){
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {			
			BasicDBObject query = Utils.convertMapToBasicObject(request.getParameterMap());
			return UserService.deleteUser(query).toString();
		}
		catch(Exception e){
			JSONObject obj = new JSONObject();
			obj.put("error", e.getLocalizedMessage());
			return obj.toString();
		}
	}
}