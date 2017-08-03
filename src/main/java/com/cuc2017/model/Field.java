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
  private String atTag;

  public Field() {
    // for default construction
  }

  public Field(String fieldName, String hashtag, String atTag) {
    this.fieldName = fieldName;
    this.setHashtag(hashtag);
    this.setAtTag(atTag);
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

  public String getAtTag() {
    return atTag;
  }

  public void setAtTag(String atTag) {
    this.atTag = atTag;
  }
}
