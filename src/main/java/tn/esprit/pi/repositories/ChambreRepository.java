package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pi.entities.Chambre;
import org.springframework.stereotype.Repository;
@Repository
public interface ChambreRepository extends JpaRepository <Chambre, Long>{
}
