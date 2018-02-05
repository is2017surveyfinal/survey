package it.is.survey.jsf;

import java.io.Serializable;
import java.util.List;
import it.is.survey.model.Statistics;
import it.is.survey.facade.*;


public class StatisticsView implements Serializable{
	
	private List<Statistics> stats;
	private StatisticsFacade sf=new StatisticsFacade();
    
	public  StatisticsView() {
        stats = sf.findAllStatistics();
        
    }
     
    public List<Statistics> getStats() {
        return stats;
    }
 	
        
}
