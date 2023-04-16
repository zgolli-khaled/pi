package tn.esprit.pi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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

    private LocalDate dateprescription;


    private LocalTime heureprescription;

    private  String content;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Dossier_Medical_id" , nullable = false)
    private Dossier_Medical dossier_medical;

}
