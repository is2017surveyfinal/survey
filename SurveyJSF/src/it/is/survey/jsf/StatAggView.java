package it.is.survey.jsf;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

import javax.faces.context.FacesContext;

import it.is.survey.model.Statistics;
import it.is.survey.facade.*;


public class StatAggView implements Serializable{
	
	private List<StatAgg> statsAgg;
	private StatisticsFacade sf=new StatisticsFacade();
	private FacesContext context;
    
	public  StatAggView() {
		//recupero dal contestol a survey selezionata ed effetuo la query 
		context = FacesContext.getCurrentInstance();
    	SurveyProducer slp=(SurveyProducer) context.getExternalContext().getSessionMap().get("surveyProducer");
    	System.out.println("******Survey Selezionata:"+slp.getSelectedSurvey().getId());
        statsAgg = sf.getStatAgg(slp.getSelectedSurvey().getId());
        
    }
     
    public List<StatAgg> getStatsAgg() {
        return statsAgg;
    }
 	
        
}