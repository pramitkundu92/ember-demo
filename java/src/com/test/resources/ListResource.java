package com.test.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.test.services.NoteService;
import com.test.services.ProjectService;
import com.test.services.UserService;
import com.test.util.Utils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.test.rest.RestURIs;

@Path(RestURIs.list)
public class ListResource {
	
	@Context 
	private HttpServletRequest request;
	
	@Context
	private HttpServletResponse response;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path(RestURIs.users)
	public String getUsers(){
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			BasicDBObject query = Utils.convertMapToBasicObject(request.getParameterMap());
			List<DBObject> users = UserService.getUsers(query);
			return users.toString();
		}
		catch(Exception e){
			JSONObject obj = new JSONObject();
			obj.put("error", e.getLocalizedMessage());
			return obj.toString();
		}
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path(RestURIs.projects)
	public String getAllProjects(){
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			BasicDBObject query = Utils.convertMapToBasicObject(request.getParameterMap());
			List<DBObject> projects = ProjectService.getProjects(query);
			return projects.toString();
		}
		catch(Exception e){
			JSONObject obj = new JSONObject();
			obj.put("error", e.getLocalizedMessage());
			return obj.toString();
		}
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path(RestURIs.notes)
	public String getNotes(){
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			BasicDBObject query = Utils.convertMapToBasicObject(request.getParameterMap());
			List<DBObject> notes = NoteService.getNotes(query);
			return notes.toString();
		}
		catch(Exception e){
			JSONObject obj = new JSONObject();
			obj.put("error", e.getLocalizedMessage());
			return obj.toString();
		}
	}
}
