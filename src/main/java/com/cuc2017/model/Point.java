package com.cuc2017.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Point extends AbstractEntity {
  @ManyToOne
  private Team team;
  @ManyToOne
  private Game game;

  public Point() {
    // default Constructor
  }

  public Point(Game game, Team team) {
    this.game = game;
    this.team = team;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

}
