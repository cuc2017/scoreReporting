package com.cuc2017.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Team extends AbstractEntity implements Comparable<Team> {

  @ManyToOne
  private Division division;
  private String name;
  private String displayName;
  private int teamNumber;

  public Team() {
    // default constructor
  }

  @Override
  public String toString() {
    return getName();
  }

  public Team(Division division, String name, int ultimateCanadaTeamNumber) {
    this.division = division;
    this.name = name;
    this.displayName = name;
    this.teamNumber = ultimateCanadaTeamNumber;
  }

  @Override
  public int compareTo(Team other) {
    return this.getName().toLowerCase().compareTo(other.getName().toLowerCase());
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

  public int getTeamNumber() {
    return teamNumber;
  }

  public void setTeamNumber(int teamNumber) {
    this.teamNumber = teamNumber;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
