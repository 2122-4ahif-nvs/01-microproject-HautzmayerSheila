package at.htl.control;

import at.htl.entity.Appointment;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AppointmentRepository {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Appointment save(Appointment appointment){
        return entityManager.merge(appointment);
    }

    public List<Appointment> getAllAppointments(){
        return entityManager.createQuery("Select a from Appointment a").getResultList();
    }

    public List<Appointment> getAppointmentsByCourseId(Long id){
           TypedQuery<Appointment> query =  entityManager.createNamedQuery("Appointment.findAppointmentByCustomerId", Appointment.class).setParameter("id", id);
           return query.getResultList();
    }
}
