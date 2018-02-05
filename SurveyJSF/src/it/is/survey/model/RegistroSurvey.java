package it.is.survey.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: RegistroSurvey
 *
 */
@Entity

public class RegistroSurvey implements Serializable {

	   
	@Id
	@GeneratedValue
	private long Id;
	@Column(columnDefinition = "MEDIUMTEXT")
	private String SurveyXML;
	private static final long serialVersionUID = 1L;
/*
	private String idFruitore;
	public String getIdFruitore() {
		return idFruitore;
	}
	public void setIdFruitore(String idFruitore) {
		this.idFruitore = idFruitore;
	}
	public String getIdSurvey() {
		return idSurvey;
	}
	public void setIdSurvey(String idSurvey) {
		this.idSurvey = idSurvey;
	}
	public String getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(String idQuestion) {
		this.idQuestion = idQuestion;
	}
	public String getIdAnswer() {
		return idAnswer;
	}
	public void setIdAnswer(String idAnswer) {
		this.idAnswer = idAnswer;
	}
	public String getTitoloSurvey() {
		return titoloSurvey;
	}
	public void setTitoloSurvey(String titoloSurvey) {
		this.titoloSurvey = titoloSurvey;
	}
	public String getTitoloDomanda() {
		return titoloDomanda;
	}
	public void setTitoloDomanda(String titoloDomanda) {
		this.titoloDomanda = titoloDomanda;
	}
	public String getTitoloRisposta() {
		return titoloRisposta;
	}
	public void setTitoloRisposta(String titoloRisposta) {
		this.titoloRisposta = titoloRisposta;
	}

	private String idSurvey;
	private String idQuestion;
	private String idAnswer;
	private String titoloSurvey;
	private String titoloDomanda;
	private String titoloRisposta;
	
	
	/*@ManyToMany
	private List<Fruitore> fruitoreList;
	
	public List<Fruitore> getFruitoreList() {
		return fruitoreList;
	}




	public void setFruitoreList(List<Fruitore> fruitoreList) {
		this.fruitoreList = fruitoreList;
	}




	public void AddSurveyToDB(Fruitore fruitore, String surveyXML) {
		
		
		
	}
		
	*/
	
	
	public RegistroSurvey() {
		super();
	}   
	public long getId() {
		return this.Id;
	}

	public void setId(long Id) {
		this.Id = Id;
	}   
	public String getSurveyXML() {
		return this.SurveyXML;
	}

	public void setSurveyXML(String SurveyXML) {
		this.SurveyXML = SurveyXML;
	}
   
}
