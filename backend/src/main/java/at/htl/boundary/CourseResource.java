package at.htl.boundary;

import at.htl.control.CourseRepository;
import at.htl.entity.Course;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.print.Book;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("Course")
public class CourseResource {

    @Inject
    Validator validator;

    @Inject
    CourseRepository courseRepository;

    @Path("GetAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getAllCourses(){
        return courseRepository.getAllCourses();
    }


    @Path("/manual-validation")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result tryMeManualValidation(Course course) {
        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        if (violations.isEmpty()) {
            return new Result("Course is valid! It was validated by manual validation.");
        } else {
            return new Result(violations);
        }
    }

    @Path("/end-point-method-validation")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result tryMeEndPointMethodValidation(@Valid Course course) {
        return new Result("Course is valid! It was validated by end point method validation.");
    }

    @Path("/repo-method-validation")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result tryMeRepoMethodValidation(Course course) {
        try {
            courseRepository.addCourse(course);
            return new Result("Course is valid!");
        } catch (ConstraintViolationException e) {
            return new Result(e.getConstraintViolations());
        }
    }

    public static class Result {

        private String message;
        private boolean success;

        Result(String message) {
            this.success = true;
            this.message = message;
        }

        Result(Set<? extends ConstraintViolation<?>> violations) {
            this.success = false;
            this.message = violations.stream()
                    .map(cv -> cv.getMessage())
                    .collect(Collectors.joining(", "));
        }

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }

    }
}
