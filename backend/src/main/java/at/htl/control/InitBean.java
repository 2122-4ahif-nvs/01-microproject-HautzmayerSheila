package at.htl.control;

import at.htl.entity.*;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    void init(@Observes StartupEvent startupEvent) throws IOException {


       /* Person person1 = new Person("Sheila", "Hautzi");
        Person person2 = new Person("Julian", "Dummi");
        Person person3 = new Person("Anna", "Hartl");
        Person person4 = new Person("Jogi", "Pelz");
        person1 = personRepository.save(person1);
        person2 = personRepository.save(person2);
        person3 = personRepository.save(person3);
        person4 = personRepository.save(person4);*/

        List<Person> people = readPeopleFromCSV("person.csv");
        for (Person person : people) {
            personRepository.save(person);
        }


      /*  Course course1 = new Course("Jazz", 5,  "Test");
        Course course2 = new Course("Modern", 10,  "Test");
        course1= courseRepository.save(course1);
        course2= courseRepository.save(course2);

        Appointment appointment1 = new Appointment(LocalDateTime.now(), LocalDateTime.now(), course1);
        Appointment appointment2= new Appointment(LocalDateTime.now(), LocalDateTime.now(), course2);
        Appointment appointment3= new Appointment(LocalDateTime.now(), LocalDateTime.now(), course2);
        Appointment appointment4 = new Appointment(LocalDateTime.now(), LocalDateTime.now(), course1);
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(LocalDateTime.now(), LocalDateTime.now(), course1));
        appointments.add(new Appointment(LocalDateTime.now(), LocalDateTime.now(), course1));
        appointments.add(new Appointment(LocalDateTime.now(), LocalDateTime.now(), course1));
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentRepository.save(appointment3);
        appointmentRepository.save(appointment4);

        CoursePerson coursePerson1 = new CoursePerson(PersonTypeSchool.STUDENT, course1, person1);
        CoursePerson coursePerson2 = new CoursePerson(PersonTypeSchool.TEACHER, course1, person2);
        coursePerson1 = coursePersonRepository.save(coursePerson1);
        coursePerson2 = coursePersonRepository.save(coursePerson2);*/

       /* for (Appointment appointment : appointments) {
            appointmentRepository.save(appointment);
        }

        course1.getAppointments().addAll(appointments);
        courseRepository.save(course1);*/

    }

    private static List<Person> readPeopleFromCSV(String fileName) throws IOException {
        List<Person> people = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII);

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(";");

                Person person = createPerson(attributes);

                // adding book into ArrayList
                people.add(person);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }



        return people;
    }

    private static Person createPerson(String[] metadata) {
        String firstName = metadata[0];
        String lastName = metadata[1];


        // create and return book of this metadata
        return new Person(firstName, lastName);
    }


}
