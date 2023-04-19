package tn.esprit.pi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Appointment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment , Long> {

    List<Appointment> findAppointmentsByDateApp(Date dateApp);
    // List<Appointment> findAppointmentsByDateAppBefore(Date dateApp);
    @Query("SELECT a FROM Appointment a WHERE a.dateApp BETWEEN CURRENT_DATE  AND CURRENT_DATE + :day ")
    List<Appointment> findAppointmentsByDateBefore( @Param("day") Double day);

    @Query("SELECT count(*) FROM Appointment a WHERE a.dateApp = :date ")
    int countAppointmen(@Param("date") Date date);


}
