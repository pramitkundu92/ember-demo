package com.test.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.test.util.Utils;

public class ProjectService {
	
	private static final DBCollection projects = Utils.getCollection(Utils.projects);
	
	public static List<DBObject> getProjects(BasicDBObject query){
		List<DBObject> list = new ArrayList<DBObject>();
		DBObject obj;
		for(String key: query.keySet()){
			query.put(key, Pattern.compile(query.get(key).toString(),Pattern.CASE_INSENSITIVE));
		}
		DBCursor cursor = projects.find(query);
		while(cursor.hasNext()){
			obj = cursor.next();	
			list.add(obj);
		}
		return list;
	}
}
