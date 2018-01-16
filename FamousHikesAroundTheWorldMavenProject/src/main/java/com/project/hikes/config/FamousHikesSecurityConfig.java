package com.project.hikes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.RequestMethod;

//@Configuration
//@EnableWebSecurity
public class FamousHikesSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private FamousHikesCustomAuthenticationProvider authProvider;
	
	//set up users/roles
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//add users for in memory authentication
		/*auth.inMemoryAuthentication().
		withUser("John").password("test123").roles("USER");

		auth.inMemoryAuthentication().
		withUser("m").password("t").roles("MANAGER");

		auth.inMemoryAuthentication().
		withUser("Susan").password("test123").roles("ADMIN");*/
		
		//String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		//System.out.println("config file: Username = "+currentUserName);
		
		
		//custom authentication provider
		auth.authenticationProvider(authProvider);
		
	}

	//redirect to custom login page
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
	
	
	
	/*@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/account/showLoginPage");
	}*/

	
	/*@Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager 
          = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
          .username("jack")
          .password("password")
          .authorities("USER")
          .build());
        return manager;
    }*/
	
}
