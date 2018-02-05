package it.is.survey.jsf;

import java.util.ArrayList;

import java.util.Collections;

import java.util.List;

 

import javax.faces.bean.ManagedBean;

 



public class AutoComplete {

    private String countryName;

    public String getCountryName() {

        return countryName;

    }

 

    public void setCountryName(String countryName) {

        this.countryName = countryName;

    }

 

    // Method To Display The Country List On The JSF Page

    public List countryList() {        

        ArrayList countryList = new ArrayList();
        countryList.add("India");
        countryList.add("Australia"); 
        countryList.add("Germany"); 
        countryList.add("Italy");
        countryList.add("United States Of America");
        countryList.add("Russia");
        countryList.add("Sweden");
        Collections.sort(countryList);
        return countryList; 

    }

}
