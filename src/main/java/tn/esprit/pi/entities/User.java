package tn.esprit.pi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import tn.esprit.pi.entities.Enumeration.RoleType;
import tn.esprit.pi.entities.Enumeration.genderType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private  String CIN ;
    private  String img_url;
    //@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    private String adress;
    private String phone_nbr;
    private String email_addr;
    @Enumerated(EnumType.STRING)
    private genderType gender;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "dossier_medical_id")
    private Dossier_Medical dossier_medical;




}
