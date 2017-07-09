package service;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import data.CountryCode;
import data.PhoneNumber;
import data.PhoneNumberList;


/**
 * The jax-rs service , implementing PhoneBook functionality.
 * 
 * @author Borislav
 *
 */
@Path("/phonebook")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class PhoneBookService {
	@Context
	private UriInfo uriInfo;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("catenate-phonebook");
	EntityManager em = emf.createEntityManager();

	@POST
	public Response create(PhoneNumber phoneNumber) {
		Response response = Response.notModified().build();
		if (phoneNumber == null)
			throw new BadRequestException();

		em.getTransaction().begin();
		em.persist(phoneNumber);
		em.getTransaction().commit();

		URI uri = uriInfo.getAbsolutePathBuilder().path(phoneNumber.getId().toString()).build();
		response = Response.created(uri).build();
		
		return response;
	}

	@PUT
	public Response update(PhoneNumber phoneNumber) {
		if (phoneNumber == null)
			throw new BadRequestException();

		em.getTransaction().begin();
		em.merge(phoneNumber);
		em.getTransaction().commit();

		return Response.ok().build();
	}

	@GET
	@Path("{id}")
	public Response get(@PathParam("id") String id) {
		PhoneNumber phoneNumber = em.find(PhoneNumber.class, Long.valueOf(id).longValue());

	    if (phoneNumber == null)
	      throw new NotFoundException();

	    return Response.ok(phoneNumber).build();
	}

/**
 * 
 * 
 * @return all phone records
 */
	@GET
	public Response getAll() {
	    TypedQuery<PhoneNumber> query = em.createNamedQuery(PhoneNumber.FIND_ALL, PhoneNumber.class);
	    PhoneNumberList phoneNumberList = new PhoneNumberList(query.getResultList());
		GenericEntity<List<PhoneNumber>> list = new GenericEntity<List<PhoneNumber>>(phoneNumberList) {};
		return Response.ok(list).build();
	}

	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") String id) {
		PhoneNumber phoneNumber = em.find(PhoneNumber.class, id);

	    if (phoneNumber == null)
	      throw new NotFoundException();

	    em.getTransaction().begin();
		em.remove(phoneNumber);
		em.getTransaction().commit();

	    return Response.noContent().build();
	}

}
