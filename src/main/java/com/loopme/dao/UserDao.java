package com.loopme.dao;

import com.loopme.domain.User;
import com.loopme.domain.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface  UserDao extends CrudRepository<User,Integer>
{
  User getUserById( Integer id );
  User getUserByName( String name );
  List<User> getUserByUserRole( UserRole userRole );
}
