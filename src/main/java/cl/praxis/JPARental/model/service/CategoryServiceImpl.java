package cl.praxis.JPARental.model.service;

import cl.praxis.JPARental.model.entities.Category;
import cl.praxis.JPARental.model.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository repository;

  public CategoryServiceImpl(CategoryRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Category> findAll() {
    return repository.findAll();
  }

  @Override
  public Category findOne(int id) {

    Category c = new Category();

    return repository.findById(id).orElse(null);
  }
}
