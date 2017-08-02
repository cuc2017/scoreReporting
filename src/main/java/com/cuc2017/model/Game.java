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

	public Game() {
		// default constructor
	}

	public Game(Division division, Team homeTeam, Team awayTeam, Field field) {
		this.division = division;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.field = field;
	}

	@Override
	public String toString() {
		return getDivision() + " " + getHomeTeam() + " vs " + getAwayTeam() + " @ " + getField();
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
}
