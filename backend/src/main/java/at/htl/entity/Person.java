package at.htl.entity;

import javax.persistence.*;


@Entity

@NamedQueries({
        @NamedQuery(
                name = "Person.findAllPeople",
                query = "Select p from Person p"
        ),
        @NamedQuery(
                name = "Person.findPersonById",
                query = "Select p.firstName, p.lastName from Person p where p.id = :fId"
        )})


public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;


    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {
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
