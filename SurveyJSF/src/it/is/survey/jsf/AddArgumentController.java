package it.is.survey.jsf;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;

import org.primefaces.model.UploadedFile;

import it.is.survey.facade.ArgumentFacade;
import it.is.survey.facade.SurveyFacade;

import javax.inject.Inject;

public class AddArgumentController implements Serializable {
		
	private static final long serialVersionUID = 5493038842003809106L;
	
	@Inject
	
	private ArgumentListProducer argumentListProducer;
    private UploadedFile uploadedImage;
	private FacesContext context;
		
		
		public AddArgumentController() {

			context = FacesContext.getCurrentInstance();
	    	argumentListProducer=(ArgumentListProducer) context.getExternalContext().getSessionMap().get("argumentListProducer");
		}
				
	public String save(Argument argument) {

		String redirectURL = "argument?faces-redirect=true";
		// Question nq=new Question();
		try {
			ArgumentFacade af = new ArgumentFacade();
			if (argument.getTesto().equals("")) {
				throw new NullPointerException("Titolo argomento non inserito non inserito");
			}
			af.createArgument(argument.getTesto());
			
			System.out.println("argomento creato:" + argument.getTesto());
			argumentListProducer.getArguments().add(argument);
			argument = null;
			context.getExternalContext().getSessionMap().put("argument", argument);
			System.out.println("lista argomenti:" + argumentListProducer.getArguments());
			return redirectURL;
		} catch (NullPointerException e) {
			System.out.println("**************Errore**********");
			FacesContext context = FacesContext.getCurrentInstance();
			if (argument.getTesto().equals("")) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di convalida ",
						"Campo argomento obbligatorio "));
			}

			redirectURL = null;
		}
		System.out.println("prima di redirect:"+redirectURL);
		return redirectURL;

	}

	public void remove(Argument argument)
		{
			argumentListProducer.getArguments().remove(argument);
			ArgumentFacade af=new ArgumentFacade();
		    af.deleteArgumentById(argument.getId());
		}
}

