package com.loopme.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.loopme.utils.Utils;

@Controller
public class NavigationController
{
  @RequestMapping( "/" )
  public String root()
  {
    return "redirect:/index.html";
  }

  @RequestMapping("/index.html")
  public String index() {
    if(Utils.isAutorizedUser()){
      return "index";
    }
    return "redirect:/login.html";
  }

  @RequestMapping(value="/login.html" , method = {RequestMethod.GET, RequestMethod.POST})
  public String login(Model model, @RequestParam( value = "fail", defaultValue = "false") boolean isFail ) {
    model.addAttribute("loginError", isFail);
    return "login";
  }

  @RequestMapping("/403.html")
  public String accessDenied() {
    return "403";
  }


  @RequestMapping(value="/logout", method = RequestMethod.GET)
  public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null){
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login?logout";
  }

}
