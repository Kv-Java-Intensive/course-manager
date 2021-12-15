package com.itacademy.cms;


import com.itacademy.cms.security.jwt.JwtConfigurer;
import com.itacademy.cms.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String ADMIN_ENDPOINT = "/api/admin/**";
  private static final String LOGIN_ENDPOINT = "/api/login";
  private final JwtTokenProvider jwtTokenProvider;

  @Autowired
  public SpringSecurityConfig(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        .httpBasic().disable()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(ADMIN_ENDPOINT).hasAnyRole("ADMIN", "USER", "AUTHOR")
        .antMatchers(LOGIN_ENDPOINT, ADMIN_ENDPOINT).permitAll()
        .anyRequest().authenticated()
        .and()
        .apply(new JwtConfigurer(jwtTokenProvider));
  }
}