package com.project.hikes.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

//@Component
public class FamousHikesCustomAuthenticationProvider implements AuthenticationProvider {

	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("Name = " + username + " ,Password = " + password);
        
        // use the credentials and authenticate against the third-party system
        if(("user".equals(username) && "user".equals(password)) 
        		|| ("admin".equals(username) && "admin".equals(password))){
        		//System.out.println("Succesful authentication!");
        		
        		/*List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        	    grantedAuths.add(new SimpleGrantedAuthority("USER"));
        	    grantedAuths.add(new SimpleGrantedAuthority("ADMIN"));*/
        	    
        		return new UsernamePasswordAuthenticationToken(username, password, null);
        }
        
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
