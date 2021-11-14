package at.htl.control;

import at.htl.entity.CoursePerson;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class CoursePersonRepository {
    @Inject
    EntityManager entityManager;

    @Transactional
    public CoursePerson save(CoursePerson coursePerson){
        return entityManager.merge(coursePerson);
    }
}
