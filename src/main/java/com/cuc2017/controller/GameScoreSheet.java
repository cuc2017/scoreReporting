package com.cuc2017.controller;

import com.cuc2017.model.Game;

public class GameScoreSheet {

  private final String startTime;
  private final String endTime;
  private final String currentScore;
  private final String events;

  public GameScoreSheet(Game game, String events) {
    this.startTime = game.getGameStartTime();
    this.endTime = game.getGameEndTime();
    this.currentScore = game.getCurrentScore();
    this.events = events;
  }

  public String getStartTime() {
    return startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public String getCurrentScore() {
    return currentScore;
  }

  public String getEvents() {
    return events;
  }

}
