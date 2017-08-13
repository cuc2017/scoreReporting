package com.cuc2017.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Player extends AbstractEntity {
	public static final int UNKNOWN_PLAYER = -1;
	public static final int CALLAHAN = 100;
	private String firstName;
	private String lastName;
	private int number;
	private String ultimateCanadaPlayerId;
	@ManyToOne(cascade = CascadeType.ALL)
	private Game game;
	@ManyToOne
	private Team team;

	public Player() {
		// default Constructor
	}

	public Player(int number, String firstName, String lastName, Team team) {
		this.number = number;
		this.firstName = firstName;
		this.lastName = lastName;
		this.team = team;
	}

	public Player(int number, String firstName, String lastName, Team team, Game game) {
		this(number, firstName, lastName, team);
		this.game = game;
	}

	public Player(int number, String firstName, String lastName, Team team, String ultimateCanadaPlayerId) {
		this(number, firstName, lastName, team);
		this.setUltimateCanadaPlayerId(ultimateCanadaPlayerId);
	}

	public Player(int number, String firstName, String lastName, Team team, String ultimateCanadaPlayerId, Game game) {
		this(number, firstName, lastName, team, ultimateCanadaPlayerId);
		this.game = game;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if (isCallahanPlayer()) {
			buffer.append("XX ");
		} else if (getNumber() > UNKNOWN_PLAYER) {
			buffer.append(String.format("%02d ", getNumber()));
		}
		buffer.append(getFirstName());
		buffer.append(" ");
		buffer.append(getLastName());
		buffer.append(" ");
		return buffer.toString();
	}

	public boolean isCallahanPlayer() {
		return getNumber() == CALLAHAN;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getUltimateCanadaPlayerId() {
		return ultimateCanadaPlayerId;
	}

	public void setUltimateCanadaPlayerId(String ultimateCanadaPlayerId) {
		this.ultimateCanadaPlayerId = ultimateCanadaPlayerId;
	}
}
