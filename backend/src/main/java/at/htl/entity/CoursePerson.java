package at.htl.entity;

import javax.persistence.*;

@Entity
public class CoursePerson {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PersonTypeSchool personTypeSchool;

    @ManyToOne
    private Course course;
    @ManyToOne
    private Person person;

    public CoursePerson(PersonTypeSchool personTypeSchool, Course course, Person person) {
        this.personTypeSchool = personTypeSchool;
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

    public PersonTypeSchool getPersonType() {
        return personTypeSchool;
    }

    public void setPersonType(PersonTypeSchool personTypeSchool) {
        this.personTypeSchool = personTypeSchool;
    }
}
