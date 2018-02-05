package it.is.survey.jsf;





import javax.inject.Named;
import java.io.Serializable;



public class SurveyProducer implements Serializable {
	private static final long serialVersionUID = -1828660647917534556L;

	private enum Mode {
		EDIT, ADD
	}
	private Survey survey;
	private Mode mode;

	public Survey getSelectedSurvey() {
		return survey;
	}
	public void setSelectedSurvey(Survey survey) {
		this.survey = survey;
	}
	public boolean isAddMode() {
		return mode == Mode.ADD;
	}
	public void prepareAddSurvey() {
		this.survey = new Survey();
		this.mode = Mode.ADD;
	}
	public void prepareEditSurvey(Survey survey) {
		this.survey = survey;
		this.mode = Mode.EDIT;
	}
}

