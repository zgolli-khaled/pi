package tn.esprit.pi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
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



    @ManyToOne
    private Role role;

    @OneToMany(mappedBy="user")
    private Set<Payment> Payments;

    @OneToMany(mappedBy="user")
    private Set<Appointment> Appointments;


    @OneToOne(mappedBy = "user")
    private Chambre chambre;

    @OneToMany(mappedBy = "user")
    private  Set<Reclamation> Reclamations;


    @ManyToOne
    private Pharmacie pharmacie;


    @OneToOne(cascade = CascadeType.REMOVE)
    private DossierMedical dossierMedical;


    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "user")
    private Set<Prescription> Prescriptions;
















}
