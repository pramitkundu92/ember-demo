package com.test.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.test.dto.User;
import com.test.util.Utils;

public class UserService {
	
	private static final DBCollection users = Utils.getCollection(Utils.users);
	
	public static List<DBObject> getUsers(DBObject query){
		List<DBObject> list = new ArrayList<DBObject>();
		DBObject obj;
		for(String key: query.keySet()){
			if(!key.equals("id")){
				query.put(key, Pattern.compile(query.get(key).toString(),Pattern.CASE_INSENSITIVE));
			}
			else query.put(key, Long.parseLong(query.get(key).toString()));
		}
		DBCursor cursor = users.find(query);
		while(cursor.hasNext()){
			obj = cursor.next();	
			list.add(obj);
		}
		return list;
	}
	
	public static DBObject addUser(User user){
		List<DBObject> list = new ArrayList<DBObject>();
		DBObject obj = new BasicDBObject();
		obj.put("name", user.getName());
		obj.put("email", user.getEmail());
		obj.put("id", user.getId());
		obj.put("designation", user.getDesignation());
		obj.put("mobile", user.getMobile());
		obj.put("projectName", user.getProjectName());
		list.add(obj);
		WriteResult r = users.insert(list);
		obj = new BasicDBObject();
		if(r.getN()>0){
			obj.put("status", "success");		
		}
		else {
			obj.put("status", "error");	
		}
		return obj;
	}
	
	public static DBObject deleteUser(DBObject query){
		DBObject obj;
		WriteResult r;
		for(String key: query.keySet()){
			if(key.equals("id")){
				query.put(key, Long.parseLong(query.get(key).toString()));
			}
		}
		r = users.remove(query);
		obj = new BasicDBObject();
		if(r.getN()>0){
			obj.put("status", "success");		
		}
		else {
			obj.put("status", "error");	
		}
		return obj;
	}
}
