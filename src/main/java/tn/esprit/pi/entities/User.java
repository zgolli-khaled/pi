package tn.esprit.pi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idUser;
    @NotBlank
    @Size(max = 20)
    @Column(name = "NOM")
    private String nom;
    @NotBlank
    @Size(max = 20)
    @Column(name = "PRENOM")
    private String prenom;

    @Column(nullable=false, unique=true)
    private String email;

    @NotBlank
    @JsonIgnore
    private String password;


    private String numero;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")

    private Date birthday;


    private String address;


    private int age;


    private String cin;


    @Column(nullable= true)
    private String specialite;
    @NotBlank
    String username;
    @Column(name = "Code")
    private String code;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<Role> roles = new HashSet<>();

    @Column(name = "reset_password_token")
    private String resetPasswordToken;
    @OneToMany(mappedBy="user")
    private Set<Payment> Payments;

    @OneToMany(mappedBy="user",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Appointment> Appointments;


    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Chambre chambre;

    @OneToMany(mappedBy = "user")
    private  Set<Reclamation> Reclamations;


    @ManyToOne
    private Pharmacie pharmacie;


    @OneToOne(cascade = CascadeType.REMOVE)
    private DossierMedical dossierMedical;


    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "user")
    private Set<Prescription> Prescriptions;



    public User(String nom, String email, String cin, String numero, String address, String prenom, int age, Date birthday,String username, String password) {
        super();
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.address= address;
        this.age=age ;
        this.numero = numero;
        this.cin=cin ;
        this.prenom= prenom ;
        this.birthday=birthday;
        this.username=username;

    }
}
