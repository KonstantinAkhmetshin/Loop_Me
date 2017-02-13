package com.loopme.domain;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "USERS")
public class App
{
  @Id
  @Column(name = "ID", nullable = false)
  private final Integer           id;

  @Column(name = "NAME", nullable = false)
  private final String            name;

  @Column(name = "TYPE", nullable = false)
  private final AppType           type;

//  @OneToMany(mappedBy = "")
  @ElementCollection(targetClass = ContentType.class)
  private final List<ContentType> contentTypes;

  @Column(name = "USER", nullable = false)
  private final User              user;

  public App( User user, List<ContentType> contentTypes, AppType type, String name, Integer id )
  {
    this.user = user;
    this.contentTypes = contentTypes;
    this.type = type;
    this.name = name;
    this.id = id;
  }

  public Integer getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public AppType getType()
  {
    return type;
  }

  public List<ContentType> getContentTypes()
  {
    return contentTypes;
  }

  public User getUser()
  {
    return user;
  }
}
