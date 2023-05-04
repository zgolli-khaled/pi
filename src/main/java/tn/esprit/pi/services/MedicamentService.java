package tn.esprit.pi.services;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Medicament;
import tn.esprit.pi.repositories.MedicamentRepo;

import java.util.List;
@AllArgsConstructor
@Service
public class MedicamentService implements IMedicament{

    public MedicamentRepo medicamentRepo;
    private JavaMailSender emailSender;

    @Override
    public Medicament addMedicament(Medicament medicament) {
        return medicamentRepo.save(medicament);

    }

    @Override
    public List<Medicament> getAllMedicaments() {

            return (List<Medicament>) medicamentRepo.findAll();

    }

    @Override
    public Medicament updateMedicament(Medicament medicament) {
        Medicament existingMedicament = medicamentRepo.findById(Math.toIntExact(medicament.getIdMed())).get();
        existingMedicament.setLibelle(medicament.getLibelle());
        existingMedicament.setPrix(medicament.getPrix());
        existingMedicament.setQuantite(medicament.getQuantite());
        existingMedicament.setDate(medicament.getDate());
        return medicamentRepo.save(existingMedicament);
    }

    @Override
    public Medicament retrieveMedicament(int idMed) {
        return medicamentRepo.findById(Math.toIntExact(idMed)).orElse(null);
    }

    @Override
    public void removeMedicament(Medicament medicament) {
        medicamentRepo.delete(medicament);

    }

    @Override
    public List<Medicament> advancedSearch(String libelle,Float prix) {
        if (libelle == null && prix == null ) {
            return getAllMedicaments();
        } else if (libelle != null && prix == null  ) {
            return medicamentRepo.findMedicamentByLibelle(libelle);
        } else if (libelle == null && prix != null ) {
            return medicamentRepo.findMedicamentByPrix(prix);
        } else {
            return medicamentRepo.findMedicamentByLibelleAndPrix(libelle,prix);
        }
    }
    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }


}
