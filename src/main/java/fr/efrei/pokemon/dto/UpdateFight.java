package fr.efrei.pokemon.dto;

public class UpdateFight {

  private String firstTrainerId;
  private String secondTrainerId;
  private String arenaId;
  private String winnerId;


  public String getFirstTrainerId() {
    return firstTrainerId;
  }

  public void setFirstTrainerId(String firstTrainerId) {
    this.firstTrainerId = firstTrainerId;
  }

  public String getSecondTrainerId() {
    return secondTrainerId;
  }

  public void setSecondTrainerId(String secondTrainerId) {
    this.secondTrainerId = secondTrainerId;
  }

  public String getArenaId() {
    return arenaId;
  }

  public void setArenaId(String arenaId) {
    this.arenaId = arenaId;
  }

  public String getWinnerId() {
    return winnerId;
  }

  public void setWinnerId(String winnerId) {
    this.winnerId = winnerId;
  }
}
