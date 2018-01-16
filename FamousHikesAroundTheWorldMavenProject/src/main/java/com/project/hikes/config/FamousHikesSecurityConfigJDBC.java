package com.project.hikes.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class FamousHikesSecurityConfigJDBC extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password, enabled from users where username=?")
				.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
			authorities() needs complete role name like “ROLE_USER”
			roles() needs role name like “USER”. 
			It will automatically adds “ROLE_” value to this “USER” role name
		*/
		http.authorizeRequests()
		.antMatchers("/hike/showFormForAdd").authenticated()
		.antMatchers("/hike/showFormForAdd").hasRole("ADMIN")
		
		.antMatchers("/hike/editHikeTrail").authenticated()
		.antMatchers("/hike/editHikeTrail").hasRole("ADMIN")
		
		.antMatchers("/hike/deleteHikeTrail").authenticated()
		.antMatchers("/hike/deleteHikeTrail").hasRole("ADMIN")
		
		.antMatchers(HttpMethod.POST).access("hasRole('ADMIN')")
		.anyRequest().permitAll()
		
		//.antMatchers("/resources/**").permitAll()
		//.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/account/showLoginPage")
		.loginProcessingUrl("/authenticateTheUser")
		//.usernameParameter("username")
		//.passwordParameter("password")
		.and()
		
		.logout().permitAll();
		//.logoutSuccessUrl("/account/a");
		/*.and()
		.httpBasic();*/
	}
	
}
