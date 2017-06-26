package com.cuc2017.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Team extends AbstractEntity {

	@ManyToOne
	private Division division;
	@Column(unique = true)
	private String name;

	public Team() {
		// default constructor
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName() + " in " + getDivision();
	}

	public Team(Division division, String name) {
		this.division = division;
		this.name = name;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
