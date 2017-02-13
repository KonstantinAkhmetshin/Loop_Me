package com.loopme.domain;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

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

  @Column( name = "CONTENT_TYPEc", nullable = false )
  @ElementCollection( targetClass = ContentType.class )
  @Enumerated( EnumType.STRING )
  private List<ContentType> contentTypes;

  @Column( name = "USER", nullable = false )
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
