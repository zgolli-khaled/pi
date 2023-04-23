package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

