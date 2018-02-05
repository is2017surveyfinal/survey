package it.is.survey.jsf;

public class QuestionProducer {
	private enum Mode {
		EDIT, ADD
	}

	private Question question;
	private Mode mode;

	public Question getSelectedQuestion() {
		return question;
	}

	public void setSelectedQuestion(Question question) {
		this.question = question;
	}

	public boolean isAddMode() {
		return mode == Mode.ADD;
	}

	public void prepareAddQuestion() {
		this.question = new Question();
		this.mode = Mode.ADD;
	}

	public void prepareEditSurvey(Survey survey) {
		this.question = question;
		this.mode = Mode.EDIT;
	}
}
