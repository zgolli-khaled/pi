package tn.esprit.pi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP )
    private Date datecreation;

    private  String content;

    @ManyToOne
    @JoinColumn(name = "Dossier_Medical_id" )
    @JsonIgnoreProperties("prescriptions")
    private Dossier_Medical dossier_medical;


}
