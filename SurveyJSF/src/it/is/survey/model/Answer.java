package it.is.survey.model;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.PERSIST;

/**
 * Entity implementation class for Entity: Answer
 *
 */
@Entity

public class Answer implements Serializable {

	
	private String testo;   
	@Id
	@GeneratedValue
	private long Id;
	private boolean checked;
	private static final long serialVersionUID = 1L;
	/*@ManyToOne(cascade = PERSIST)
	private Question question;*/

	public Answer() {
		super();
	}   
	public String getTesto() {
		return this.testo;
	}
/*
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}*/
	public void setTesto(String testo) {
		this.testo = testo;
	}   
	public long getId() {
		return this.Id;
	}

	public void setId(long Id) {
		this.Id = Id;
	}   
	public boolean getChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
   
}
