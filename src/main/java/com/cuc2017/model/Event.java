package com.cuc2017.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Event extends AbstractEntity {

	private EventType eventType;
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

	public Event() {
		// default constructor
	}

	public Event(EventType eventType, Game game) {
		this.setEventType(eventType);
		this.game = game;
	}

	public String tweetString() {
		return getEventType().getName() + ":";
	}

	@Override
	public String toString() {
		return getEventType().getName();
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
}
