package at.htl.boundary;

import at.htl.control.CourseRepository;
import at.htl.entity.Course;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Course")
public class CourseResource {

    @Inject
    CourseRepository courseRepository;

    @Path("GetAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getAllCourses(){
        return courseRepository.getAllCourses();
    }
}
