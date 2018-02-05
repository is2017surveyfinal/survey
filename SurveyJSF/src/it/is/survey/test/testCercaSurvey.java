package it.is.survey.test;

import it.is.survey.facade.SurveyFacade;

public class testCercaSurvey {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SurveyFacade sa=new SurveyFacade();
       // System.out.println(sa.findSurveyByName("Survey 2").getTitle());
        System.out.println(sa.findSurveyById(1).getTitle());
       // System.out.println(sa.findAllSurvey());
	}

}
