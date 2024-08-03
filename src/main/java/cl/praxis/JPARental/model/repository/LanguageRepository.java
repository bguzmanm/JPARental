package cl.praxis.JPARental.model.repository;

import cl.praxis.JPARental.model.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
