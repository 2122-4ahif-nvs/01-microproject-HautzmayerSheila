package at.htl.boundary;

import at.htl.control.AppointmentRepository;
import at.htl.entity.Appointment;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("App")
public class AppointmentResource {
    @Inject
    AppointmentRepository appointmentRepository;


    @Path("/GetAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    @Path("/GetByCourseId/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAllAppointmentsByCId(@PathParam("id") Long id){
        return appointmentRepository.getAppointmentsByCourseId(id);
    }
}
