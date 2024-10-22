package fr.efrei.pokemon.services;

import fr.efrei.pokemon.dto.UpdateFight;
import fr.efrei.pokemon.model.Arena;
import fr.efrei.pokemon.model.Fight;
import fr.efrei.pokemon.model.Trainer;
import fr.efrei.pokemon.repositories.ArenaRepository;
import fr.efrei.pokemon.repositories.FightRepository;
import fr.efrei.pokemon.repositories.TrainerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class FightService {

  private final FightRepository repository;
  private final TrainerService trainerService;
  private final ArenaService arenaService;

  @Autowired
  public FightService(FightRepository repository, TrainerService trainerService, ArenaService arenaService) {
    this.repository = repository;
    this.trainerService = trainerService;
    this.arenaService = arenaService;

  }

  public List<Fight> findAll() {
    return repository.findAll();
  }

  public void save(Fight fightBody) {
    Fight fight = new Fight();

    Trainer firstTrainer = trainerService.findById(fightBody.getFirstTrainer().getId());
    Trainer secondTrainer = trainerService.findById(fightBody.getSecondTrainer().getId());

    if (firstTrainer != null && secondTrainer != null) {
      fight.setFirstTrainer(firstTrainer);
      fight.setSecondTrainer(secondTrainer);
    }

    fight.setArena(fightBody.getArena());

    Trainer winnerTrainer = fightBody.getWinnerTrainer() != null ?
      trainerService.findById(fightBody.getWinnerTrainer().getId()): null;

    if (winnerTrainer != null) {
      fight.setWinnerTrainer(winnerTrainer);
    }

    repository.save(fight);
  }

  public Fight findById(String id) {
    return repository.findById(id).orElse(null);
  }

  @Transactional
  public void update(String id, UpdateFight fightBody) {
    Fight fight = repository.findById(id).orElse(null);

    // arrete la maj si le combat est inexistant
    if (fight == null) {
      return;
    }

  // met a jour l'arene du combat si il est saisi dans le body
    if (fightBody.getArenaId() != null) {
      Arena arena = arenaService.findById(fightBody.getArenaId());
      if (arena != null) {
        fight.setArena(arena);
      }
    }

  // met a jour le fighter 1 du combat si il est saisi dans le body
    if (fightBody.getFirstTrainerId() != null) {
      Trainer firstTrainer = trainerService.findById(fightBody.getFirstTrainerId());
      if (firstTrainer != null) {
        fight.setFirstTrainer(firstTrainer);
      }
    }

    // met a jour le fighter 2 du combat si il est saisi dans le body
    if (fightBody.getSecondTrainerId() != null) {
      Trainer secondTrainer = trainerService.findById(fightBody.getSecondTrainerId());
      if (secondTrainer != null) {
        fight.setSecondTrainer(secondTrainer);
      }
    }

    // met a jour le winner  du combat si il est saisi dans le body
    if (fightBody.getWinnerId() != null) {
      Trainer winnerTrainer = trainerService.findById(fightBody.getWinnerId());
      if (winnerTrainer != null) {
        fight.setWinnerTrainer(winnerTrainer);
      }
    }

    repository.save(fight);
  }

  public void delete(String id) {
    repository.deleteById(id);
  }
}

