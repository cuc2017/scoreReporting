package com.cuc2017.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Division extends AbstractEntity {
	@Column(unique = true)
	private String name;

	public Division() {
		// default constructer
	}

	@Override
	public String toString() {
		return getName();
	}

	public Division(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
