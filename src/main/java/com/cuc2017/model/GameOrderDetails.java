package com.cuc2017.model;

public class GameOrderDetails extends AbstractEntity {

  private Team homeTeam;
  private Team awayTeam;
  private int gameNumber;

  public GameOrderDetails(Team homeTeam, Team awayTeam, int gameNumber) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.gameNumber = gameNumber;
  }

  @Override
  public String toString() {
    return "Game number is: " + gameNumber + " homeTeam: " + homeTeam + " awayTeam: " + awayTeam;
  }

  public Team getHomeTeam() {
    return homeTeam;
  }

  public void setHomeTeam(Team homeTeam) {
    this.homeTeam = homeTeam;
  }

  public Team getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(Team awayTeam) {
    this.awayTeam = awayTeam;
  }

  public int getGameNumber() {
    return gameNumber;
  }

  public void setGameNumber(int gameNumber) {
    this.gameNumber = gameNumber;
  }
}
