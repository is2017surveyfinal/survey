package it.is.survey.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Statistics
 *
 */




@Entity
@NamedQueries({
    @NamedQuery(name="Statistics.findAll",    query="SELECT s FROM Statistics s "),
    @NamedQuery(name="Statistics.findById",   query="SELECT s FROM Statistics s where s.titoloSurvey= :titoloSurvey"),
    
}) 

public class Statistics implements Serializable {

	   
	@Id
	@GeneratedValue
	
	private int Id;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}

	private String idSurvey;   
	private String idFruitore;   
	private String idDomanda;
	private String idRisposta;
	private String argomento;
	private String titoloSurvey;
	private String testoDomanda;
	private String testoRisposta;
	private static final long serialVersionUID = 1L;

	public Statistics() {
		super();
	}   
	public String getIdSurvey() {
		return this.idSurvey;
	}

	public void setIdSurvey(String idSurvey) {
		this.idSurvey = idSurvey;
	}   
	public String getIdFruitore() {
		return this.idFruitore;
	}

	public void setIdFruitore(String idFruitore) {
		this.idFruitore = idFruitore;
	}   
	public String getIdDomanda() {
		return this.idDomanda;
	}

	public void setIdDomanda(String idDomanda) {
		this.idDomanda = idDomanda;
	}   
	public String getIdRisposta() {
		return this.idRisposta;
	}

	public void setIdRisposta(String idRisposta) {
		this.idRisposta = idRisposta;
	}   
	public String getArgomento() {
		return this.argomento;
	}

	public void setArgomento(String argomento) {
		this.argomento = argomento;
	}   
	public String getTitoloSurvey() {
		return this.titoloSurvey;
	}

	public void setTitoloSurvey(String titoloSurvey) {
		this.titoloSurvey = titoloSurvey;
	}   
	public String getTestoDomanda() {
		return this.testoDomanda;
	}

	public void setTestoDomanda(String testoDomanda) {
		this.testoDomanda = testoDomanda;
	}   
	public String getTestoRisposta() {
		return this.testoRisposta;
	}

	public void setTestoRisposta(String testoRisposta) {
		this.testoRisposta = testoRisposta;
	}
   
}
