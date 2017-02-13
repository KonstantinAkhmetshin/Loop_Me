package com.loopme.utils;

import com.loopme.exception.InvalidEmailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
  public static final Pattern VALID_EMAIL_ADDRESS_REGEX
          = Pattern.compile( "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE );

  public static void validateEmail( String email )
  {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher( email );
    if( !matcher.find() )
    {
      throw new InvalidEmailException( "Invalid email." );
    }
  }
}
