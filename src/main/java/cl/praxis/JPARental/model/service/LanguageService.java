package cl.praxis.JPARental.model.service;

import cl.praxis.JPARental.model.entities.Film;
import cl.praxis.JPARental.model.entities.Language;

import java.util.List;

public interface LanguageService {
  List<Language> findAll();
  Language findOne(int id);
}
