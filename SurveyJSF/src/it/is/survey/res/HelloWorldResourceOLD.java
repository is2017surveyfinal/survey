package it.is.survey.res;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import javax.ws.rs.Path;




/**
 * Resource which has only one representation.
 *
 */


public class HelloWorldResourceOLD extends ServerResource {

    @Get
    public String represent() {
     
    	
    	
    	return "hello, world (from the cloud******!)";
   	
    	 
    }
    
  
}