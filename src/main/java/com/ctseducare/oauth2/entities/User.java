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
@Table(name = "users")
public class User implements java.io.Serializable {

  private static final long serialVersionUID = 6911310418608012848L;

  private Integer id;
  private String name;
  private String enabled;
  private String login;
  private String password;
  private String email;
  private Set<Role> roles = new HashSet<>(0);

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "name", nullable = false, length = 80)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "enabled", nullable = false, length = 5)
  public String getEnabled() {
    return this.enabled;
  }

  public void setEnabled(String enabled) {
    this.enabled = enabled;
  }

  @Column(name = "login", unique = true, nullable = false, length = 20)
  public String getLogin() {
    return this.login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @Column(name = "password", nullable = false, length = 100)
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "email", length = 40)
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "users_roles",
    joinColumns = @JoinColumn(name = "iduser", nullable = false, updatable = false),
    inverseJoinColumns = @JoinColumn(name = "idrole", nullable = false, updatable = false)
  )
  public Set<Role> getRoles() {
    return this.roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

}
