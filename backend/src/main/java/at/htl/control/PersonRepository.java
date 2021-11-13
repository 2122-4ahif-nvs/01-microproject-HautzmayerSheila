package at.htl.control;

import at.htl.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonRepository {
    @Inject
    EntityManager em;
    PersonRepository personRepository;

    @Transactional
    public Person save(Person person){
        return em.merge(person);
    }

    public List<Person> findAll(){
        return em.createQuery("Select p from Person p").getResultList();
    }

    public List<Person> findAllNamedQuery(){
        return em.createNamedQuery("Person.findAllPeople").getResultList();
    }

    public Person findPersonById(Long id){
       // return em.createNamedQuery("Person.findPersonById").setParameter("fId", 1);
        return em.find(Person.class, id);
    }

}
