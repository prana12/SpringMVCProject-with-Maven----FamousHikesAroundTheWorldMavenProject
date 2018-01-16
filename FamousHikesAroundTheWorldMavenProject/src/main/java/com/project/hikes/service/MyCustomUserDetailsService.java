package com.project.hikes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hikes.dao.UserDao;
import com.project.hikes.entity.MyCustomUserDetails;
import com.project.hikes.entity.HikeUser;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserDao userDao;
	
	@Autowired
    private UserService userService;
	
     
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		/*User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }*/
		
		HikeUser user = userService.findUserByEmail(email);
        //System.out.println("In CustomUserDetailsService, email name is "+user.getEmail()+" and Password= "+user.getPassword());
		
        //return (UserDetails) user;
		return new MyCustomUserDetails(user);
	}
	
	
	/*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = usersRepository.findByName(username);

        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalUsers
                .map(CustomUserDetails::new).get();
    }*/
	
	/*private Set<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        System.out.println("user authorities are " + authorities.toString());
        return authorities;
    }*/

}
