package it.is.survey.facade;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.is.survey.model.Argument;
import it.is.survey.model.Question;
import it.is.survey.model.Survey;

public class ArgumentFacade {

	private static final String PERSISTENCE_UNIT_NAME = "Survey";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	public ArgumentFacade() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
	}

	public Argument findArgumentByTesto(String testo) {
		Argument s = null;
		List<Argument> results = em.createNamedQuery("Argument.findByTesto").setParameter("testo", testo).getResultList();
		Iterator<Argument> IteratorArgument = results.iterator();
		while (IteratorArgument.hasNext()) {
			s = IteratorArgument.next();
		}
		return s;
	}

	public long findIdArgumentByTesto(String testo) {
		Argument s = null;
		List<Argument> results = em.createNamedQuery("Argument.findByTesto").setParameter("testo", testo).getResultList();
		Iterator<Argument> IteratorArgument = results.iterator();
		while (IteratorArgument.hasNext()) {
			s = IteratorArgument.next();
		}
		return s.getId();
	}

	public List<Argument> findAllArgument() {
		Argument s = null;
		List<Argument> results = em.createNamedQuery("Argument.findAll").getResultList();
		Iterator<Argument> IteratorArgument = results.iterator();
		while (IteratorArgument.hasNext()) {
			s = IteratorArgument.next();
		}
		return results;
	}

	public void createArgument(String testo) {
		em.getTransaction().begin();
		Argument argument = new Argument();
		argument.setTesto(testo);
		em.persist(argument);
		em.getTransaction().commit();
		em.close();
	}

	public void deleteArgumentById(long id) {
		Argument a = null;
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Argument.deleteById").setParameter("Id", id);
		int deleted = query.executeUpdate();
		em.getTransaction().commit();
	}

}
