package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pi.entities.treatment;

public interface treatmentRepository extends JpaRepository<treatment,Long> {



}
