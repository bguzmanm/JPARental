package cl.praxis.JPARental.controller;

import cl.praxis.JPARental.model.entities.Film;
import cl.praxis.JPARental.model.entities.Language;
import cl.praxis.JPARental.model.service.FilmService;
import cl.praxis.JPARental.model.service.LanguageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/films")
public class FilmController {
  private final FilmService service;
  private final LanguageService languageService;

  public FilmController(FilmService service, LanguageService languageService) {
    this.service = service;
    this.languageService = languageService;
  }

  @GetMapping
  public String findAll(Model model){
    model.addAttribute("films", service.findAll());
    return "filmsList";
  }

  @GetMapping("/{id}")
  public String findOne(@PathVariable("id") int id, Model model){
    model.addAttribute("film", service.findOne(id));
    model.addAttribute("languages", languageService.findAll());
    return "filmEdit";
  }

  @PostMapping
  public String update(@ModelAttribute Film film){
    boolean result = service.update(film);
    return "redirect:/films";
  }

  @GetMapping("/new")
  public String toCreate(Model model){
    model.addAttribute("languages", languageService.findAll());
    return "filmNew";
  }

  @PostMapping("/new")
  public String create(@ModelAttribute Film film){
    boolean result = service.create(film);

    return "redirect:/films";
  }
  @GetMapping("/{id}/del")
  public String delete(@PathVariable("id") int id){
    boolean result = service.delete(id);

    return "redirect:/films";
  }
}
