package com.loopme.repository;

import com.loopme.domain.App;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AppRepository extends CrudRepository<App,Integer>
{
  App getAppById( Integer id );
  List<App> findAll();
  List<App> getAppByUserName( String name );
}
