package com.test.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.test.resources.CreateResource;
import com.test.resources.DeleteResource;
import com.test.resources.ListResource;
import com.test.resources.UpdateResource;

@ApplicationPath(RestURIs.api)
public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(ListResource.class);
		classes.add(CreateResource.class);
		classes.add(DeleteResource.class);
		classes.add(UpdateResource.class);
		return classes;
	}
}
