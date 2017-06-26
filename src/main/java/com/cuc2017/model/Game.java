package com.cuc2017.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Game extends AbstractEntity {

	@ManyToOne
	private Team homeTeam;
	@ManyToOne
	private Team awayTeam;
	@ManyToOne
	private Field field;

	public Game() {
		// default constructor
	}

	public Game(Team homeTeam, Team awayTeam, Field field) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.field = field;
	}

	@Override
	public String toString() {
		return getHomeTeam() + " vs " + getAwayTeam() + " @ " + getField();
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
}
