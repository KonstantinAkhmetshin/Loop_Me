package com.loopme.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "USERS")
public final class User implements Serializable
{
  @Id
  @Column(name = "ID", nullable = false)
  private final Integer  id;

  @Column(name = "NAME", nullable = false)
  private final String   name;

  @Column(name = "EMAIL", nullable = false)
  private final String   email;

  @Enumerated(EnumType.STRING)
  @Column(name = "USER_ROLE", nullable = false)
  private final UserRole userRole;

  private User( Integer id, String name, String email, UserRole userRole )
  {
    this.id = id;
    this.name = name;
    this.email = email;
    this.userRole = userRole;
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

  public static class UserBuilder
  {
    private Integer  id;
    private String   name;
    private String   email;
    private UserRole userRole;

    public UserBuilder setId( Integer id )
    {
      this.id = id;
      return this;
    }

    public UserBuilder setName( String name )
    {
      this.name = name;
      return this;
    }

    public UserBuilder setEmail( String email )
    {
      this.email = email;
      return this;
    }

    public UserBuilder setUserRole( UserRole userRole )
    {
      this.userRole = userRole;
      return this;
    }

    public User createUser()
    {
      return new User( id, name, email, userRole );
    }
  }
}
