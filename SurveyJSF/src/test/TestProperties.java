package test;

import it.is.survey.SurveyProperties;
import java.util.Properties;
public class TestProperties {

	public static void main(String args[]){
		
		
		System.out.println("1");
		Properties p= SurveyProperties.getSurveyProperties();
		System.out.println(p.getProperty("PERSISTENCE_UNIT_NAME"));
		
	}
	
}
