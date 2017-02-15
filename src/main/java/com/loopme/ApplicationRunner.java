package com.loopme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ApplicationRunner extends SpringBootServletInitializer
{

//  public ApplicationRunner() {
//    super();
//    setRegisterErrorPageFilter(false); // <- this one
//  }

  @Override
  protected SpringApplicationBuilder configure( SpringApplicationBuilder application )
  {
    return application.sources( ApplicationRunner.class );
  }

  public static void main( String[] args )
  {
    SpringApplication.run(ApplicationRunner.class, args);
  }


}
