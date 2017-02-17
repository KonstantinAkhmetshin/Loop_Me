package com.loopme.controller;

import com.loopme.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationController
{

    @RequestMapping("/")
    public String root() {
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


}
