package at.htl.entity;

import at.htl.control.PersonRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PersonTest {

    @Inject
    EntityManager entityManager;

    @Inject
    Logger logger;

    @Inject
    PersonRepository personRepository;

    @Test
    public void createPerson(){
        Person person = new Person("Max", "Mustermann");
        personRepository.save(person);
        assertThat(personRepository.findPersonById(5L).getFirstName()).isEqualTo("Max");
        logger.info(personRepository.findPersonById(5L));
    }

    @Test
    public void getAllPeople(){
        List<Person> people = (List<Person>) entityManager.
                createNamedQuery("Person.findAllPeople", Person.class).getResultList();
        assertThat(people.size()).isEqualTo(3);

        logger.info("Number of people: " + people.size());
        logger.info(people);

    }

    @Test
    public void deletePerson(){
        personRepository.deletePersonById(4L);
        List<Person> people = (List<Person>) entityManager.
                createNamedQuery("Person.findAllPeople", Person.class).getResultList();
        assertThat(people.size()).isEqualTo(3);
        logger.info("Number of people: " + people.size());
    }

}
