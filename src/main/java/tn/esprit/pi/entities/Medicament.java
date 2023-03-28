package tn.esprit.pi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Medicament {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_MED")
    private Long idMed;


    @Column(name ="NOM")
    private  String nom;
}
