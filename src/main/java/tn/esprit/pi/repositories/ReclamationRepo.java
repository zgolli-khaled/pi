package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Reclamation;
import tn.esprit.pi.entities.Status;

@Repository
public interface ReclamationRepo extends JpaRepository<Reclamation , Long> {

    int countAllByStatus( Status status);
}
