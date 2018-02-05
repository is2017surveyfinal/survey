package it.is.survey.jsf;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;

import org.primefaces.model.UploadedFile;

import it.is.survey.facade.ArgumentFacade;

import javax.inject.Inject;

public class AddQuestionController implements Serializable {

	private static final long serialVersionUID = 5493038842003809106L;

	@Inject
	private SurveyProducer surveyProducer;
	private ArgumentListProducer argumentListProducer;
	private UploadedFile uploadedImage;

	private Long surveyId;
	private Question question;

	public AddQuestionController() {

		FacesContext context = FacesContext.getCurrentInstance();
		surveyProducer = (SurveyProducer) context.getExternalContext().getSessionMap().get("surveyProducer");
		argumentListProducer = (ArgumentListProducer) context.getExternalContext().getSessionMap()
				.get("argumentListProducer");

	}

	public String save(Question question) {
		
		String redirectURL="sformquestion?faces-redirect=true";
		
		
        try{
        	
        if(question.getTitle().equals("")){
    		throw new NullPointerException("Titolo domanda non inserito");	
    	}	
        
        if(argumentListProducer.getArgumentName().equals("")){
    		throw new NullPointerException("Argomento domanda non inserito");	
    	}	
        
        System.out.println("*****test:"+argumentListProducer.getArgumentName().equals(""));
        
		Question nq = new Question();
		Argument argument = new Argument();
		ArgumentFacade af = new ArgumentFacade();
		System.out.println("0");
		
		long idArgomento = af.findIdArgumentByTesto(argumentListProducer.getArgumentName());
		argument.setId(idArgomento);
		
		argument.setTesto(argumentListProducer.getArgumentName());
		question.setArgument(argument);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove(question);
		System.out.println("1");
		FileUploaded fu = (FileUploaded) context.getExternalContext().getSessionMap().get("fileUploaded");
		InputStream is = null;
		if (fu != null) {
			System.out.println("fu");
			is = fu.getImageFile();
		} else
			System.out.println("test rip 3");
		byte[] bytes = new byte[0];

		try {
			if (is != null) {
				bytes = IOUtils.toByteArray(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		question.setImage(bytes);
		surveyProducer.getSelectedSurvey().addQuestion(question);
		nq.setImage(null);
		argumentListProducer.setArgumentName("");
		context.getExternalContext().getSessionMap().put("question", nq);
		System.out.println("redirect:"+redirectURL);
        }
        catch (NullPointerException e){
        	System.out.println("**************Errore**********");
        	FacesContext context = FacesContext.getCurrentInstance();
        	 if(question.getTitle().equals("")){
        		 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore di convalida ",  "Titolo domanda obbligatorio ")); 
        	 }
        	 
        	 if (argumentListProducer.getArgumentName().equals("")){
        		 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Errore di convalida ",  "Campo argomento obbligatorio "));
        	 }
        	     
        	
        	redirectURL=null;
        }
        System.out.println("prima di redirect:"+redirectURL);
		return redirectURL;
	}
}
