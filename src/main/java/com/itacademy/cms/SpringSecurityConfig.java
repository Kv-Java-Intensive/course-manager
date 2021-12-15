package com.itacademy.cms;


import com.itacademy.cms.security.jwt.JwtConfigurer;
import com.itacademy.cms.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
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

  // Create 2 users for demo
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//    auth.inMemoryAuthentication()
//        .withUser("user").password("{noop}password").roles("USER")
//        .and()
//        .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
//
//  }

  // Secure the endpoins with HTTP Basic authentication
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        //HTTP Basic authentication
        .httpBasic().disable()
//        .and()
//        .authorizeRequests()
//        .antMatchers(HttpMethod.GET, "/courses/**").hasRole("USER")
//        .antMatchers(HttpMethod.POST, "/courses").hasRole("USER")
//        .antMatchers(HttpMethod.GET, "/users/**").hasRole("USER")
//        //  .antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
//        // .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
//        .and()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(LOGIN_ENDPOINT).permitAll()
        .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .apply(new JwtConfigurer(jwtTokenProvider));
    // .formLogin().disable();
  }
}