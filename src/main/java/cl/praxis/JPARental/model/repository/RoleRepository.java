package cl.praxis.JPARental.model.repository;

import cl.praxis.JPARental.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
