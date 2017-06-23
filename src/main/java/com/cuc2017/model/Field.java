package com.cuc2017.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Field extends AbstractEntity {

	@Column(name = "FIELD_NAME", unique = true)
	private String fieldName;

	public Field() {
		// for default construction
	}

	public Field(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
