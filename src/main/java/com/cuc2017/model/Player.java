package com.cuc2017.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Player extends AbstractEntity {
  public static final int UNKNOWN_PLAYER = -1;
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

  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    if (getNumber() > UNKNOWN_PLAYER) {
      buffer.append(String.format("%02d ", getNumber()));
    }
    buffer.append(getFirstName());
    buffer.append(" ");
    buffer.append(getLastName());
    buffer.append(" ");
    return buffer.toString();
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
