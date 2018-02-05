package it.is.survey.res;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.lang3.StringUtils;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.fileupload.RestletFileUpload;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import it.is.survey.SurveyProperties;
import it.is.survey.facade.RegistroSurveyFacade;

public class UploadServerResource extends ServerResource {

	@Post
	public void handleQuery(Representation rep) {
		String hostname=SurveyProperties.getSurveyProperties().getProperty("IP_WEB_SERVER");
		if (rep == null) {
			this.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);

		} else {
			try {
				String repAsString = rep.getText();
                //System.out.println("*******************rapAsString:"+repAsString); 
				RegistroSurveyFacade rs = new RegistroSurveyFacade();
				rs.addSurveytoDB("", requestToXML(repAsString));
				System.out.println("http://"+hostname+"/SurveyJSF/rest/ack/true");
				getResponse().redirectSeeOther("http://"+hostname+"/SurveyJSF/rest/ack/true");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				getResponse().redirectSeeOther("http://"+hostname+"/SurveyJSF/rest/ack/false");
				e.printStackTrace();
			}
		}

	}

	
	private String requestToXML(String param) {

		String str = param;

		String result = str.substring(str.indexOf("<sondaggio "));
		
		String result2=StringUtils.substringBefore(result, "</sondaggio>");
		result2=result2+"</sondaggio>";
		//System.out.println("**************result2:*****"+result2);
		
        return result2;
	}
}
