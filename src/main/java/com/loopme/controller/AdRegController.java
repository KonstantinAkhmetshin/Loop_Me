package com.loopme.controller;

import com.loopme.domain.AppType;
import com.loopme.domain.ContentType;
import com.loopme.domain.User;
import com.loopme.service.FacadeService;
import com.loopme.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdRegController
{
  @Autowired
  FacadeService service;


  @RequestMapping( value = "/publisher/get", method =  RequestMethod.GET  )
  @ResponseBody
  public List<User> getPublishers()
  {
    return service.getPublishers();
  }

// создать паблишера
  @RequestMapping( value = "/publisher/create", method = { RequestMethod.POST, RequestMethod.GET } )
  public ResponseEntity<?> createPublisher(@RequestParam( value = "name", defaultValue = "" ) String name,
                                           @RequestParam( value = "email", defaultValue = "" ) String email,
                                           @RequestParam( value = "password", defaultValue = "" ) String password)
  {
    service.createPublisher(name, email, password);
    return new ResponseEntity<>( HttpStatus.OK );
  }

// редактировать паблишера
  @RequestMapping( value = "/publisher/edit", method = RequestMethod.POST )
  public void editPublisher( @RequestParam( value = "id", required = true ) Integer id,
                             @RequestParam( value = "name", defaultValue = "" ) String name,
                             @RequestParam( value = "email", defaultValue = "" ) String email )
  {
    service.editPublisher(id, name, email);
  }

// удалить паблишера
  @RequestMapping( value = "/publisher/delete", method = RequestMethod.GET )

  public ResponseEntity<?> deletePublisher(@RequestParam( value = "id", required = true ) Integer id )
  {
    service.deletePublisher(id);
    return new ResponseEntity<>( HttpStatus.OK );
  }

// создать Оператора
  @RequestMapping( value = "/operator/create", method = RequestMethod.POST )
  public void createOperator( @RequestParam( value = "name", required = true ) String name,
                              @RequestParam( value = "email", required = true ) String email,
                              @RequestParam( value = "email", required = true ) String password)
  {
    service.createOperator(name, email, password);
  }

// редактировать Оператора
  @RequestMapping( value = "/operator/edit", method = RequestMethod.POST )
  public void editOperator( HttpSession session,
                            @RequestParam( value = "id", required = true ) Integer id,
                            @RequestParam( value = "name", defaultValue = "" ) String name,
                            @RequestParam( value = "email", defaultValue = "" ) String email )
  {
    service.editOperator(id, name, email);
  }

// удалить Оператора
  @RequestMapping( value = "/operator/delete", method = RequestMethod.GET )
  public void deleteOperator( HttpSession session,
                              @RequestParam( value = "id", required = true ) Integer id )
  {
    service.deleteOperator(id);
  }

// создать приложение
  // TODO : check inputs
  @RequestMapping( value = "/app/create", method = { RequestMethod.POST, RequestMethod.GET } )
  public void createApp( HttpSession session,
                         @RequestParam( value = "name", required = true ) String name,
                         @RequestParam( value = "type", required = true ) AppType type,
                         @RequestParam( value = "contentType", required = true ) List<ContentType> contentTypes )
  {
    service.createApp(name, Utils.getUserFromSession(session), type, contentTypes);
  }

// обновить приложение
  @RequestMapping( value = "/app/edit", method = RequestMethod.POST )
  public void editApp( HttpSession session,
                       @RequestParam( value = "id", required = true ) Integer id,
                       @RequestParam( value = "name", defaultValue = "" ) String name,
                       @RequestParam( value = "type", required = true ) AppType type,
                       @RequestParam( value = "contentType", required = true ) List<ContentType> contentTypes )
  {
    service.editApp(id, name, new User(), type, contentTypes);
  }

// удалить приложение
  @RequestMapping( value = "/app/delete", method = RequestMethod.GET )
  public void deleteApp( HttpSession session,
                         @RequestParam( value = "id", required = true ) Integer id )
  {
    service.deleteApp(id);
  }

  @RequestMapping(value="/logout", method = RequestMethod.GET)
  public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null){
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login?logout";
  }

  @ExceptionHandler( Exception.class )
  public ResponseEntity<?> handleAllException( Exception ex )
  {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
  }
}
