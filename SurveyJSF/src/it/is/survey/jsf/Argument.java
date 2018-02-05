package it.is.survey.jsf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.is.survey.facade.ArgumentFacade;

public class Argument {
	
	private long Id;
	private String testo;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	public List countryList() {        

        ArrayList<Argument> ArgumentList = new ArrayList<Argument>();
        ArgumentFacade af=new ArgumentFacade();
        af.findAllArgument();
               
        
        
        return ArgumentList; 

    }
	
 public String toString(){
	 return getTesto();
 }
	
}
