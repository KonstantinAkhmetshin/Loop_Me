package com.loopme.service;

import com.loopme.domain.App;
import com.loopme.domain.AppType;
import com.loopme.domain.ContentType;
import com.loopme.domain.User;

import java.util.List;

public interface FacadeService
{
  String NO_VALUE = "";

  User createPublisher( String name, String email, String password );

  User editPublisher(Integer id, String name, String email );

  void deletePublisher( Integer id );

  User createOperator( String name, String email, String password );

  User editOperator(Integer id, String name, String email );

  void deleteOperator( Integer id );

  App createApp( String name, String type, List<String> contentTypes );

  App editApp( Integer id, String name, String type, List<String> contentTypes );

  void deleteApp( Integer id );

  List<User> getPublishers();

  List<User> getOperators();

  User getUserByName( String name );

  List<App> getApps();
}
