package com.itacademy.cms.security.jwt;

import com.itacademy.cms.model.User;
import com.itacademy.cms.model.enums.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {

  public JwtUserFactory() {
  }

  public static JwtUser create(User user) {
    List<Role> roles = new ArrayList<>();
    roles.add(user.getRole());
    return new JwtUser(
        user.getId(),
        user.getFirstName(),
        user.getLastName(),
        user.getPassword(),
        user.getEmail(),
        mapTOGrantedAuthorities(roles),
        true);
  }

  public static List<GrantedAuthority> mapTOGrantedAuthorities(List<Role> userRole) {
    return userRole.stream()
        .map(role ->
            new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
  }
}