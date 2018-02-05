package it.is.survey.test;

import java.util.ArrayList;
import java.util.List;

import it.is.survey.facade.SurveyFacade;

public class testAddQuestionToSurveyWithCreate {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SurveyFacade sa=new SurveyFacade();
        List<String> answers= new ArrayList();
        answers.add("risposta 1");
        answers.add("risposta 2");
        answers.add("risposta 3");
        
        sa.addQuestionToSurvey(1, "question ccc",null,answers,1);
        
        
        
	}
	
}
