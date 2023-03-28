package tn.esprit.pi.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_SERVICE")
    private Long idService;


    @Column(name ="NOM")
    private  String nom;

    @Column(name ="DESCRIPTION")
    private  String description;
}
