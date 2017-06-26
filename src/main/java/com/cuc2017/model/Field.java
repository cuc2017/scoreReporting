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

	public Field() {
		// for default construction
	}

	public Field(String fieldName) {
		this.fieldName = fieldName;
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
}
