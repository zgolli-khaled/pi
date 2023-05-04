package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Medicament;

import java.util.List;

public interface MedicamentRepo extends JpaRepository<Medicament, Integer> {
    List<Medicament> findMedicamentByLibelle(String libelle);
    List<Medicament> findMedicamentByPrix(Float prix);
    List<Medicament> findMedicamentByLibelleAndPrix(String libelle, Float prix);







}