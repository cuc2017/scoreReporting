package com.cuc2017.model;

import java.util.Calendar;
import java.util.Date;

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
  private boolean gameOver = false;

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

  public boolean isGameFromToday() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    Date today = calendar.getTime();
    return getCreated().after(today);
  }

  @Override
  public String toString() {
    return getHomeTeam() + " vs " + getAwayTeam() + " in " + getDivision() + " " + " @ " + getField();
  }

  public String getGameTweetSummary() {
    return getHomeTeam() + " vs " + getAwayTeam() + " in " + getDivision().getHashtag() + " on "
        + getField().getHashtag() + " follow " + getField().getAtTag();
  }

  public String getCurrentGameTweet() {
    return getDivision().getHashtag() + " " + getHomeTeam() + ": " + getHomeTeamScore() + " " + getAwayTeam() + ": "
        + getAwayTeamScore();
  }

  public String getFinalGameTweetField() {
    return "Game Over: " + getCurrentGameTweet();
  }

  public String getFinalGameTweet() {
    return getFinalGameTweetField() + " on " + getField().getHashtag() + " " + getField().getAtTag();
  }

  public String getCurrentScore() {
    return getHomeTeam() + ": " + getHomeTeamScore() + "<br />" + getAwayTeam() + ": " + getAwayTeamScore();
  }

  public String getProposedFinalScore() {
    return getHomeTeam() + ": " + getHomeTeamScore() + "  " + getAwayTeam() + ": " + getAwayTeamScore();
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

  public boolean isGameOver() {
    return gameOver;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

}
