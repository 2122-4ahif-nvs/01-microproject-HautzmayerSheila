package at.htl.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Name must not be blank")
    private String name;

    @Min(message="Course has too less people", value=1)
    private int maxNumberOfPeople;
    @NotBlank(message="Description must not be blank")
    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CoursePerson> coursePerson;

    public Course(String name, int maxNumberOfPeople, String description) {
        this();
        this.name = name;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.description = description;
    }

    public Course() {
        this.appointments = new ArrayList<>();
        this.coursePerson = new ArrayList<>();
    }


    public List<CoursePerson> getCoursePerson() {
        return coursePerson;
    }

    public void setCoursePerson(List<CoursePerson> coursePerson) {
        this.coursePerson = coursePerson;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(int maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", maxNumberOfPeople=" + maxNumberOfPeople +
                ", description='" + description + '\'' +
                '}';
    }
}
