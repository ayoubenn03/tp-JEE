package fr.efrei.pokemon.model;

import jakarta.persistence.*;

@Entity
public class Fight {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @ManyToOne
  private Arena arena;

  @OneToOne
  private Trainer firstTrainer;

  @OneToOne
  private Trainer secondTrainer;

  @OneToOne
  private Trainer winnerTrainer;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Trainer getWinnerTrainer() {
    return winnerTrainer;
  }

  public void setWinnerTrainer(Trainer winnerTrainer) {
    this.winnerTrainer = winnerTrainer;
  }

  public Trainer getSecondTrainer() {
    return secondTrainer;
  }

  public void setSecondTrainer(Trainer secondTrainer) {
    this.secondTrainer = secondTrainer;
  }

  public Trainer getFirstTrainer() {
    return firstTrainer;
  }

  public void setFirstTrainer(Trainer firstTrainer) {
    this.firstTrainer = firstTrainer;
  }

  public Arena getArena() {
    return arena;
  }

  public void setArena(Arena arena) {
    this.arena = arena;
  }
}
