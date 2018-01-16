package com.project.hikes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.hikes.service.MyCustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class FamousHikesSecurityConfigDAO extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService myCustomUserDetailsService;
	
	
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		//auth.userDetailsService(userDetailsService);
		
	}*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		/*auth.userDetailsService(myCustomUserDetailsService)
		.passwordEncoder(new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence charSequence, String s) {
				return true;
			}
			
			@Override
			public String encode(CharSequence charSequence) {
				return charSequence.toString();
			}
		});*/
		
	}

	//@Bean
	private DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(myCustomUserDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder(11);
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
		//.antMatchers("/hike/showFormForAdd").hasRole("ADMIN")
		.antMatchers("/hike/showFormForAdd").hasAuthority("ADMIN")
		
		.antMatchers("/hike/editHikeTrail").authenticated()
		.antMatchers("/hike/editHikeTrail").hasAuthority("ADMIN")
		
		.antMatchers("/hike/deleteHikeTrail").authenticated()
		.antMatchers("/hike/deleteHikeTrail").hasAuthority("ADMIN")
		
		.antMatchers("/account/registerTheUser").permitAll()
		
		.antMatchers("/hike/showReviewForm/*").authenticated()
		.antMatchers("/hike/saveHikeTrailReview/*").authenticated()
		.antMatchers("/hike/deleteHikeReview/*").authenticated()
		
		//.antMatchers(HttpMethod.POST).access("hasRole('ADMIN')")
		.antMatchers(HttpMethod.POST).hasAuthority("ADMIN")
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
