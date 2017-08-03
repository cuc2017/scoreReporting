package com.cuc2017.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Game extends AbstractEntity {

  @ManyToOne
  private Division division;
  @ManyToOne
  private Team homeTeam;
  @ManyToOne
  private Team awayTeam;
  @ManyToOne
  private Field field;
  private String scoreKeepers;
  private int homeTeamScore;
  private int awayTeamScore;

  public Game() {
    // default constructor
  }

  public Game(Division division, Team homeTeam, Team awayTeam, Field field) {
    this.division = division;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.field = field;
  }

  public Game(Division division, Team homeTeam, Team awayTeam, Field field, String scoreKeepers) {
    this.division = division;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.field = field;
    this.scoreKeepers = scoreKeepers;
  }

  @Override
  public String toString() {
    return getHomeTeam() + " vs " + getAwayTeam() + " in " + getDivision() + " " + " @ " + getField();
  }

  public String getGameTweetSummary() {
    return getHomeTeam() + " vs " + getAwayTeam() + " in " + getDivision().getHashtag() + " on "
        + getField().getHashtag() + " follow game " + getField().getAtTag();
  }

  public String getCurrentGameTweet() {
    return getDivision().getHashtag() + " " + getHomeTeam() + ": " + getHomeTeamScore() + " " + getAwayTeam() + ": "
        + getAwayTeamScore();
  }

  public String getCurrentScore() {
    return getHomeTeam() + ": " + getHomeTeamScore() + "<br />" + getAwayTeam() + ": " + getAwayTeamScore();
  }

  public void incrementHomeTeamScore() {
    homeTeamScore++;
  }

  public void incrementAwayTeamScore() {
    awayTeamScore++;
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

  public Field getField() {
    return field;
  }

  public void setField(Field field) {
    this.field = field;
  }

  public Division getDivision() {
    return division;
  }

  public void setDivision(Division division) {
    this.division = division;
  }

  public String getScoreKeepers() {
    return scoreKeepers;
  }

  public void setScoreKeepers(String scoreKeepers) {
    this.scoreKeepers = scoreKeepers;
  }

  public int getHomeTeamScore() {
    return homeTeamScore;
  }

  public void setHomeTeamScore(int homeTeamScore) {
    this.homeTeamScore = homeTeamScore;
  }

  public int getAwayTeamScore() {
    return awayTeamScore;
  }

  public void setAwayTeamScore(int awayTeamScore) {
    this.awayTeamScore = awayTeamScore;
  }

}
