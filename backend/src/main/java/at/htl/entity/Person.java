package at.htl.entity;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity

@NamedQueries({
        @NamedQuery(
                name = "Person.findAllPeople",
                query = "Select p from Person p"
        ),
        @NamedQuery(
                name = "Person.findPersonById",
                query = "Select p from Person p where p.id = :id"
        )})


public class Person {

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;


    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CoursePerson> coursePerson;


    public Person(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {
        this.coursePerson = new ArrayList<>();
    }

    public List<CoursePerson> getCoursePerson() {
        return coursePerson;
    }

    public void setCoursePerson(List<CoursePerson> coursePerson) {
        this.coursePerson = coursePerson;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
