package it.is.survey.model;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

/**
 * Entity implementation class for Entity: Question
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Question.findById",
                query="SELECT q FROM Question q where q.Id= :Id"),
   
}) 

public class Question implements Serializable {

	   
	@Id
	@GeneratedValue  
	private long Id;
	private String Testo;
	@Lob
	private byte[] image;
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	/*public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}*/

	private static final long serialVersionUID = 1L;
	/*@ManyToOne    
	private Survey survey;*/
	@OneToMany(orphanRemoval = true, cascade = PERSIST)
	private List<Answer> AnswerList;
	@ManyToOne(cascade = PERSIST)
	private Argument argument;
	
	/*
	public Survey getSurveyList() {
		return survey;
	}
	public void setSurveyList(Survey survey) {
		this.survey = survey;
	}*/
	public List<Answer> getAnswerList() {
		return AnswerList;
	}
	public void setAnswerList(List<Answer> answerList) {
		AnswerList = answerList;
	}
	public Argument getArgument() {
		return argument;
	}
	public void setArgument(Argument argument) {
		this.argument = argument;
	}



	public Question() {
		super();
	}   
	public long getId() {
		return this.Id;
	}

	public void setId(long Id) {
		this.Id = Id;
	}   
	public String getTesto() {
		return this.Testo;
	}

	public void setTesto(String Testo) {
		this.Testo = Testo;
	}
   
	public void createAnswer(String AnswerText)
	{
		
		Answer a= new Answer();
		a.setTesto(AnswerText);
		AnswerList.add(a);
		
		
	}
	
}
