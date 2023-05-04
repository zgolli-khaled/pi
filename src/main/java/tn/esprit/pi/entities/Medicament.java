package tn.esprit.pi.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Medicament {
 @Id
 @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idMed;
    private String libelle;
    private String Date;
    private Float prix;
    private Integer quantite;
}
