package it.is.survey.res;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.restlet.data.MediaType;
import org.restlet.representation.ByteArrayRepresentation;
import org.restlet.representation.FileRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class ImageServerResource extends ServerResource {

	private static final String PERSISTENCE_UNIT_NAME = "Survey";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	public ImageServerResource() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
	}

	@Get("image/jpeg")
	public void represent() {

		long id = Long.parseLong(((String) this.getRequestAttributes().get("imageId")));

		Query q = em.createNativeQuery("SELECT q.image FROM QUESTION q where q.id=" + id);

		Object imageXMLText = q.getResultList();

		List a = (List) imageXMLText;

		byte[] b = (byte[]) a.get(0);

		ByteArrayRepresentation bar = new ByteArrayRepresentation(b, MediaType.IMAGE_JPEG);
		getResponse().setEntity(bar);
	}
}
