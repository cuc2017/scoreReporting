package com.cuc2017.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Field class
 *
 * @author Rob Tyson
 */
@Entity
public class Field extends AbstractEntity {

	@Column(unique = true)
	private String fieldName;
	private String hashtag;

	public Field() {
		// for default construction
	}

	public Field(String fieldName, String hashtag) {
		this.fieldName = fieldName;
		this.setHashtag(hashtag);
	}

	@Override
	public String toString() {
		return getFieldName();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
}
