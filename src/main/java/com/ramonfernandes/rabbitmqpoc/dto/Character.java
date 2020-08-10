package com.ramonfernandes.rabbitmqpoc.dto;

public class Character {

  private Race race;
  private CharacterClass classe;

  public Character(Race race, CharacterClass classe) {
    this.race = race;
    this.classe = classe;
  }

  public Race getRace() {
    return race;
  }

  public void setRace(Race race) {
    this.race = race;
  }

  public CharacterClass getClasse() {
    return classe;
  }

  public void setClasse(CharacterClass classe) {
    this.classe = classe;
  }

}

