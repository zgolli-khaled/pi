package tn.esprit.pi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long idUser;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "PRENOM")
    private String prenom;

    @Column(name = "NUMERO")
    private String numero;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyy-mm-dd")
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "AGE")
    private int age;

    @Column(name = "CIN")
    private String cin;


    @Column(nullable= true ,name = "SEPECIALITE")
    private String specialite;















}
