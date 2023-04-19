package tn.esprit.pi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private Long idUser;


    private String nom;


    private String prenom;


    private String numero;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyy-mm-dd")

    private Date birthday;


    private String address;


    private int age;


    private String cin;


    @Column(nullable= true)
    private String specialite;



    @ManyToOne
    private Role role;

    @OneToMany(mappedBy="user")
    private Set<Payment> Payments;

    @OneToMany(mappedBy="user" ,fetch = FetchType.LAZY)
    //@JsonManagedReference
    @JsonIgnore
    private Set<Appointment> Appointments;


    @OneToOne(mappedBy = "user")
    private Chambre chambre;

    @OneToMany(mappedBy = "user")
    private  Set<Reclamation> Reclamations;


    @ManyToOne
    private Pharmacie pharmacie;


    @OneToOne(cascade = CascadeType.REMOVE ,mappedBy = "user")
    @JsonIgnore
    private DossierMedical dossierMedical;


    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "user")
    private Set<Prescription> Prescriptions;
















}
