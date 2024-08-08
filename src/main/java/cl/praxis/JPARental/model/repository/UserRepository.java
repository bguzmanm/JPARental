package cl.praxis.JPARental.model.repository;

import cl.praxis.JPARental.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByUsername(String username);
  // List<User> findByFirstNameAndLastName(String firstName, String lastName);
}
