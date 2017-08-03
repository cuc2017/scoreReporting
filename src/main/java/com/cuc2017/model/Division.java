package com.cuc2017.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Division extends AbstractEntity {
	@Column(unique = true)
	private String name;
	private String hashtag;

	public Division() {
		// default constructer
	}

	public Division(String name, String hashtag) {
		this.name = name;
		this.hashtag = hashtag;
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
}
