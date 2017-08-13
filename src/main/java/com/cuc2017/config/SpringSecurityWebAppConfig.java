package com.cuc2017.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.servlet.http.Saver;

@Configuration
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {

	@Value("#{ @environment['stormpath.web.login.nextUri'] ?: '/' }")
	protected String loginNextUri;

	@Value("#{ @environment['stormpath.web.produces'] ?: 'application/json, text/html' }")
	protected String produces;

	@Autowired
	@Qualifier("stormpathAuthenticationResultSaver")
	protected Saver<AuthenticationResult> authenticationResultSaver;

	@Autowired
	protected Client client;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").permitAll();
		// http.authorizeRequests().antMatchers("/score", "/scorekeeper",
		// "/scoreKeeper").fullyAuthenticated()
		// .antMatchers("/**", "/currentScores/**",
		// "google849f54b5f1b34ed4.html").permitAll();
	}
}