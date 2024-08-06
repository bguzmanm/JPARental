package cl.praxis.JPARental.controller;

import cl.praxis.JPARental.model.service.ActorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/actors")
public class ActorController {

  private final ActorService service;

  public ActorController(ActorService service) {
    this.service = service;
  }

  @GetMapping
  public String findAll(Model model){
    model.addAttribute("actors", service.findAll());
    return "actorsList";
  }
}
