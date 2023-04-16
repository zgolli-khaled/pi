package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pi.entities.Dossier_Medical;

public interface Dossier_MedicalRepository extends JpaRepository<Dossier_Medical,Long> {
}
