package com.cuc2017.model;

import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Event extends AbstractEntity {

	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("hh:mm");
	private EventType eventType;
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	public Event() {
		// default constructor
	}

	public Event(EventType eventType, Game game) {
		this.setEventType(eventType);
		this.game = game;
	}

	public Event(EventType eventType, Game game, Team team) {
		this.setEventType(eventType);
		this.game = game;
		this.team = team;
	}

	public String tweetString() {
		switch (getEventType()) {
		case POINT_SCORED:
			break;
		case GAVE_OVER:
		case READY:
		case STARTED:
		default:
			return getEventType().getName() + ": ";
		}
		return "";
	}

	public String eventAsHtmlRow() {
		StringBuffer row = new StringBuffer();
		row.append("<tr>");
		row.append("<td>");
		switch (getEventType()) {
		case POINT_SCORED:
			row.append(getTeam().getName());
			row.append(" scored");
			break;
		case GAVE_OVER:
		case READY:
		case STARTED:
		default:
			row.append(getEventType().getName());
		}
		row.append("</td><td>");
		row.append(FORMATTER.format(getCreated()));
		row.append("</td");
		row.append("</tr>");
		return row.toString();
	}

	@Override
	public String toString() {
		switch (getEventType()) {
		case POINT_SCORED:
			return getTeam().getName() + " scored";
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
}
