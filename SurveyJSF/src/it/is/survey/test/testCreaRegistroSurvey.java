package it.is.survey.test;

import it.is.survey.facade.*;

public class testCreaRegistroSurvey {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        RegistroSurveyFacade rs=new RegistroSurveyFacade();
        System.out.println("registroSurvey:"+rs);
        rs.addSurveytoDB("crocchi@angelini.it", "Seconda  survey");
	}

}
