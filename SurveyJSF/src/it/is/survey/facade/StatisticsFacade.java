package it.is.survey.facade;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.is.survey.jsf.StatAgg;
import it.is.survey.model.Statistics;


public class StatisticsFacade {

	private static final String PERSISTENCE_UNIT_NAME = "Survey";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	public StatisticsFacade() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
	}

	
	
	
	public List<StatAgg> getStatAgg(long id) {
		Query q = em.createNativeQuery("select idsurvey,titolosurvey,iddomanda,testodomanda,idrisposta,testorisposta,CAST(contatore as CHAR(50)) from stats where idsurvey="+id);
		List<Object[]> statistic = q.getResultList();
		List<StatAgg> lsa=new ArrayList<StatAgg>();
		StatAgg sa; 
		for (Object[] s : statistic) {
			sa =new StatAgg();
			sa.setIdSurvey((String)s[0]);
			sa.setTitoloSurvey((String)s[1]);
			sa.setIdDomanda((String)s[2]);
			sa.setTestoDomanda((String)s[3]);
			sa.setIdRisposta((String)s[4]);
			sa.setTestoRisposta((String)s[5]);
			System.out.println(s[6]);
			//sa.setContatore("1");
			sa.setContatore((String)s[6]);
			lsa.add(sa);
		    System.out.println("survey: "
		            + s[0]
		            + " "
		            + s[1]);
		}
		return lsa;
	}
	
	

	public List<Statistics> findAllStatistics() {
		Statistics s = null;
		List<Statistics> results = em.createNamedQuery("Statistics.findAll").getResultList();
		Iterator<Statistics> IteratorStatistics = results.iterator();
		while (IteratorStatistics.hasNext()) {
			s = IteratorStatistics.next();
		}
		return results;
	}

	

	public List<Statistics> findStatisticsById(String titoloSurvey) {
		Statistics a = null;
		Query query = em.createNamedQuery("Statistics.findById").setParameter("titoloSurvey", titoloSurvey);
		List<Statistics> results = query.getResultList();
		return results;
	
	}

}
