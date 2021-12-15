package com.itacademy.cms;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  // Create 2 users for demo
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.inMemoryAuthentication()
        .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
        .and()
        .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
  }

  // Secure the endpoins with HTTP Basic authentication
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        //HTTP Basic authentication
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/courses/**").hasRole("USER")
        .antMatchers(HttpMethod.POST, "/courses").hasRole("USER")
        .antMatchers(HttpMethod.GET, "/users/**").hasRole("USER")
        //  .antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
        // .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
        .and()
        .csrf().disable()
        .formLogin().disable();
  }

  @Bean
  protected PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }
}