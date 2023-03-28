package tn.esprit.pi.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Role")
    private Long idRole;

    @Enumerated()
    @Column(name = "ROLE")
    private TypeRole role;


}
