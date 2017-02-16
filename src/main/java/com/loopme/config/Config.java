package com.loopme.config;


import org.springframework.beans.BeansException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;


@Configuration
public class Config implements ApplicationContextAware
{
  private ApplicationContext applicationContext;

  public Config() {
    super();
  }


  public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Bean
  public SpringResourceTemplateResolver templateResolver(){
    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    templateResolver.setApplicationContext(this.applicationContext);
    templateResolver.setPrefix("/WEB-INF/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(SpringResourceTemplateResolver.DEFAULT_TEMPLATE_MODE);

    templateResolver.setCacheable(true);
    return templateResolver;
  }

  @Bean
  public SpringSecurityDialect securityDialect() {
    return new SpringSecurityDialect();
  }

//  @Bean
//  public SpringTemplateEngine templateEngine(){
//    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
////    templateEngine.setEnableSpringELCompiler(true); // Compiled SpringEL should speed up executions
//    templateEngine.setTemplateResolver(templateResolver());
//    templateEngine.addDialect(new SpringSecurityDialect());
//    return templateEngine;
//  }
//
//  @Bean
//  public ThymeleafViewResolver viewResolver(){
//    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//    viewResolver.setTemplateEngine(templateEngine());
//    return viewResolver;
//  }

}
