package com.test.util;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class Utils {
	
	public static final String mongoUrl = "mongodb://localhost:27017";
	public static final String database = "llp-user";
	public static final String users = "users";
	public static final String projects = "projects";
	public static final String notes = "notes";
	public static MongoClient dbConn = null;
	
	static {
		try {
			dbConn = new MongoClient(new MongoClientURI(mongoUrl));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static DBCollection getCollection(String collectionName){
		return dbConn.getDB(database).getCollection(collectionName);
	}
	
	public static JSONObject convertMapToJson(Map<String,String[]> map){
		JSONObject obj = new JSONObject();
		for(Map.Entry<String,String[]> e: map.entrySet()){
			if(e.getValue().length == 1){
				obj.put(e.getKey(), e.getValue()[0]);
			}
			else {
				obj.put(e.getKey(), e.getValue());
			}
		}
		return obj;
	}
	
	public static Map<String,String> convertJsonToMap(JSONObject obj){
		Map<String,String> map = new HashMap<String,String>();
		for(Object key : obj.keySet()){
			map.put(key.toString(), obj.getString(key.toString()));
		}
		return map;
	}
	
	public static BasicDBObject convertMapToBasicObject(Map<String,String[]> map){
		BasicDBObject obj = new BasicDBObject();
		for(Map.Entry<String,String[]> e: map.entrySet()){
			if(e.getValue().length == 1){
				obj.put(e.getKey(), e.getValue()[0]);
			}
			else {
				obj.put(e.getKey(), e.getValue());
			}
		}
		return obj;
	}
}
