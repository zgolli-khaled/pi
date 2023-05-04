package tn.esprit.pi.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long idRole;

    @Enumerated()
    @Column(name = "ROLE")
    private TypeRole role;


    @OneToMany(mappedBy="role")
    private Set<User> Users;


}
