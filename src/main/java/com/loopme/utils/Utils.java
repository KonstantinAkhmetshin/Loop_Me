package com.loopme.utils;

import com.loopme.domain.User;
import com.loopme.exception.InvalidEmailException;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
  public static final String  USER_KEY_IN_SESSION       = "user";
  public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile( "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE );

  public static void validateEmail( String email )
  {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher( email );
    if( !matcher.find() )
    {
      throw new InvalidEmailException( "Invalid email." );
    }
  }

  public static User getUserFromSession( HttpSession httpSession )
  {
    return (User)httpSession.getAttribute( USER_KEY_IN_SESSION );
  }

  public static void setUserToSession( HttpSession httpSession, User user )
  {
    httpSession.setAttribute( USER_KEY_IN_SESSION, user );
  }
}
