package at.htl.control;

import at.htl.entity.Person;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class InitBean {

    @Inject
    PersonRepository personRepository;

    void init(@Observes StartupEvent startupEvent){
        Person person1 = new Person("Sheila", "Hautzi");
        Person person2 = new Person("Julian", "Dummi");
        Person person3 = new Person("Anna", "Hartl");
        Person person4 = new Person("Jogi", "Pelz");
        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);
        personRepository.save(person4);
    }
}
