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

import com.test.services.NoteService;
import com.test.services.UserService;
import com.test.dto.Note;
import com.test.dto.User;
import com.test.rest.RestURIs;

@Path(RestURIs.create)
public class CreateResource {
	
	@Context 
	private HttpServletRequest request;
	
	@Context
	private HttpServletResponse response;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path(RestURIs.notes)	
	public String addNotes(Note note){
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {			
			return NoteService.addNote(note).toString();
		}
		catch(Exception e){
			JSONObject obj = new JSONObject();
			obj.put("error", e.getLocalizedMessage());
			return obj.toString();
		}
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path(RestURIs.users)	
	public String addUser(User user){
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {			
			return UserService.addUser(user).toString();
		}
		catch(Exception e){
			JSONObject obj = new JSONObject();
			obj.put("error", e.getLocalizedMessage());
			return obj.toString();
		}
	}
}