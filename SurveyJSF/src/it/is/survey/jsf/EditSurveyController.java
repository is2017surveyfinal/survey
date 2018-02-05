package it.is.survey.jsf;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import it.is.survey.facade.SurveyFacade;

import java.io.Serializable;

public class EditSurveyController implements Serializable {
	private static final long serialVersionUID = 2815796004558360299L;

	@Inject
	private SurveyListProducer surveyListProducer;
	@Inject
	private SurveyProducer surveyProducer;

	public EditSurveyController() {

		surveyListProducer = new SurveyListProducer();
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("surveyListProducer", surveyListProducer);
		surveyProducer = (SurveyProducer) context.getExternalContext().getSessionMap().get("surveyProducer");
	}

	public String doSave() {
		if (surveyProducer.isAddMode()) {
			surveyListProducer.getSurveys().add(surveyProducer.getSelectedSurvey());
			SurveyFacade sf = new SurveyFacade();
			surveyProducer.getSelectedSurvey().setId(sf.createSurvey(surveyProducer.getSelectedSurvey().getTitle()));

		}
		return Pages.LIST_SURVEYS;
	}

	public String doCancel() {
		return Pages.LIST_SURVEYS;
	}
}