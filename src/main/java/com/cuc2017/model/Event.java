package com.cuc2017.model;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Event extends AbstractEntity {

	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("hh:mm");
	private EventType eventType;
	@ManyToOne
	@JoinColumn(name = "game_id")
	@JsonManagedReference
	private Game game;
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	// @ManyToMany
	// @JoinColumn(name = "player_id")
	// private Player scoredBy;
	// @ManyToMany
	// @JoinColumn(name = "player_id")
	// private Player assitedBy;

	private boolean useEvent = true;

	static {
		TimeZone timeZone = TimeZone.getTimeZone("America/New_York");
		FORMATTER.setTimeZone(timeZone);
	}

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
		case HALF_TIME:
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
		row.append("<tr data-event-id=");
		row.append(getId());
		row.append(">");
		row.append("<td>");
		switch (getEventType()) {
		case POINT_SCORED:
			row.append(getTeam().getName());
			row.append(" scored");
			break;
		case HALF_TIME:
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
