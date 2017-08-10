package com.cuc2017.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Event extends AbstractEntity {

  private EventType eventType;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "game_id")
  @JsonManagedReference
  private Game game;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "team_id")
  private Team team;
  @ManyToOne(cascade = CascadeType.ALL)
  private Player goal;
  @ManyToOne(cascade = CascadeType.ALL)
  private Player assist;

  private boolean useEvent = true;

  public Event() {
    // default constructor
  }

  public Event(EventType eventType, Game game) {
    this.setEventType(eventType);
    this.game = game;
  }

  public Event(EventType eventType, Game game, Team team) {
    this(eventType, game);
    this.team = team;
  }

  public Event(EventType eventType, Game game, Team team, Player scoredBy, Player assistedBy) {
    this(eventType, game, team);
    this.goal = scoredBy;
    this.assist = assistedBy;
  }

  public String tweetString() {
    switch (getEventType()) {
      case POINT_SCORED:
        break;
      case TIME_OUT:
      case HALF_TIME:
      case GAVE_OVER:
      case READY:
      case STARTED:
      default:
        return getEventType().getName() + ": ";
    }
    return "";
  }

  @Override
  public String toString() {
    switch (getEventType()) {
      case POINT_SCORED:
        return getTeam().getName() + " scored";
      case TIME_OUT:
        return getTeam().getName() + " timeout";
      case HALF_TIME:
      case GAVE_OVER:
      case READY:
      case STARTED:
      default:
        return getEventType().getName();
    }
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public boolean isUseEvent() {
    return useEvent;
  }

  public void setUseEvent(boolean useEvent) {
    this.useEvent = useEvent;
  }

  public Player getGoal() {
    return goal;
  }

  public void setGoal(Player goal) {
    this.goal = goal;
  }

  public Player getAssist() {
    return assist;
  }

  public void setAssist(Player assist) {
    this.assist = assist;
  }

  // public Player getScoredBy() {
  // return scoredBy;
  // }
  //
  // public void setScoredBy(Player scoredBy) {
  // this.scoredBy = scoredBy;
  // }
  //
  // public Player getAssitedBy() {
  // return assitedBy;
  // }
  //
  // public void setAssitedBy(Player assitedBy) {
  // this.assitedBy = assitedBy;
  // }
}
