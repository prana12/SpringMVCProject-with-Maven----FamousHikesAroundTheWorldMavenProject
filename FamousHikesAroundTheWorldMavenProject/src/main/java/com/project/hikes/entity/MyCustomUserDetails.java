package com.project.hikes.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//@Component
public class MyCustomUserDetails extends HikeUser implements UserDetails {

	public MyCustomUserDetails(HikeUser user) {
		super(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//add user roles from User record(super class User)
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		
		for(Role role:this.getRoles()){
			//GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            //authorities.add(grantedAuthority);
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		//System.out.println("In custom get Authorities, user authorities are " + authorities.toString());
		return authorities;
	}

	@Override
	public String getUsername() {
		return super.getFirstName()+ " " + super.getLastName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
