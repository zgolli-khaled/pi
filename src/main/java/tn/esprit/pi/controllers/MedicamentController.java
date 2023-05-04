package tn.esprit.pi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Medicament;
import tn.esprit.pi.services.MedicamentService;

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


}
