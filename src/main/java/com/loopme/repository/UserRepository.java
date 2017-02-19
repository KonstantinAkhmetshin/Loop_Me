package com.loopme.repository;

import com.loopme.domain.User;
import com.loopme.domain.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends CrudRepository<User,Integer>
{
  User getUserById( Integer id );
  User getUserByName( String name );
  List<User> getUserByUserRole( UserRole userRole );
}
