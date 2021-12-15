package com.itacademy.cms.security;

import com.itacademy.cms.model.User;
import com.itacademy.cms.security.jwt.JwtUser;
import com.itacademy.cms.security.jwt.JwtUserFactory;
import com.itacademy.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  private final UserService userService;

  @Autowired
  public JwtUserDetailsService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userService.findUserByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("User with username : " + email + " not found");
    }
    JwtUser jwtUser = JwtUserFactory.create(user);
    return jwtUser;
  }


}
