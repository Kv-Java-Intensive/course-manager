package com.itacademy.cms.security;

import com.itacademy.cms.model.User;
import com.itacademy.cms.service.UserService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthProvider implements AuthenticationProvider {

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String name = authentication.getName();
    String password = (String) authentication.getCredentials();

    User user = userService.loadUserByName(name);

    if (user.getFirstName().equals(name)) {
      if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new BadCredentialsException("Wrong password");
      }

      Collection<? extends GrantedAuthority> authorities =
          List.of(new SimpleGrantedAuthority(user.getRole().name()));

      return new UsernamePasswordAuthenticationToken(user, password, authorities);
    } else {
      throw new BadCredentialsException("Username not found");
    }
  }

  public boolean supports(Class<?> arg) {
    return true;
  }
}