package com.ctseducare.oauth2.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissions")
public class Permission implements java.io.Serializable {

  private static final long serialVersionUID = 2111523595550907151L;

  private Integer id;
  private String name;

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "name", unique = true, nullable = false, length = 200)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
