package com.loopme.dao;

import com.loopme.domain.App;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AppDao extends CrudRepository<App, String> {
}
