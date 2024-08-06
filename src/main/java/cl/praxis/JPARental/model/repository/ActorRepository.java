package cl.praxis.JPARental.model.repository;

import cl.praxis.JPARental.model.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
