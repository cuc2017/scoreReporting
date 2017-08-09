package com.cuc2017.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Event> events = new ArrayList<>();
	private String scoreKeepers;
	private int homeTeamScore;
	private int awayTeamScore;
	private int homeTimeOutThisHalf;
	private int awayTimeOutThisHalf;

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

	public void addEvent(Event event) {
		getEvents().add(event);
	}

	@JsonIgnore
	public Event getLastEvent() {
		int eventsSize = getEvents().size();
		int counter = 0;
		Event event;
		do {
			counter++;
			if (counter > eventsSize) {
				return null;
			}
			event = getEvents().get(eventsSize - counter);
		} while (!event.isUseEvent());
		return event;

	}

	public void resetTimeOuts() {
		setHomeTimeOutThisHalf(0);
		setAwayTimeOutThisHalf(0);
	}

	public void homeTeamTookTimeOut() {
		homeTimeOutThisHalf++;
	}

	public void awayTeamTookTimeOut() {
		awayTimeOutThisHalf++;
	}

	public void homeTeamUndoTookTimeOut() {
		homeTimeOutThisHalf--;
	}

	public void awayTeamUndoTookTimeOut() {
		awayTimeOutThisHalf--;
	}

	@Override
	public String toString() {
		return getHomeTeam() + " vs " + getAwayTeam() + " in " + getDivision() + " " + " @ " + getField();
	}

	public String getGameTweetSummary() {
		return getHomeTeam() + " vs " + getAwayTeam() + " in " + getDivision().getHashtag() + " on " + getField()
				+ " follow " + getField().getAtTag();
	}

	public String getCurrentGameTweet() {
		return getDivision().getHashtag() + " " + getHomeTeam() + ": " + getHomeTeamScore() + " " + getAwayTeam() + ": "
				+ getAwayTeamScore();
	}

	public String getFinalGameTweetField() {
		return "Game Over: " + getCurrentGameTweet();
	}

	public String getFinalGameTweet() {
		return getFinalGameTweetField() + addFieldInfoToTweet();
	}

	public String getHalftimeTweetField() {
		return "HalfTime: " + getCurrentGameTweet();
	}

	public String getHalftimeTweet() {
		return getHalftimeTweetField() + addFieldInfoToTweet();
	}

	public String addFieldInfoToTweet() {
		return " on " + getField() + " " + getField().getAtTag();
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

	public void decrementAwayTeamScore() {
		awayTeamScore--;
	}

	public void decrementHomeTeamScore() {
		homeTeamScore--;
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

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public int getHomeTimeOutThisHalf() {
		return homeTimeOutThisHalf;
	}

	public void setHomeTimeOutThisHalf(int homeTimeOutThisHalf) {
		this.homeTimeOutThisHalf = homeTimeOutThisHalf;
	}

	public int getAwayTimeOutThisHalf() {
		return awayTimeOutThisHalf;
	}

	public void setAwayTimeOutThisHalf(int awayTimeOutThisHalf) {
		this.awayTimeOutThisHalf = awayTimeOutThisHalf;
	}

}
