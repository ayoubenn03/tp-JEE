package fr.efrei.pokemon.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Arena {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;

  private String localisation;

  @OneToMany
  private List<Fight> fights;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocalisation() {
    return localisation;
  }

  public void setLocalisation(String localisation) {
    this.localisation = localisation;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<Fight> getFights() {
    return fights;
  }

  public void setFights(List<Fight> fights) {
    this.fights = fights;
  }
}
