package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Reclamation;

@Repository
public interface ReclamationRepo extends JpaRepository<Reclamation , Long> {

}
