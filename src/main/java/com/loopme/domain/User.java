package com.loopme.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "USERS" )
public final class User implements Serializable
{
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @Column( name = "ID" )
  private Integer  id;

  // TODO :make this field unique
  @Column( name = "NAME", nullable = false )
  private String   name;

  @Column( name = "EMAIL", nullable = false )
  private String   email;

  @Enumerated( EnumType.STRING )
  @Column( name = "USER_ROLE", nullable = false )
  private UserRole userRole;

  @JsonIgnore
  @Column( name = "PASSWORD", nullable = false )
  private String password;


  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  public Integer getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }

  public UserRole getUserRole()
  {
    return userRole;
  }

  public User setId( Integer id )
  {
    this.id = id;
    return this;
  }

  public User setName( String name )
  {
    this.name = name;
    return this;
  }

  public User setEmail( String email )
  {
    this.email = email;
    return this;
  }

  public User setUserRole( UserRole userRole )
  {
    this.userRole = userRole;
    return this;
  }

  @Override
  public String toString()
  {
    return "User{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", userRole=" + userRole + '}';
  }
}
