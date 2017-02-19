package com.loopme.service;

import com.loopme.repository.AppRepository;
import com.loopme.repository.UserRepository;
import com.loopme.domain.App;
import com.loopme.domain.AppType;
import com.loopme.domain.ContentType;
import com.loopme.domain.User;
import com.loopme.domain.UserRole;
import com.loopme.exception.UserNotFoundException;
import com.loopme.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacadeServiceImpl implements FacadeService
{
  // TODO : check, if email already registered.
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AppRepository appRepository;

  @Override
  public User createPublisher( String name, String email, String password )
  {
    return createUser(name, email, UserRole.PUBLISHER, password);
  }

  @Override
  public User editPublisher( Integer id, String name, String email )
  {
    return updateUser(id, name, email);
  }

  @Override
  public void deletePublisher( Integer id )
  {
    deleteUser(id);
  }

  @Override
  public User createOperator( String name, String email, String password )
  {
    return createUser(name, email, UserRole.ADOPS, password);
  }

  @Override
  public User editOperator( Integer id, String name, String email )
  {
    return updateUser(id, name, email);
  }

  @Override
  public void deleteOperator( Integer id )
  {
    deleteUser(id);
  }

  // TODO : check input params
  @Override
  public App createApp( String name, String type, List<String> contentTypes )
  {
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepository.getUserByName(userName);
    App app = new App().setName(name).setUser(user).setType(AppType.valueOf(type)).setContentTypes(Utils.transformToContentTypes(contentTypes));
    return appRepository.save(app);
  }

  // TODO : check input params
  @Override
  public App editApp( Integer id, String name,  String type, List<String> contentTypes )
  {
    App app = appRepository.getAppById(id);
    if( app == null )
    {
      // TODO : change exception type
      throw new UserNotFoundException( "Cannot update user. User with id=" + id + " not found" );
    }
    app.setName(name).setType(AppType.valueOf(type)).setContentTypes(Utils.transformToContentTypes(contentTypes));

    return appRepository.save(app);
  }

  // TODO : check input params
  @Override
  public void deleteApp( Integer id )
  {
    App app = appRepository.getAppById(id);
    appRepository.delete(app);
  }

  @Override
  public List<User> getPublishers() {
    return userRepository.getUserByUserRole(UserRole.PUBLISHER);
  }

  @Override
  public List<User> getOperators()
  {
    return userRepository.getUserByUserRole( UserRole.ADOPS );
  }

  @Override
  public User getUserByName(String name) {
    User user = userRepository.getUserByName(name);
    if( user == null )
    {
      throw new UserNotFoundException( "Cannot update user. User with name : " + name + " not found" );
    }
    return user;
  }

  @Override
  public List<App> getApps() {
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepository.getUserByName(userName);
    if(user.getUserRole() == UserRole.ADOPS){
      return appRepository.findAll();
    }
    return appRepository.getAppByUserName(userName);
  }

  private void deleteUser(Integer id){
    User user = userRepository.getUserById(id);
    userRepository.delete(user);
  }

  private User createUser( String name, String email, UserRole userRole, String password )
  {
    Utils.validateEmail( email );
    User publisherUser = new User().setName( name ).setEmail( email ).setUserRole( userRole ).setPassword(password);
    return userRepository.save( publisherUser );
  }

  private User updateUser( Integer id, String name, String email )
  {
    User user = userRepository.getUserById( id );
    if( user == null )
    {
      throw new UserNotFoundException( "Cannot update user. User with id=" + id + " not found" );
    }
    boolean changeFlag = false;
    if( ! ( email.equals( NO_VALUE ) || user.getEmail().equals( email ) ) )
    {
      Utils.validateEmail( email );
      user.setEmail( email );
      changeFlag = true;
    }
    if( ! ( name.equals( NO_VALUE ) || user.getName().equals( name ) ) )
    {
      user.setName( name );
      changeFlag = true;
    }
    if( changeFlag )
    {
      return userRepository.save( user );
    }
    return user;
  }

}
