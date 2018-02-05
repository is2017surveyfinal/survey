package it.is.survey.facade;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import it.is.survey.model.RegistroSurvey;
import it.is.survey.model.Statistics;
import it.is.survey.model.Survey;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class RegistroSurveyFacade {

	private static final String PERSISTENCE_UNIT_NAME = "Survey";
	private static EntityManagerFactory factory;
	private static EntityManager em;
	private String idSondaggioToInsert = "";
	private String idFruitoreToInsert = "";
	private String idDomandaToInsert = "";
	private String idRispostaToInsert="";
	private String argomentoToInsert="";
	private String titoloSurveyToInsert = "";
	private String testoDomandaToInsert="";
	private String testoRispostaToInsert="";
	
	

	public RegistroSurveyFacade() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
	}

	public void addSurveytoDB(String Fruitore, String survey) {
		em.getTransaction().begin();
		RegistroSurvey rs = new RegistroSurvey();
		manageStatistics(survey);
		rs.setSurveyXML(survey);
		
	    em.persist(rs);
		em.getTransaction().commit();
		em.close();
	}

	
	private void manageStatistics(String survey) {
		try {
			InputStream surveyStream = new ByteArrayInputStream(survey.getBytes(StandardCharsets.UTF_8));
			Document document;
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(surveyStream);
			NodeList nodeList = document.getDocumentElement().getChildNodes();
			// System.out.println("document:"+document.getDocumentElement().getTagName());
			idSondaggioToInsert = document.getDocumentElement().getAttribute("ids");
			System.out.println("***** id sondaggio to insert: " + idSondaggioToInsert);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					if (node.getNodeName().equals("titolo"))
						titoloSurveyToInsert = node.getTextContent();
					if (node.getNodeName().equals("fruitore"))
						idFruitoreToInsert = node.getTextContent();
					// if (node.getNodeName().equals("domanda"))
					// idDomandaToInsert=node.getTextContent();
					System.out.println("*****titolo survey to insert: " + titoloSurveyToInsert);
					System.out.println("*****id Fruitore  to insert: " + idFruitoreToInsert);

					// System.out.println("nodo:"+node.getNodeName());
					// System.out.println("valore:"+node.getTextContent());
					// System.out.println("------------");
					if (node.getNodeName().equals("domanda")) {
						idDomandaToInsert = node.getAttributes().getNamedItem("idq").getNodeValue();
						// System.out.println("valore domanda:
						// "+node.getAttributes().getNamedItem("idq").getNodeValue());
						argomentoToInsert = elem.getElementsByTagName("argomento").item(0).getChildNodes()
								.item(0).getNodeValue();
						testoDomandaToInsert = elem.getElementsByTagName("testo").item(0).getChildNodes().item(0)
								.getNodeValue();
						
						System.out.println("*****argomento to insert: " + argomentoToInsert);
						System.out.println("*****id Domanda to insert: " + idDomandaToInsert);
						System.out.println("*****testo domanda to Insert: " + testoDomandaToInsert);
						// System.out.println("***numero
						// risposte"+elem.getElementsByTagName("risposta").getLength());
						for (int j = 0; j < elem.getElementsByTagName("risposta").getLength(); j++) {
                            System.out.println("Numero risposte:"+ elem.getElementsByTagName("risposta").getLength());
							if (elem.getElementsByTagName("risposta").item(j).getAttributes().getNamedItem("checked").getNodeValue().equals("true")) {
								testoRispostaToInsert = elem.getElementsByTagName("risposta").item(j).getChildNodes().item(0).getNodeValue();
								idRispostaToInsert = elem.getElementsByTagName("risposta").item(j).getAttributes().getNamedItem("id").getNodeValue();
								System.out.println("*****risposta(" + j + "): " + testoRispostaToInsert);
								System.out.println("*****idRisposta(" + j + "): " + idRispostaToInsert);
								Statistics st=new Statistics();
								st.setIdSurvey(idSondaggioToInsert);
								st.setArgomento(argomentoToInsert);
								st.setIdDomanda(idDomandaToInsert);
								st.setIdFruitore(idFruitoreToInsert);
								st.setIdRisposta(idRispostaToInsert);
								st.setTestoDomanda(testoDomandaToInsert);
								st.setTestoRisposta(testoRispostaToInsert);
								st.setTitoloSurvey(titoloSurveyToInsert);
								em.persist(st);
								System.out.println("lancia query!!!");
							}
						}

					}
					System.out.println("------------");

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Eccezione" + e);
		}

	}
	
	

}
