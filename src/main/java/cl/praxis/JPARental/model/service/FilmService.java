package cl.praxis.JPARental.model.service;

import cl.praxis.JPARental.model.entities.Film;

import java.util.List;

public interface FilmService {
  List<Film> findAll();
  Film findOne(int id);
  boolean create(Film f);
  boolean update(Film f);
  boolean delete(int id);
}
