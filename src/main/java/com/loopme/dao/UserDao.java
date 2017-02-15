package com.loopme.dao;

import com.loopme.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface  UserDao extends CrudRepository<User,Integer>
{
  List<User> getUserById( Integer id );
  List<User> getUserByName( String name );
}
