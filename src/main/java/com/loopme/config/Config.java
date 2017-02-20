package com.loopme.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class Config implements ApplicationContextAware
{
  public Config()
  {
    super();
  }

  private ApplicationContext applicationContext;

  public void setApplicationContext( final ApplicationContext applicationContext ) throws BeansException
  {
    this.applicationContext = applicationContext;
  }

  @Bean
  public SpringResourceTemplateResolver templateResolver()
  {
    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    templateResolver.setApplicationContext( this.applicationContext );
    templateResolver.setPrefix( "/WEB-INF/" );
    templateResolver.setSuffix( ".html" );
    templateResolver.setTemplateMode( SpringResourceTemplateResolver.DEFAULT_TEMPLATE_MODE );

    templateResolver.setCacheable( true );
    return templateResolver;
  }

  @Bean
  public SpringSecurityDialect securityDialect()
  {
    return new SpringSecurityDialect();
  }

}
