package at.htl.control;

import at.htl.entity.Course;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CourseRepository {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Course save(Course course){
        Course course1 = entityManager.merge(course);
        entityManager.flush();
        return course1;
    }

    public List<Course> getAllCourses(){
        List<Course> courses = entityManager.createQuery("Select c from Course c").getResultList();
        return entityManager.createQuery("Select c from Course c").getResultList();
    }
}
