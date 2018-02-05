package it.is.survey.jsf;

import java.io.Serializable;

import javax.faces.context.FacesContext;

public class ListQuestionsController implements Serializable {
	private static final long serialVersionUID = 437878972432L;
	private QuestionProducer questionProducer;

	public ListQuestionsController() {
		questionProducer = new QuestionProducer();
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("surveyProducer", questionProducer);

	}

	public String doCancel() {
		return Pages.LIST_SURVEYS;
	}

	public String doAdd() {

		questionProducer.prepareAddQuestion();

		return Pages.LIST_SURVEYS;
	}

}
