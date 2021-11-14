package at.htl.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NamedQueries({

        @NamedQuery(
                name = "Appointment.findAppointmentByCustomerId",
                query = "Select a from Appointment  a where a.course.id = :id"
        )})

public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    private Course course;

    public Appointment(LocalDateTime startTime, LocalDateTime endTime, Course course) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.course = course;
    }

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
