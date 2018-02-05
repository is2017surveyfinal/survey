package it.is.survey.jsf;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.is.survey.facade.StatisticsFacade;
import it.is.survey.model.Statistics;


public class StatisticsServiceOLD {
	
	
	public List<Statistics> createRealStatistics() {

		it.is.survey.model.Statistics statisticsModel = null;
		
		StatisticsFacade sf = new StatisticsFacade();
		List<Statistics> statisticsList = new LinkedList<Statistics>();
		List<it.is.survey.model.Statistics> listStatistics = (List<it.is.survey.model.Statistics>) sf.findAllStatistics();
		
		Iterator<it.is.survey.model.Statistics> iterateOverStatisticsModel = listStatistics.iterator();
		while (iterateOverStatisticsModel.hasNext()) {
			statisticsModel = iterateOverStatisticsModel.next();
			System.out.println(statisticsModel.getTitoloSurvey());
		}
		return listStatistics;
	}


}
