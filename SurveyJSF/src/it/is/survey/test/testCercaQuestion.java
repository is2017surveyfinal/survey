package it.is.survey.test;

import it.is.survey.facade.SurveyFacade;

public class testCercaQuestion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SurveyFacade sa=new SurveyFacade();
        System.out.println(sa.findQuestionByID(51).getTesto());
        
        
	}

}
