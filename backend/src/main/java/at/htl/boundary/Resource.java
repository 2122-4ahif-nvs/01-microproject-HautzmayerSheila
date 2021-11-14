package at.htl.boundary;

import at.htl.control.PersonRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/FindAll")
public class Resource {
    @ConfigProperty(name = "greeting", defaultValue = "hi")
    String greeting;

    @Inject
    PersonRepository personRepository;

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


    @Path("/FindByIdNQ")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return personRepository.findPersonByIdNamedQuery().getFirstName();
    }
}
