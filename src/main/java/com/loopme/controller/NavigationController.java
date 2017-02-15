package com.loopme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationController
{
  @RequestMapping("/")
  public String welcome()
  {
    return "index";
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }


  @RequestMapping("/login-error")
  public String loginError(Model model) {
    model.addAttribute("loginError", true);
    return "login";
  }
}
