package cl.praxis.JPARental.model.repository;

import cl.praxis.JPARental.model.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {}
