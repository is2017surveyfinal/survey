package it.is.survey.facade;

import java.util.Iterator;
import org.apache.commons.beanutils.BeanMap;

public class SurveyMapperOLD {

	 	public BeanMap map (it.is.survey.jsf.Survey s){
	 		
	 		BeanMap map = new BeanMap(s);
	        
	        Iterator<String> it = map.keyIterator();
	        while (it.hasNext()) {
	            String key = it.next();
	            
	        }
	        
	 		return map;
	 	}

}
