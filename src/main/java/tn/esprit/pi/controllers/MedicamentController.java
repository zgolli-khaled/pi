package tn.esprit.pi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Medicament;
import tn.esprit.pi.services.MedicamentService;

import java.util.List;

@AllArgsConstructor
@RestController
public class MedicamentController {
    public MedicamentService medicamentService;

    @PostMapping("/addMedicament")
    @ResponseBody
    public Medicament addMedicament (@RequestBody Medicament medicament) {
        return medicamentService.addMedicament(medicament);

    }

    @PutMapping("{idMed}")
    public Medicament updateMedicament(@PathVariable int idMed, @RequestBody Medicament medicament) {
        medicament.setIdMed(idMed);
        return medicamentService.updateMedicament(medicament);
    }

    @DeleteMapping("{idMed}")
    public void removeMedicament(@PathVariable int idMed) {
        Medicament medicament = medicamentService.retrieveMedicament(idMed);
        if (medicament != null) {
            medicamentService.removeMedicament(medicament);

        }
    }

    @GetMapping("{idMed}")
    public Medicament retrieveMedicament(@PathVariable int idMed) {
        Medicament medicament = medicamentService.retrieveMedicament(idMed);
        if (medicament != null) {}
        return medicament;
    }

    @GetMapping("/getallmeds")
    public List<Medicament> getallMeds() {
       return medicamentService.getAllMedicaments();

    }

    @GetMapping("/recherche")
    public List<Medicament> advancedSearch(
            @RequestParam(name = "libelle", required = false) String libelle,
            @RequestParam(name = "prix", required = false) Float prix ){

        // Call the service method with the new parameter
        return medicamentService.advancedSearch(libelle,prix);
    }

    @PostMapping("/email")
    public void sendEmail() {
        String to = "fdoghri8@gmail.com";
        String subject = "Test email";
        String body = "This is a test email";
        medicamentService.sendEmail(to, subject, body);
    }

}
