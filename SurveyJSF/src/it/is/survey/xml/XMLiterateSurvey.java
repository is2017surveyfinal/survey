package it.is.survey.xml;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class XMLiterateSurvey {

	private static final String PERSISTENCE_UNIT_NAME = "Survey";
    private static EntityManagerFactory factory;
    private static EntityManager em;
     
    
    public XMLiterateSurvey(){
   	 factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
      
    }
	
    
    public String list(String fruitore){
    	
    	String result="<sondaggi> \n";
    	Query q=em.createNativeQuery("SELECT  s.id,s.xml FROM SURVEY s where published=1 and s.id not in (SELECT idsurvey FROM sql11203723.STATISTICS where idfruitore='"+fruitore+"')");
    	//Query q = em.createNativeQuery("SELECT  s.id,s.xml FROM SURVEY s where published=1");
    	List<Object[]> surveyXMLText = q.getResultList();
    	 
    	for (Object[] s : surveyXMLText) {
    	  
    	    result=result+s[1];
    	}
    	result=result+"</sondaggi>";
		return result;
    	
    }
    
    
    
}
