package com.test.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.test.dto.Note;
import com.test.util.Utils;

public class NoteService {
	
	private static final DBCollection notes = Utils.getCollection(Utils.notes);
	
	public static List<DBObject> getNotes(BasicDBObject query){
		List<DBObject> list = new ArrayList<DBObject>();
		DBObject obj,usr;
		for(String key: query.keySet()){
			query.put(key, Pattern.compile(query.get(key).toString(),Pattern.CASE_INSENSITIVE));
		}
		DBCursor cursor = notes.find(query);
		while(cursor.hasNext()){
			obj = cursor.next();
			usr = new BasicDBObject();
			usr.put("id", obj.get("user").toString());
			obj.put("userDetails", UserService.getUsers(usr).get(0));
			list.add(obj);
		}
		return list;
	}
	
	public static DBObject addNote(Note note){
		List<DBObject> list = new ArrayList<DBObject>();
		DBObject obj = new BasicDBObject();
		obj.put("text", note.getText());
		obj.put("user", note.getUser());
		obj.put("time", note.getTime());
		list.add(obj);
		WriteResult r = notes.insert(list);
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

