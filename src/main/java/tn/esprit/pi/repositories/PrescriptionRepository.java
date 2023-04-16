package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pi.entities.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {
}
