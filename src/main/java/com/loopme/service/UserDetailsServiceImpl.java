package com.loopme.service;

import com.loopme.repository.UserRepository;
import com.loopme.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional( readOnly = true )
  public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException
  {
    User user = userRepository.getUserByName( username );

    if( user == null )
    {
      throw new UsernameNotFoundException( "No user with name '" + username + "' found." );
    }
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

    grantedAuthorities.add( new SimpleGrantedAuthority( user.getUserRole().name() ) );

    return new org.springframework.security.core.userdetails.User( user.getName(), user.getPassword(), grantedAuthorities );
  }
}
