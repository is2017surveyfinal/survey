package it.is.survey.res;

import org.restlet.representation.FileRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.common.io.CharStreams;

import it.is.survey.xml.XMLiterateSurvey;

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

// @Path("sondaggi")
public class SondaggiResource extends ServerResource {

	@Get(value = "xml")
	public String represent() {

		ServletContext servletContext = (ServletContext) this.getContext().getAttributes()
				.get("org.restlet.ext.servlet.ServletContext");
		String idFruitore = ((String) this.getRequestAttributes().get("fruitoreId"));
		String rootPath = servletContext.getRealPath("WEB-INF/sondaggi.xml");
		String risultato = "";
		FileInputStream input = null;
		XMLiterateSurvey i = new XMLiterateSurvey();
		String myString = i.list(idFruitore);
		
		System.out.println("myString:" + myString);

		return myString;
	}

}