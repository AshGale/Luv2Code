package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//add a referance to out security data source
	
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//use jdbc from properties file
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
/*		// add our users for in memmory authentication
		UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication().withUser(users.username("Emma").password("abc123").roles("EMPLOYEE"))
				.withUser(users.username("Mary").password("abc123").roles("EMPLOYEE", "MANAGER"))
				.withUser(users.username("Adam").password("abc123").roles("EMPLOYEE", "ADMIN"));*/

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// configure the login routes
		http.authorizeRequests()
		.antMatchers("/").hasRole("EMPLOYEE")
		.antMatchers("/leaders/**").hasRole("MANAGER")
		.antMatchers("/systems/**").hasRole("ADMIN")
		.and().formLogin().loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser").permitAll()
				.and().logout().permitAll()
				.and().exceptionHandling().accessDeniedPage("/access-denied");

	}

}
