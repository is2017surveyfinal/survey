package it.is.survey.jsf;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import it.is.survey.facade.SurveyFacade;

public class ListSurveyController implements Serializable {
	private static final long serialVersionUID = 8693277383648025822L;

	@Inject
	private SurveyProducer surveyProducer;
	private SurveyListProducer surveyListProducer;

	public ListSurveyController() {

		surveyProducer = new SurveyProducer();

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("surveyProducer", surveyProducer);

		surveyListProducer = (SurveyListProducer) context.getExternalContext().getSessionMap()
				.get("surveyListProducer");

	}

	public String doAddSurvey() {

		surveyProducer.prepareAddSurvey();
		return Pages.EDIT_SURVEYS;
	}

	public String doEditSurvey(Survey survey) {

		surveyProducer.prepareEditSurvey(survey);
		return Pages.EDIT_SURVEYS;
	}

	public void doDeleteSurvey(Survey survey) {
		surveyProducer.setSelectedSurvey(survey);
		SurveyFacade sf = new SurveyFacade();
		sf.deleteSurveyById(surveyProducer.getSelectedSurvey().getId());
		surveyListProducer.getSurveys().remove(survey);
	}

	public String doListQuestions(Survey survey) {
		surveyProducer.setSelectedSurvey(survey);

		return Pages.LIST_QUESTIONS;
	}

	public String doViewStatistics(Survey survey) {
		
		surveyProducer.setSelectedSurvey(survey);
		

		return Pages.VIEW_STATS;
	}
	
}
