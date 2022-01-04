package at.htl.control;

import at.htl.entity.Course;
import at.htl.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
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

    public Course findCourseById(Long id){
        return entityManager.find(Course.class, id);
    }

    public void addCourse(@Valid Course course) {
        this.save(course);
    }

    @Transactional
    public void deleteCourseById(Long id){
        entityManager.remove(findCourseById(id));
    }
}
