package at.htl.control;

import at.htl.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
                .createNamedQuery("Person.findPersonById", Person.class).setParameter("id", 1L);

        Person person = query.getSingleResult();
        return person;

    }

    public Person findPersonByName(String name){
        TypedQuery<Person> query = em
                .createNamedQuery("Person.findPersonByName", Person.class).setParameter("name", name);

        Person person = query.getSingleResult();
        return person;

    }

    @Transactional
    public void deletePersonById(Long id){
        em.remove(findPersonById(id));
    }

}
