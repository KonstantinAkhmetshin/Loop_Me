package com.loopme.controller;

import com.loopme.domain.App;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdRegController
{
  @Autowired
  FacadeService service;


  //  ========================================================  PUBLISHERS =================================================


  @RequestMapping( value = "/publisher/get", method =  RequestMethod.GET  )
  @ResponseBody
  public List<User> getPublishers()
  {
    return service.getPublishers();
  }


  @RequestMapping( value = "/publisher/create", method = { RequestMethod.POST, RequestMethod.GET } )
  public ResponseEntity<?> createPublisher(@RequestParam( value = "name", defaultValue = "" ) String name,
                                           @RequestParam( value = "email", defaultValue = "" ) String email,
                                           @RequestParam( value = "password", defaultValue = "" ) String password)
  {
    service.createPublisher(name, email, password);
    return new ResponseEntity<>( HttpStatus.OK );
  }

  @RequestMapping( value = "/publisher/edit", method = {RequestMethod.POST, RequestMethod.GET} )
  public ResponseEntity<?> editPublisher( @RequestParam( value = "id", required = true) Integer id,
                             @RequestParam( value = "name", defaultValue = "" ) String name,
                             @RequestParam( value = "email", defaultValue = "" ) String email )
  {
    service.editPublisher(id, name, email);
    return new ResponseEntity<>( HttpStatus.OK );
  }

  @RequestMapping( value = "/publisher/delete", method = RequestMethod.GET )

  public ResponseEntity<?> deletePublisher(@RequestParam( value = "id", required = true ) Integer id )
  {
    service.deletePublisher(id);
    return new ResponseEntity<>( HttpStatus.OK );
  }

  //  ========================================================  OPERATORS =================================================


  @RequestMapping( value = "/operator/get", method =  RequestMethod.GET  )
  @ResponseBody
  public List<User> getOperators()
  {
    return service.getOperators();
  }

  @RequestMapping( value = "/operator/create", method = RequestMethod.POST )
  public ResponseEntity<?> createOperator( @RequestParam( value = "name", required = true ) String name,
                                           @RequestParam( value = "email", required = true ) String email,
                                           @RequestParam( value = "password", required = true ) String password)
  {
    service.createOperator(name, email, password);
    return new ResponseEntity<>( HttpStatus.OK );
  }

  @RequestMapping( value = "/operator/edit", method = RequestMethod.POST )
  public ResponseEntity<?> editOperator( @RequestParam( value = "id", required = true) Integer id,
                                         @RequestParam( value = "name", defaultValue = "" ) String name,
                                         @RequestParam( value = "email", defaultValue = "" ) String email )
  {
    service.editPublisher(id, name, email);
    return new ResponseEntity<>( HttpStatus.OK );
  }

  @RequestMapping( value = "/operator/delete", method = RequestMethod.GET )
  public ResponseEntity<?> deleteOperator(@RequestParam( value = "id", required = true ) Integer id )
  {
    service.deleteOperator(id);
    return new ResponseEntity<>( HttpStatus.OK );
  }


//  ========================================================  APPS =================================================


  @RequestMapping( value = "/app/typencontent", method =  RequestMethod.GET  )
  @ResponseBody
  public Map<String, Object>  getTypesAndContents()
  {
    Map<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("appType", AppType.values());
    stringObjectMap.put("contentType", ContentType.values());

    return stringObjectMap;
  }


  @RequestMapping( value = "/app/get", method =  RequestMethod.GET  )
  @ResponseBody
  public List<App> getApps()
  {
    return service.getApps();
  }

// создать приложение
  // TODO : check inputs
  @RequestMapping( value = "/app/create", method = { RequestMethod.POST, RequestMethod.GET } )
  public ResponseEntity<?> createApp( @RequestParam( value = "name", required = true ) String name,
                                     @RequestParam( value = "type", required = true ) String type,
                                     @RequestParam( value = "contentType", required = true ) List<String> contentTypes
  )
  {
    service.createApp(name, type, contentTypes);
    return new ResponseEntity<>( HttpStatus.OK );

  }

// обновить приложение
  @RequestMapping( value = "/app/edit", method = RequestMethod.POST )
  public ResponseEntity<?> editApp( @RequestParam( value = "id", required = true ) Integer id,
                                   @RequestParam( value = "name", defaultValue = "" ) String name,
                                   @RequestParam( value = "type", required = true ) String type,
                                   @RequestParam( value = "contentType", required = true ) List<String> contentTypes )
  {
    service.editApp(id, name, type, contentTypes);
    return new ResponseEntity<>( HttpStatus.OK );

  }

// удалить приложение
  @RequestMapping( value = "/app/delete", method = RequestMethod.GET )
  public ResponseEntity<?> deleteApp(@RequestParam( value = "id", required = true ) Integer id )
  {
    service.deleteApp(id);
    return new ResponseEntity<>( HttpStatus.OK );

  }

//  ========================================================  Exception Handler =================================================

  @ExceptionHandler( Exception.class )
  public ResponseEntity<?> handleAllException( Exception ex )
  {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
  }
}
