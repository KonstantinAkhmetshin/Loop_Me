package com.loopme.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "APPS" )
public class App
{
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @Column( name = "ID", nullable = false )
  private Integer           id;

  @Column( name = "NAME", nullable = false )
  private String            name;

  @Column( name = "TYPE", nullable = false )
  private AppType           type;

  @Column( name = "CONTENT_TYPE", nullable = false )
  @ElementCollection( targetClass = ContentType.class )
  @Enumerated( EnumType.STRING )
  private List<ContentType> contentTypes;

  @OneToOne( fetch = FetchType.LAZY )
  @JoinColumn( name = "USERS_ID" )
  private User              user;

  public Integer getId()
  {
    return id;
  }

  public App setId( Integer id )
  {
    this.id = id;
    return this;
  }

  public String getName()
  {
    return name;
  }

  public App setName( String name )
  {
    this.name = name;
    return this;
  }

  public AppType getType()
  {
    return type;
  }

  public App setType( AppType type )
  {
    this.type = type;
    return this;
  }

  public List<ContentType> getContentTypes()
  {
    return contentTypes;
  }

  public App setContentTypes( List<ContentType> contentTypes )
  {
    this.contentTypes = contentTypes;
    return this;
  }

  public User getUser()
  {
    return user;
  }

  public App setUser( User user )
  {
    this.user = user;
    return this;
  }

  @Override
  public String toString()
  {
    return "App{" + "id=" + id + ", name='" + name + '\'' + ", type=" + type + ", contentTypes=" + contentTypes + ", user=" + user + '}';
  }
}
