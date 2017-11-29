package com.honeyshop.application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.honeyshop.provider.MyJacksonJsonProvider;
import com.honeyshop.resource.CommentResource;
import com.honeyshop.resource.UserResource;

@ApplicationPath("/rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(org.glassfish.jersey.jackson.JacksonFeature.class);
        //we could also use this: resources.add(com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);
        resources.add(MyJacksonJsonProvider.class);
        resources.add(CommentResource.class);
        resources.add(UserResource.class);

        return resources;
    }

    @Override
    public Set<Object> getSingletons() {
        return Collections.emptySet();
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.wadl.disableWadl", true);
        return properties;
    }
}
