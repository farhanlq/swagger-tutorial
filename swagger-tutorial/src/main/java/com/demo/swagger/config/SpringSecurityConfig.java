package com.demo.swagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;





/**
 * @author farhan.laeeq
 * Implementing Basic Security on REST API
 *
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("farhan").password("{noop}secret1").roles("USER").and().withUser("admin")
				.password("{noop}secret1").roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/api/v1/employees/**").hasRole("USER")
		.antMatchers(HttpMethod.POST,"/api/v1/employees").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/v1/employees/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/v1/employees/**").hasRole("ADMIN")
		.and()
		.csrf().disable();
	}

}
