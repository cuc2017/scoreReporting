package com.cuc2017.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Base class to derive entity classes from.
 *
 * @author Rob Tyson
 */
@MappedSuperclass
public class AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created", nullable = false)
  private Date created;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated", nullable = false)
  private Date updated;

  @PrePersist
  protected void onCreate() {
    setCreated(new Date());
    setUpdated(getCreated());
  }

  @PreUpdate
  protected void onUpdate() {
    setUpdated(new Date());
  }

  public Long getId() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }

    if (this.id == null || obj == null || !this.getClass().equals(obj.getClass())) {
      return false;
    }

    AbstractEntity that = (AbstractEntity) obj;

    return this.id.equals(that.getId());
  }

  @Override
  public int hashCode() {
    return id == null ? 0 : id.hashCode();
  }

  public Date getUpdated() {
    return updated;
  }

  private void setUpdated(Date updated) {
    this.updated = updated;
  }

  public Date getCreated() {
    return created;
  }

  private Date setCreated(Date created) {
    this.created = created;
    return created;
  }
}
