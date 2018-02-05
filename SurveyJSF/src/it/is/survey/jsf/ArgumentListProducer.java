package it.is.survey.jsf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.is.survey.facade.ArgumentFacade;
import it.is.survey.facade.SurveyFacade;

public class ArgumentListProducer {

	private List<Argument> arguments;

	public List<Argument> getArguments() {
		return arguments;
	}

	public void setArguments(List<Argument> arguments) {
		this.arguments = arguments;
	}

	private String ArgumentName;

	public String getArgumentName() {

		return ArgumentName;

	}

	public void setArgumentName(String argumentName) {

		this.ArgumentName = argumentName;

	}

	public ArgumentListProducer() {
		arguments = createRealArgument();
	}

	public List<Argument> createRealArgument() {

		it.is.survey.model.Argument argumentModel = null;
		Argument argumentJSF = null;
		ArgumentFacade af = new ArgumentFacade();
		List<Argument> argumentListJSF = new LinkedList<Argument>();
		List<it.is.survey.model.Argument> listArgument = (List<it.is.survey.model.Argument>) af.findAllArgument();
		Iterator<it.is.survey.model.Argument> iterateOverArgumentModel = listArgument.iterator();
		while (iterateOverArgumentModel.hasNext()) {
			argumentModel = iterateOverArgumentModel.next();
			argumentJSF = new Argument();
			argumentJSF.setId(argumentModel.getId());
			argumentJSF.setTesto(argumentModel.getTesto());
			argumentListJSF.add(argumentJSF);
		}
		return argumentListJSF;
	}

	public void remove(Argument argument) {
		arguments.remove(argument);
		ArgumentFacade af = new ArgumentFacade();
		System.out.println("in remove argument:"+argument.getId());
		af.deleteArgumentById(argument.getId());
	}

}
