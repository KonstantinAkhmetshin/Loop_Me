package com.loopme.service;

import com.loopme.dao.AppDao;
import com.loopme.dao.UserDao;
import com.loopme.domain.App;
import com.loopme.domain.AppType;
import com.loopme.domain.ContentType;
import com.loopme.domain.User;
import com.loopme.domain.UserRole;
import com.loopme.exception.UserNotFoundException;
import com.loopme.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacadeServiceImpl implements FacadeService
{
  // TODO : check, if email already registered.
  @Autowired
  private UserDao userDao;

  @Autowired
  private AppDao  appDao;

  @Override
  public User createPublisher( String name, String email )
  {
    return createUser( name, email, UserRole.PUBLISHER );
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
  public User createOperator( String name, String email )
  {
    return createUser( name, email, UserRole.ADOPS );
  }

  @Override
  public User editOperator( Integer id, String name, String email )
  {
    return null;
  }

  @Override
  public void deleteOperator( Integer id )
  {
    deleteUser(id);
  }

  // TODO : check input params
  @Override
  public App createApp( String name, User user, AppType type, List<ContentType> contentTypes )
  {
    App app = new App().setName(name).setUser(user).setType(type).setContentTypes(contentTypes);
    return appDao.save(app);
  }

  // TODO : check input params
  @Override
  public App editApp( Integer id, String name, User user, AppType type, List<ContentType> contentTypes )
  {
    List<App> appList = appDao.getAppById(id);
    if( appList.isEmpty() )
    {
      // TODO : change exception type
      throw new UserNotFoundException( "Cannot update user. User with id=" + id + " not found" );
    }
    App app = appList.get(0).setName(name).setUser(user).setType(type).setContentTypes(contentTypes);

    return appDao.save(app);
  }

  // TODO : check input params
  @Override
  public void deleteApp( Integer id )
  {
    appDao.delete(id);
  }

  // TODO : test method. remove
  @Override
  public void getUsers()
  {
    System.out.println( userDao.findAll() );
    System.out.println();
    System.out.println( appDao.findAll() );

  }

  private void deleteUser(Integer id){
    userDao.delete(id);
  }

  private User createUser( String name, String email, UserRole userRole )
  {
      Utils.validateEmail( email );
    User publisherUser = new User().setName( name ).setEmail( email ).setUserRole( userRole );
    return userDao.save( publisherUser );
  }

  private User updateUser( Integer id, String name, String email )
  {
    List<User> userList = userDao.getUserById( id );
    if( userList.isEmpty() )
    {
      throw new UserNotFoundException( "Cannot update user. User with id=" + id + " not found" );
    }
    User user = userList.get( 0 );
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
      return userDao.save( user );
    }
    return user;
  }

}
