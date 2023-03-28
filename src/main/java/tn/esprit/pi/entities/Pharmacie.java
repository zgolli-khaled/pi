package tn.esprit.pi.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class Pharmacie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PHAR")
    private Long idPhar;


    @Column(name ="NOM")
    private  String nom;


}
