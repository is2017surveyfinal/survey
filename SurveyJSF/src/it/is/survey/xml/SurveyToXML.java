package it.is.survey.xml;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import it.is.survey.SurveyProperties;
import it.is.survey.jsf.ArgumentListProducer;
import it.is.survey.jsf.SurveyProducer;
import it.is.survey.model.Answer;
import it.is.survey.model.Argument;
import it.is.survey.model.Question;
import it.is.survey.model.Survey;

public class SurveyToXML {

	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private Document doc;

	public SurveyToXML(Survey survey) {
		try {

			Element questionElement = null;
			Element testoQuestionElement = null;
			Element argomentoQuestionElement = null;
			Element immagineQuestionElement = null;

			Element answerElement = null;
			Attr attrIdQuestion;
			Attr attrIdAnswer;
			Attr attrIdElement;

			Iterator<Question> iterateOverQuestion;
			Iterator<Answer> iterateOverAnswer;
			Question questionApp = null;
			Answer answerApp = null;

			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("sondaggio");
			doc.appendChild(rootElement);
			Attr attrId = doc.createAttribute("ids");

			attrId.setValue(String.valueOf(survey.getId()));
			rootElement.setAttributeNode(attrId);
			Element titoloSurvey = doc.createElement("titolo");
			titoloSurvey.appendChild(doc.createTextNode(survey.getTitle()));
			Element fruitoreSurvey = doc.createElement("fruitore");
			fruitoreSurvey.appendChild(doc.createTextNode("template"));
			rootElement.appendChild(titoloSurvey);
			rootElement.appendChild(fruitoreSurvey);

			iterateOverQuestion = survey.getQuestionList().iterator();

			while (iterateOverQuestion.hasNext()) {
				questionApp = iterateOverQuestion.next();
				questionElement = doc.createElement("domanda");
				attrIdQuestion = doc.createAttribute("idq");
				attrIdQuestion.setValue(String.valueOf(questionApp.getId()));
				questionElement.setAttributeNode(attrIdQuestion);

				rootElement.appendChild(questionElement);

				testoQuestionElement = doc.createElement("testo");
				testoQuestionElement.appendChild(doc.createTextNode(questionApp.getTesto()));
				argomentoQuestionElement = doc.createElement("argomento");
				argomentoQuestionElement.appendChild(doc.createTextNode(questionApp.getArgument().getTesto()));
				immagineQuestionElement = doc.createElement("immagine");
				if (questionApp.getImage().length != 0) {
					String hostname=SurveyProperties.getSurveyProperties().getProperty("IP_WEB_SERVER");
					String port = SurveyProperties.getSurveyProperties().getProperty("PORT_NUMBER");
					immagineQuestionElement.appendChild(
							
							doc.createTextNode("http://"+hostname+":"+port+"/SurveyJSF/rest/image/" + questionApp.getId()));

				} else {
					immagineQuestionElement.appendChild(doc.createTextNode("None" + questionApp.getId()));

				}

				questionElement.appendChild(testoQuestionElement);
				questionElement.appendChild(immagineQuestionElement);
				questionElement.appendChild(argomentoQuestionElement);

				iterateOverAnswer = questionApp.getAnswerList().iterator();
				while (iterateOverAnswer.hasNext()) {
					answerApp = iterateOverAnswer.next();
					answerElement = doc.createElement("risposta");
					answerElement.appendChild(doc.createTextNode(answerApp.getTesto()));
					attrIdAnswer = doc.createAttribute("id");
					attrIdAnswer.setValue(String.valueOf(answerApp.getId()));
					answerElement.setAttributeNode(attrIdAnswer);
					questionElement.appendChild(answerElement);

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public void writeToFile(Document doc) {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:\\fileSondaggio.xml"));

			transformer.transform(source, result);

			System.out.println("File saved!");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public String getXMLsurvey() {
		String xmlString = null;
		;
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);

			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);

			
			transformer.transform(source, result);
			xmlString = sw.toString();

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return xmlString;
	}

}
