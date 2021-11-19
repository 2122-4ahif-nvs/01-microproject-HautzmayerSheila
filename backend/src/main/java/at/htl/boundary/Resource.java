package at.htl.boundary;

import at.htl.control.CourseRepository;
import at.htl.control.PersonRepository;
import at.htl.entity.Course;
import at.htl.entity.Person;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/Person")
public class Resource {
    @ConfigProperty(name = "greeting", defaultValue = "hi")
    String greeting;

    @Inject
    Logger logger;

    @Inject
    PersonRepository personRepository;

    @Inject
    CourseRepository courseRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<String> findAll() {
        return personRepository.findAll().stream().map(person -> person.getFirstName()).collect(Collectors.toList());
    }

    @Path("/NamedQuery")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<String> findAllNamedQuery() {
        return personRepository.findAllNamedQuery().stream().map(person -> person.getFirstName()).collect(Collectors.toList());
    }

    @Path("/FindById/{id}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String findById(@PathParam("id") Long id) {
        return personRepository.findPersonById(id).getFirstName();
    }


    @Path("/FindByIdNq")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Person test() {
        return personRepository.findPersonByIdNamedQuery();
    }

    @Path("/FindAllCourses")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<Course> findAllCourses() {
        return courseRepository.getAllCourses();
    }

    @Path("AddPerson")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPersonWithJsonb(Person person) {

        personRepository.save(person);
        return Response.ok(personRepository.save(person)).build();
    }

    @POST
    @Path("/Add")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(MediaType.TEXT_PLAIN)
    public String addProduct(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName
    ) {
        Person p = new Person(firstName, lastName);
        personRepository.save(p);
        return p.toString();
    }
}
