package com.verlab.alexandria.security.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.verlab.alexandria.core.authentication.entity.Role;
import com.verlab.alexandria.core.authentication.entity.User;
import com.verlab.alexandria.core.authentication.service.UserService;

@Service
public class SecurityUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UserService userService;

    public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
    	List<GrantedAuthority> authorities = Arrays.asList("ADMIN").stream()
    			.map(role -> new SimpleGrantedAuthority(role))
    			.collect(Collectors.toList());
    	return new UserDetailsImpl(1L,"test 1", "test@gmail.com","",authorities);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userService.getUser(username);
    	if (user == null) {
    		throw new UsernameNotFoundException(username);
    	}
    	List<Role> roles = userService.getRoles(user.getId());
    	List<GrantedAuthority> authorities = roles.stream()
    			.map(role -> new SimpleGrantedAuthority(role.getName()))
    			.collect(Collectors.toList());
    	return new UserDetailsImpl(1L,user.getUsername(), "",user.getPassword(), authorities);
    }
    
}