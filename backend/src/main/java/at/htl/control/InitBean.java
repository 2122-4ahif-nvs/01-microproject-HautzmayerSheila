package at.htl.control;

import at.htl.entity.*;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InitBean {

    @Inject
    PersonRepository personRepository;
    @Inject
    CourseRepository courseRepository;
    @Inject
    AppointmentRepository appointmentRepository;
    @Inject
    CoursePersonRepository coursePersonRepository;

    void init(@Observes StartupEvent startupEvent){


        Person person1 = new Person("Sheila", "Hautzi");
        Person person2 = new Person("Julian", "Dummi");
        Person person3 = new Person("Anna", "Hartl");
        Person person4 = new Person("Jogi", "Pelz");
        person1 = personRepository.save(person1);
        person2 = personRepository.save(person2);
        person3 = personRepository.save(person3);
        person4 = personRepository.save(person4);

        Course course1 = new Course("Jazz", 5,  "Test");
        Course course2 = new Course("Modern", 10,  "Test");
        course1= courseRepository.save(course1);
        course2= courseRepository.save(course2);

        Appointment appointment1 = new Appointment(LocalDateTime.now(), LocalDateTime.now(), course1);
        Appointment appointment2= new Appointment(LocalDateTime.now(), LocalDateTime.now(), course2);
        Appointment appointment3= new Appointment(LocalDateTime.now(), LocalDateTime.now(), course2);
        Appointment appointment4 = new Appointment(LocalDateTime.now(), LocalDateTime.now(), course1);
        /*List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(LocalDateTime.now(), LocalDateTime.now(), course1));
        appointments.add(new Appointment(LocalDateTime.now(), LocalDateTime.now(), course1));
        appointments.add(new Appointment(LocalDateTime.now(), LocalDateTime.now(), course1));*/
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentRepository.save(appointment3);
        appointmentRepository.save(appointment4);

        CoursePerson coursePerson1 = new CoursePerson(PersonType.STUDENT, course1, person1);
        CoursePerson coursePerson2 = new CoursePerson(PersonType.TEACHER, course1, person2);
        coursePerson1 = coursePersonRepository.save(coursePerson1);
        coursePerson2 = coursePersonRepository.save(coursePerson2);

       /* for (Appointment appointment : appointments) {
            appointmentRepository.save(appointment);
        }

        course1.getAppointments().addAll(appointments);
        courseRepository.save(course1);*/

    }
}
