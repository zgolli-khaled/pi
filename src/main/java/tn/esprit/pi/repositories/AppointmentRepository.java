package tn.esprit.pi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment , Long> {
}
