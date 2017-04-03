package com.test.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.test.dto.Note;
import com.test.rest.RestURIs;

@Path(RestURIs.update)
public class UpdateResource {
	
	@Context 
	private HttpServletRequest request;
	
	@Context
	private HttpServletResponse response;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path(RestURIs.notes)
	public String updateNotes(Note[] notes){
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			JSONObject obj = new JSONObject();
			obj.put("user", request.getParameter("user"));
			return obj.toString();
		}
		catch(Exception e){
			JSONObject obj = new JSONObject();
			obj.put("error", e.getLocalizedMessage());
			return obj.toString();
		}
	}
}