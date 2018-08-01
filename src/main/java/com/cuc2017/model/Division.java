package com.cuc2017.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Division extends AbstractEntity {
  @Column(unique = true)
  private String name;
  private int series;
  private String hashtag;

  public Division() {
    // default constructer
  }

  public Division(String name, int series, String hashtag) {
    this.name = name;
    this.setSeries(series);
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

  public int getSeries() {
    return series;
  }

  public void setSeries(int series) {
    this.series = series;
  }
}
