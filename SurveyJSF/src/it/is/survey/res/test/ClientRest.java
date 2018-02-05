package it.is.survey.res.test;

import java.io.IOException;

import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class ClientRest {
	
public static void main(String args[]){
	
	
	
	try {
		sendPostRequest("https://survey-175512.appspot.com/rest/upload/");
	} catch (ResourceException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void sendPostRequest(final String uri) throws ResourceException, IOException {
	
	
	 try {
	  ClientResource client = new ClientResource(uri);
	  StringRepresentation entity = new StringRepresentation("<xml> test </xml>"); //Turn DOM into string and then instantiate StringRepresentation
	  entity.setCharacterSet(null); 
	  entity.setMediaType(MediaType.TEXT_XML);
	  // Viene effettuato il post dellla stringa
	  client.post(entity); 
	 }
	 catch (Exception ex) {
	  ex.printStackTrace();
	 }
}


}
