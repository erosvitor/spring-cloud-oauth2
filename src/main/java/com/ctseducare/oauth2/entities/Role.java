package com.ctseducare.oauth2.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements java.io.Serializable {

  private static final long serialVersionUID = 8147460135144072676L;

  private Integer id;
  private String name;
  private Set<Permission> permissions = new HashSet<>(0);

  public Role() {
  }

  public Role(String name) {
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "name", unique = true, nullable = false, length = 100)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "roles_permissions", 
    joinColumns = @JoinColumn(name = "idrole", nullable = false, updatable = false),
    inverseJoinColumns = @JoinColumn(name = "idpermission", nullable = false, updatable = false)
  )
  public Set<Permission> getPermissions() {
    return this.permissions;
  }

  public void setPermissions(Set<Permission> permissions) {
    this.permissions = permissions;
  }

}
