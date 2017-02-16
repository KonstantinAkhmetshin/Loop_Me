package com.loopme.dao;

import com.loopme.domain.App;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AppDao extends CrudRepository<App,Integer>
{
  App getAppById( Integer id );
}
