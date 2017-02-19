package com.loopme.utils;

import com.loopme.domain.ContentType;
import com.loopme.domain.User;
import com.loopme.exception.InvalidEmailException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
  public static final String  USER_KEY_IN_SESSION       = "user";
  public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile( "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE );


//  TODO : check
  public static void validateEmail( String email )
  {
//    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher( email );
//    if( !matcher.find() )
//    {
//      throw new InvalidEmailException( "Invalid email." );
//    }
  }

  public static boolean isAutorizedUser()
  {
    return !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser");
  }

  public static List<ContentType> transformToContentTypes(List<String> stringValues){
    List<ContentType> types = new ArrayList<>();
    for(String value : stringValues)
    {
      types.add(ContentType.valueOf(value));
    }
    return types;
  }

}
