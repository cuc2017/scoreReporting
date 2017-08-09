package com.cuc2017;

public class Roster {

  private String type;
  private String first_name;
  private String last_name;
  private String team_name;
  private String roles;
  private int uniform_number;
  private String division;

  @Override
  public String toString() {
    return getFirst_name() + " " + getLast_name() + " team: " + team_name + " division: " + division + " number: "
        + uniform_number;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getTeam_name() {
    return team_name;
  }

  public void setTeam_name(String team_name) {
    this.team_name = team_name;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  public int getUniform_number() {
    return uniform_number;
  }

  public void setUniform_number(int uniform_number) {
    this.uniform_number = uniform_number;
  }

  public String getDivision() {
    return division;
  }

  public void setDivision(String division) {
    this.division = division;
  }

  public boolean isPlayer() {
    return getRoles().contains("captain") || getRoles().contains("player");
  }
}
