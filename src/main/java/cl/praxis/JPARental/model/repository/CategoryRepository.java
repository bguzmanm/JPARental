package cl.praxis.JPARental.model.repository;

import cl.praxis.JPARental.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
