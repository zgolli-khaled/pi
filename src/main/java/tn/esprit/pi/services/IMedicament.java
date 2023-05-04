package tn.esprit.pi.services;

import tn.esprit.pi.entities.Medicament;

import java.util.List;

public interface IMedicament {
    Medicament addMedicament(Medicament medicament);

    List<Medicament> getAllMedicaments();

    Medicament updateMedicament(Medicament medicament);

    Medicament retrieveMedicament(int idMed);

    void removeMedicament(Medicament medicament);

    List<Medicament> advancedSearch(String libelle,Float prix);
    void sendEmail(String to, String subject, String body);
}
