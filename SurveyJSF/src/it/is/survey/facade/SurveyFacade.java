package it.is.survey.facade;

import java.util.Iterator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.is.survey.model.Answer;
import it.is.survey.model.Argument;
import it.is.survey.model.Question;
import it.is.survey.model.Survey;
import it.is.survey.xml.SurveyToXML;

public class SurveyFacade {

	private static final String PERSISTENCE_UNIT_NAME = "Survey";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	public SurveyFacade() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();

	}

	public Survey findSurveyByName(String title) {
		Survey s = null;

		List<Survey> results = em.createNamedQuery("Survey.findByTitle").setParameter("title", title).getResultList();
		Iterator<Survey> IteratorSurvey = results.iterator();
		while (IteratorSurvey.hasNext()) {
			s = IteratorSurvey.next();
		}
		return s;
	}

	public List<it.is.survey.model.Survey> findAllSurvey() {

		List<Survey> results = em.createNamedQuery("Survey.findAll").getResultList();

		return results;

	}

	public Survey findSurveyById(long id) {

		Survey s = null;
		List<Survey> results = em.createNamedQuery("Survey.findById").setParameter("Id", id).getResultList();

		Iterator<Survey> IteratorSurvey = results.iterator();
		while (IteratorSurvey.hasNext()) {
			s = IteratorSurvey.next();
		}
		return s;
	}

	public void deleteSurveyById(long id) {

		Survey s = null;

		em.getTransaction().begin();
		Query query = em.createNamedQuery("Survey.deleteById").setParameter("Id", id);

		int deleted = query.executeUpdate();
		em.getTransaction().commit();

	}

	public Question findQuestionByID(long id) {

		Question s = null;
		List<Question> results = em.createNamedQuery("Question.findById").setParameter("Id", id).getResultList();
		Iterator<Question> IteratorQuestion = results.iterator();
		while (IteratorQuestion.hasNext()) {
			s = IteratorQuestion.next();
		}
		return s;
	}

	public int createSurvey(String title) {

		em.getTransaction().begin();

		Survey survey = new Survey();
		survey.setTitle(title);
		survey.setPublished(false);
		em.persist(survey);
		em.getTransaction().commit();

		return survey.getId();

	}

	public void addQuestionToSurvey(long surveyId, String questionText, byte[] image, List<String> listAnswerText,
			long idArgument) {
		Question q = null;
		Answer a = null;
		Argument arg = null;

		List<Argument> results = em.createNamedQuery("Argument.findById").setParameter("Id", idArgument)
				.getResultList();

		Iterator<Argument> IteratorArgument = results.iterator();
		while (IteratorArgument.hasNext()) {
			arg = IteratorArgument.next();

		}

		em.getTransaction().begin();

		Survey s = findSurveyById(surveyId);

		q = s.createQuestion(questionText);
		q.setArgument(arg);
		q.setImage(image);
		em.persist(s);
		Iterator<String> AnswerTextIterator = listAnswerText.iterator();
		while (AnswerTextIterator.hasNext()) {

			q.createAnswer(AnswerTextIterator.next());
		}

		em.persist(s);
		em.getTransaction().commit();

	}

	public void addQuestionToSurvey(long idSurvey, long idQuestion) {

		Question q = findQuestionByID(idQuestion);
		Survey s = findSurveyById(idSurvey);
		System.out.println("Start Transaction for Question adding...");
		em.getTransaction().begin();
		System.out.println("Add existing question Question.....");
		s.getQuestionList().add(q);
		em.persist(s);
		em.getTransaction().commit();

	}

	public void clearSurvey(long id) {

		Survey survey = this.findSurveyById(id);
		em.getTransaction().begin();
		survey.getQuestionList().clear();
		em.persist(survey);
		em.getTransaction().commit();
	}

	public void saveAsXML(long id) {

		String result;
		Survey survey;
		survey = findSurveyById(id);

		SurveyToXML stx = new SurveyToXML(survey);

		em.getTransaction().begin();
		survey.setXML(stx.getXMLsurvey());
		em.persist(survey);
		em.getTransaction().commit();

	}

	public void publish(long id) {

		Survey survey = this.findSurveyById(id);
		em.getTransaction().begin();
		survey.setPublished(true);
		em.persist(survey);
		em.getTransaction().commit();

	}

	public void unPublish(long id) {

		Survey survey = this.findSurveyById(id);
		em.getTransaction().begin();
		survey.setPublished(false);
		em.persist(survey);
		em.getTransaction().commit();

	}

	public boolean isPublish(long id) {

		Survey survey = this.findSurveyById(id);
		if (survey.isPublished())
			return true;
		else
			return false;

	}

}
