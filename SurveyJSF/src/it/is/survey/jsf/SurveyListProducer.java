package it.is.survey.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import it.is.survey.facade.SurveyFacade;

public class SurveyListProducer implements Serializable {
	private static final long serialVersionUID = -182866064791747156L;

	private List<Survey> surveys;

	public SurveyListProducer() {

		surveys = createRealSurvey();
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public List<Survey> createRealSurvey() {

		Survey surveyJSF = null;
		Question questionJSF = null;
		Answer answerJSF = null;
		Argument argumentJSF = null;

		it.is.survey.model.Survey surveyModel = null;
		it.is.survey.model.Question questionModel = null;
		it.is.survey.model.Answer answerModel = null;
		it.is.survey.model.Argument argumentModel = null;

		SurveyFacade sf = new SurveyFacade();
		List<Survey> ls = new LinkedList<Survey>();

		List<it.is.survey.model.Survey> listSurvey = (List<it.is.survey.model.Survey>) sf.findAllSurvey();

		Iterator<it.is.survey.model.Survey> iterateOverSurveyModel = listSurvey.iterator();

		Iterator<it.is.survey.model.Question> iterateOverQuestionModel;

		while (iterateOverSurveyModel.hasNext()) {

			surveyModel = iterateOverSurveyModel.next();

			surveyJSF = new Survey();
			surveyJSF.setId(surveyModel.getId());
			surveyJSF.setTitle(surveyModel.getTitle());
			surveyJSF.setPublished(surveyModel.isPublished());

			iterateOverQuestionModel = surveyModel.getQuestionList().iterator();

			Iterator<it.is.survey.model.Answer> iterateOverAnswerModel;
			while (iterateOverQuestionModel.hasNext()) {

				questionModel = iterateOverQuestionModel.next();
				iterateOverAnswerModel = questionModel.getAnswerList().iterator();

				questionJSF = new Question();
				questionJSF.setTitle(questionModel.getTesto());
				questionJSF.setImage(questionModel.getImage());
				argumentJSF = new Argument();
				argumentJSF.setId(questionModel.getArgument().getId());
				argumentJSF.setTesto(questionModel.getArgument().getTesto());
				questionJSF.setArgument(argumentJSF);

				while (iterateOverAnswerModel.hasNext()) {
					answerModel = iterateOverAnswerModel.next();
					answerJSF = new Answer();
					answerJSF.setTitle(answerModel.getTesto());
					answerJSF.setId(answerModel.getId());
					questionJSF.getItems().add(answerJSF);
				}

				surveyJSF.getQuestions().add(questionJSF);
			}
			ls.add(surveyJSF);
		}

		return ls;
	}

	public void remove(Survey survey) {

		surveys.remove(survey);
		SurveyFacade sf = new SurveyFacade();
		sf.clearSurvey(survey.getId());
		sf.deleteSurveyById(survey.getId());

	}

	public List<Survey> createMockSurveys() {

		Survey survey1 = new Survey();
		survey1.setTitle("titolo survey1");
		Survey survey2 = new Survey();
		survey2.setTitle("titolo survey2");
		List<Survey> ret = new LinkedList<Survey>();
		ret.add(survey1);
		ret.add(survey2);

		return ret;
	}

	public void managePublish(Survey survey) {

		SurveyFacade sf = new SurveyFacade();

		if (survey.isPublished()) {
			sf.publish(survey.getId());
		} else {
			sf.unPublish(survey.getId());
		}
	}
}
