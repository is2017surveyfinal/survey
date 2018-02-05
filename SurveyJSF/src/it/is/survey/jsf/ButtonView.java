package it.is.survey.jsf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanMap;

import it.is.survey.facade.SurveyFacade;
//import it.is.survey.facade.SurveyMapper;
import it.is.survey.xml.*;

public class ButtonView {

	@Inject
	private SurveyProducer surveyProducer;
	private SurveyFacade sf = new SurveyFacade();

	public ButtonView() {
		FacesContext context = FacesContext.getCurrentInstance();
		surveyProducer = (SurveyProducer) context.getExternalContext().getSessionMap().get("surveyProducer");
	}

	public void save(ActionEvent actionEvent) {
		BeanMap map;
		Question q = null;
		List<String> answerList = new ArrayList<String>();
		addMessage("Data saved");
		//Eliminato il 17 gennaio 2018 
		//SurveyMapper sm = new SurveyMapper();
		//map = sm.map(surveyProducer.getSelectedSurvey());
		sf.clearSurvey(surveyProducer.getSelectedSurvey().getId());// pulisce la
		Iterator IterateOverQuestion = surveyProducer.getSelectedSurvey().getQuestions().iterator();
		Iterator<Answer> iterateOverAnswer = null;
        
		while (IterateOverQuestion.hasNext()) {
			q = (Question) IterateOverQuestion.next();
			iterateOverAnswer = q.getItems().iterator();
			while (iterateOverAnswer.hasNext()) {
				answerList.add(iterateOverAnswer.next().getTitle());
			}
			sf.addQuestionToSurvey(surveyProducer.getSelectedSurvey().getId(), q.getTitle(), q.getImage(), answerList,
					q.getArgument().getId());
			answerList.clear();
		}
		sf.saveAsXML(surveyProducer.getSelectedSurvey().getId());
	}

	public void cancel(ActionEvent actionEvent) {
		addMessage("Data updated");
	}

	public void delete(ActionEvent actionEvent) {
		addMessage("Data deleted");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
