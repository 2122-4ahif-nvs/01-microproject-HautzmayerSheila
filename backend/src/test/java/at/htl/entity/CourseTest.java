package at.htl.entity;

import at.htl.control.CourseRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.logging.Logger;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CourseTest {
    @Inject
    Logger logger;

    @Inject
    CourseRepository courseRepository;

    @Inject
    EntityManager entityManager;

    @Test
    public void createCourse(){
        Course course = new Course("Jazz", 11, "Test");
        courseRepository.save(course);
        assertThat(courseRepository.findCourseById(2L).getName()).isEqualTo("Jazz");
        logger.info(courseRepository.findCourseById(2L));
    }

    @Test
    public void getAllCourses(){
        List<Course> courses = courseRepository.getAllCourses();
        assertThat(courses.size()).isEqualTo(1);

        logger.info("Number of people: " + courses.size());
        logger.info(courses);

    }

    @Test
    public void deleteCourse(){
        courseRepository.deleteCourseById(1L);
        List<Course> courses = courseRepository.getAllCourses();
        assertThat(courses.size()).isEqualTo(0);

        logger.info("Number of people: " + courses.size());
    }


}
