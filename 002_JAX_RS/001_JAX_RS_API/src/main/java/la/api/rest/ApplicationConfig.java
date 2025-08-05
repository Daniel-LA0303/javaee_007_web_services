package la.api.rest;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.ApplicationPath;

import java.util.Collections;
import java.util.Set;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
	
	 @Override
	    public Set<Class<?>> getClasses() {
	        return Collections.singleton(ConversorResource.class);
	    }
	
}