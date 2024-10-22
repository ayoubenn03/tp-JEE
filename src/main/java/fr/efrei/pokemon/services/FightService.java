package fr.efrei.pokemon.services;

import fr.efrei.pokemon.dto.UpdateFight;
import fr.efrei.pokemon.model.Fight;
import fr.efrei.pokemon.model.Trainer;
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
  private final TrainerRepository trainerRepository;

  @Autowired
  public FightService(FightRepository fightRepository, TrainerRepository trainerRepository) {
    this.repository = fightRepository;
    this.trainerRepository = trainerRepository;
  }

  public List<Fight> findAll() {
    return repository.findAll();
  }

  public void save(Fight fightBody) {
    Fight fight = new Fight();

    Trainer firstTrainer = trainerRepository.findById(fightBody.getFirstTrainer().getId()).orElse(null);
    Trainer secondTrainer = trainerRepository.findById(fightBody.getSecondTrainer().getId()).orElse(null);

    if (firstTrainer != null && secondTrainer != null) {
      fight.setFirstTrainer(firstTrainer);
      fight.setSecondTrainer(secondTrainer);
    }

    fight.setArena(fightBody.getArena());

    Trainer winnerTrainer = fightBody.getWinnerTrainer() != null ?
      trainerRepository.findById(fightBody.getWinnerTrainer().getId()).orElse(null) : null;

    if (winnerTrainer != null) {
      fight.setWinnerTrainer(winnerTrainer);
    }

    repository.save(fight);
  }

  public Fight findById(String id) {
    return repository.findById(id).orElse(null);
  }

}

