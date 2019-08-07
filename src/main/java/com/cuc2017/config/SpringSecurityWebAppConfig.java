package com.cuc2017.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  // @formatter:off
  auth.inMemoryAuthentication()
          .withUser("scorekeeper").password("skEdmonton").roles("USER")
          .and()
          .withUser("admin").password("Edmonton20!9").roles("ADMIN");
  // @formatter:on
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // TODO: uncomment for scoretraining
    // http.authorizeRequests().antMatchers("/**").permitAll();

    // TODO: comment for scoretraining
    // @formatter:off
    http.httpBasic()
        .and().authorizeRequests()
        .antMatchers("/allGames/**").hasAnyRole("ADMIN")
        .antMatchers("/", "/scoresheet/**", "/gameScoreSheet/**", "/currentScores/**", "google849f54b5f1b34ed4.html").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
          .permitAll()
        .and()
        .logout()
          .logoutSuccessUrl("/")
          .permitAll();
     // @formatter:on
  }
}