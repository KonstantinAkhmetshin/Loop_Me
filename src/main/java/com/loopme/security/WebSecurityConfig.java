package com.loopme.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{


  @Autowired
  UserDetailsService userDetailsService;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Override
  protected void configure( final HttpSecurity http ) throws Exception
  {
    http.formLogin()
                .loginPage("/login.html")
                .successForwardUrl("/index.html")
                .failureUrl("/login-error.html")
            .and()
            .logout()
                .logoutSuccessUrl("/login.html")
            .and()
               .authorizeRequests()
                       .antMatchers("/**").permitAll()
//               .antMatchers("/login.html").permitAll()
//               .antMatchers("/publisher/**", "/operator/**").hasRole("ADMIN")
//               .antMatchers("/publisher/**", "/app/**").hasRole("ADOPS")
//               .antMatchers("/app/**").hasRole("PUBLISHER")
            .and()
               .exceptionHandling()
            .accessDeniedPage("/403.html");

  }


  @Autowired
  public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception
  {
    auth.userDetailsService( userDetailsService );//.passwordEncoder( bCryptPasswordEncoder() );
  }
}

