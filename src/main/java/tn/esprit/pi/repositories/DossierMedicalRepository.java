package tn.esprit.pi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.DossierMedical;

@Repository
public interface DossierMedicalRepository  extends JpaRepository<DossierMedical,Long> {


}
