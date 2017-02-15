package com.loopme.controller;

import com.loopme.domain.AppType;
import com.loopme.domain.ContentType;
import com.loopme.domain.User;
import com.loopme.service.FacadeService;
import com.loopme.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/data")
public class AdRegController
{
  @Autowired
  FacadeService service;

  @RequestMapping( value = "/getUsers", method = { RequestMethod.GET } )
  public void getUsers()
  {
    service.getUsers();
  }

// TODO : login point, add current user to Session after successful login
  @RequestMapping( value = "/login", method = { RequestMethod.GET } )
  public void createPublisher( HttpSession session,
                               @RequestParam( value = "name", required = true ) String name)
  {
    User user = service.getUserByName(name);
    Utils.setUserToSession(session, user);
  }

// создать паблишера
  @RequestMapping( value = "/create/publisher", method = { RequestMethod.POST, RequestMethod.GET } )
  public void createPublisher( HttpSession session,
                               @RequestParam( value = "name", required = true ) String name,
                               @RequestParam( value = "email", required = true ) String email )
  {
    service.createPublisher( name, email );
  }

// редактировать паблишера
  @RequestMapping( value = "/edit/publisher", method = RequestMethod.POST )
  public void editPublisher( HttpSession session,
                             @RequestParam( value = "id", required = true ) Integer id,
                             @RequestParam( value = "name", defaultValue = "" ) String name,
                             @RequestParam( value = "email", defaultValue = "" ) String email )
  {
    service.editPublisher( id, name, email );
  }

// удалить паблишера
  @RequestMapping( value = "delete/publisher", method = RequestMethod.GET )
  public void deletePublisher( HttpSession session,
                               @RequestParam( value = "id", required = true ) Integer id )
  {
    service.deletePublisher( id );
  }

// создать Оператора
  @RequestMapping( value = "/create/operator", method = RequestMethod.POST )
  public void createOperator( HttpSession session,
                              @RequestParam( value = "name", required = true ) String name,
                              @RequestParam( value = "email", required = true ) String email )
  {
    service.createOperator( name, email );
  }

// редактировать Оператора
  @RequestMapping( value = "/edit/operator", method = RequestMethod.POST )
  public void editOperator( HttpSession session,
                            @RequestParam( value = "id", required = true ) Integer id,
                            @RequestParam( value = "name", defaultValue = "" ) String name,
                            @RequestParam( value = "email", defaultValue = "" ) String email )
  {
    service.editOperator( id, name, email );
  }

// удалить Оператора
  @RequestMapping( value = "delete/operator", method = RequestMethod.GET )
  public void deleteOperator( HttpSession session,
                              @RequestParam( value = "id", required = true ) Integer id )
  {
    service.deleteOperator( id );
  }

// создать приложение
  // TODO : check inputs
  @RequestMapping( value = "/create/app", method = { RequestMethod.POST, RequestMethod.GET } )
  public void createApp( HttpSession session,
                         @RequestParam( value = "name", required = true ) String name,
                         @RequestParam( value = "type", required = true ) AppType type,
                         @RequestParam( value = "contentType", required = true ) List<ContentType> contentTypes )
  {
    service.createApp( name, Utils.getUserFromSession(session), type, contentTypes );
  }

// обновить приложение
  @RequestMapping( value = "/edit/app", method = RequestMethod.POST )
  public void editApp( HttpSession session,
                       @RequestParam( value = "id", required = true ) Integer id,
                       @RequestParam( value = "name", defaultValue = "" ) String name,
                       @RequestParam( value = "type", required = true ) AppType type,
                       @RequestParam( value = "contentType", required = true ) List<ContentType> contentTypes )
  {
    service.editApp( id, name, new User(), type, contentTypes );
  }

// удалить приложение
  @RequestMapping( value = "delete/app", method = RequestMethod.GET )
  public void deleteApp( HttpSession session,
                         @RequestParam( value = "id", required = true ) Integer id )
  {
    service.deleteApp( id );
  }

  // TODO : dose not process exceptions correctly

  @ExceptionHandler( Exception.class )
  public ModelAndView handleAllException( Exception ex )
  {

    ModelAndView model = new ModelAndView( "error" );
    model.addObject( "errCode", ex );
    model.addObject( "errMsg", ex.getMessage() );
    return model;

  }
}
