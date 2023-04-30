package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.pi.entities.Dossier_Medical;

import java.util.List;

public interface Dossier_MedicalRepository extends JpaRepository<Dossier_Medical,Long> {

    @Query (value = "select * from dossier_medical order by datecreation desc " , nativeQuery = true)
    public List<Dossier_Medical> List_By_Last_Date();
}
