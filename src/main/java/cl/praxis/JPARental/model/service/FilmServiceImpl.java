package cl.praxis.JPARental.model.service;

import cl.praxis.JPARental.model.entities.Film;
import cl.praxis.JPARental.model.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

  FilmRepository repository;

  public FilmServiceImpl(FilmRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Film> findAll() {
    return repository.findAll();
  }

  @Override
  public Film findOne(int id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public boolean create(Film f) {
    Film result = repository.save(f);
    System.out.println("creado el id " + result.getId());

    return result != null;
  }

  @Override
  public boolean update(Film f) {
    Film film = repository.save(f);
    return film != null;
  }

  @Override
  public boolean delete(int id) {
    boolean exist = repository.existsById(id);
    if (exist){
      repository.deleteById(id);
    }
    return exist;
  }
}
