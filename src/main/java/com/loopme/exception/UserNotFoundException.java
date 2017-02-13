package com.loopme.exception;

public class UserNotFoundException extends RuntimeException
{
  public UserNotFoundException( String message )
  {
    super( message );
  }
}
