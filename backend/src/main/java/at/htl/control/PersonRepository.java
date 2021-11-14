package at.htl.control;

import at.htl.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonRepository {
    @Inject
    EntityManager em;

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
        return em.find(Person.class, id);
    }

    public Person findPersonByIdNamedQuery(){
        TypedQuery<Person> query = em
                .createNamedQuery("Person.findPersonById",Person.class).setParameter("id", 1L);

        Person person = query.getSingleResult();
        return person;

    }

}
