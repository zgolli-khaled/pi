package tn.esprit.pi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Prescription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;


    @OneToMany(cascade = CascadeType.ALL)
    private List<treatment> treatments;


    @JsonIgnore
    @ManyToOne
    private Dossier_Medical dossierMedical;

}

