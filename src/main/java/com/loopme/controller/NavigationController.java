package com.loopme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationController
{

  @RequestMapping("/index.html")
  public String index() {
    return "index";
  }

  @RequestMapping("/login.html")
  public String login() {
    return "login";
  }


  @RequestMapping("/login-error.html")
  public String loginError(Model model) {
    model.addAttribute("loginError", true);
    return "login";
  }
}
