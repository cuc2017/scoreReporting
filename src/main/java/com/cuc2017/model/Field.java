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
  private String atTag;
  private boolean useField;

  public Field() {
    // for default construction
  }

  public Field(String fieldName, String atTag) {
    this.fieldName = fieldName;
    this.setAtTag(atTag);
    this.setUseField(true);
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

  public String getAtTag() {
    return atTag;
  }

  public void setAtTag(String atTag) {
    this.atTag = atTag;
  }

  public boolean isUseField() {
    return useField;
  }

  public void setUseField(boolean useField) {
    this.useField = useField;
  }
}
