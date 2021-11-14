package at.htl.entity;

import javax.inject.Inject;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CoursePerson {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PersonType personType;

    @ManyToOne
    private Course course;
    @ManyToOne
    private Person person;

    public CoursePerson(PersonType personType, Course course, Person person) {
        this.personType = personType;
        this.course = course;
        this.person = person;
    }

    public CoursePerson() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }
}
