package fr.efrei.pokemon.Controller;

import fr.efrei.pokemon.dto.CreateTrainer;
import fr.efrei.pokemon.model.Fight;
import fr.efrei.pokemon.model.Pokemon;
import fr.efrei.pokemon.model.Trainer;
import fr.efrei.pokemon.services.FightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fights")
public class FightController {

  private final FightService fightService;

  @Autowired
  public FightController(FightService fightService) {
    this.fightService=fightService;
  }

  @GetMapping
  public ResponseEntity<List<Fight>> findAll() {
    return new ResponseEntity<>(fightService.findAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Fight fight) {
    fightService.save(fight);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Fight> findById(@PathVariable String id) {
    Fight fight = fightService.findById(id);
    if (fight == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(fight, HttpStatus.OK);
  }
}
