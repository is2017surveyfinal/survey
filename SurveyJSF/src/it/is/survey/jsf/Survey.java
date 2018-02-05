package it.is.survey.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

public class Survey {
	private int id;
	private String title;
	List<Question> questions;
	private boolean published;

	@PostConstruct
	public void init() {
		questions = new ArrayList<Question>();

	}

	public Survey() {

		questions = new ArrayList<Question>();

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void remove(Question question) {
		questions.remove(question);
	}

	public void save() {

	}

	public void addQuestion() {
		questions.add(new Question());
	}

	public void addQuestion(Question q) {
		questions.add(q);
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

}
