package com.cuc2017.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {

  // @Value("#{ @environment['stormpath.web.login.nextUri'] ?: '/' }")
  // protected String loginNextUri;
  //
  // @Value("#{ @environment['stormpath.web.produces'] ?: 'application/json,
  // text/html' }")
  // protected String produces;
  //
  // @Autowired
  // @Qualifier("stormpathAuthenticationResultSaver")
  // protected Saver<AuthenticationResult> authenticationResultSaver;
  //
  // @Autowired
  // protected Client client;

  @SuppressWarnings("deprecation")
  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder().username("scorekeeper").password("skEdmonton").roles("USER")
        .build();

    UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("Edmonton20!9").roles("ADMIN")
        .build();

    return new InMemoryUserDetailsManager(user, admin);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // TODO: uncomment for scoretraining
    http.authorizeRequests().antMatchers("/**").permitAll();

    // TODO: comment for scoretraining
    // http.authorizeRequests()
    // .antMatchers("/score/**", "/scorekeeper/**", "/scoreKeeper/**",
    // "/allGames/**", "/finishGame/**", "/docs/**",
    // "/doc/**")
    // .fullyAuthenticated().antMatchers("/**", "/currentScores/**",
    // "google849f54b5f1b34ed4.html").formLogin()
    // .loginPage("/login")
    // .permitAll()
    // .and()
    // .logout()
    // .permitAll();
  }
}