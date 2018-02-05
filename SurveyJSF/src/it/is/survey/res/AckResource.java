package it.is.survey.res;

import org.restlet.representation.FileRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.common.io.CharStreams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.ws.rs.Path;




/**
 * Resource which has only one representation.
 *
 */


public class AckResource extends ServerResource {

    @Get( value = "xml" )
    public String represent() {
    
    	// Extract the ServletContext from the attributes of RestletContext
        ServletContext servletContext = (ServletContext) this.getContext().getAttributes().get("org.restlet.ext.servlet.ServletContext");
       // Get the path of the config file relative to the WAR
        //String rootPath = servletContext.getRealPath("/WEB-INF/ack.xml");
        
        String status=(String) this.getRequestAttributes().get("status");
    	
        String risultato="<ack> <message> file trasmitted </message> </ack>";
        
        if (status.equals("false")) {
        	   risultato="<ack> <message> ERROR file NOT trasmitted </message> </ack>";
        }
        
        
		

		
    	
        
        
        return risultato;
    }

}