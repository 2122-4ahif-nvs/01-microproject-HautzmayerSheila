package at.htl.control;

import at.htl.entity.Person;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class PersonRepositoryTest {
    @Inject
    PersonRepository personRepository;

    @Test
    void createPersonWithPersist() {
        Person p = new Person();
        p = personRepository.save(p);
        assertThat(p.getId()).isNotNull();
        System.out.println(p);
    }

    @Test
    void findPersonByName_ok() {

        Person person = personRepository.findPersonByName("Sheila");
        assertThat(person.getFirstName()).isEqualTo("Sheila");

    }

}
