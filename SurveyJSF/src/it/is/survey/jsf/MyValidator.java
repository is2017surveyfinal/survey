package it.is.survey.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("it.is.survey.jsf.MyValidator")
public class MyValidator implements Validator{

  public void validate(FacesContext ct, UIComponent co, Object obj) throws ValidatorException { 
    if(!continueValidation()){
      return;
    }
    System.out.println("****In skip valiation: "+ obj.toString());
    // validat
  }

protected boolean continueValidation() {
    String skipValidator= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("skipValidator");
    if (skipValidator != null && skipValidator.equalsIgnoreCase("true")) {
      return false;
    }
    return true;
  } 





}