package com.cuc2017.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Player extends AbstractEntity {
	private String firstName;
	private String lastName;
	private int number;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
}
