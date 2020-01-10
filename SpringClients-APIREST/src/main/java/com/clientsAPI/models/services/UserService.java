package com.clientsAPI.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientsAPI.models.dao.IUserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService implements UserDetailsService, IUserService { 
	
	private Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private IUserDAO userDAO;
 
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//No podemos tener dos clases User así que hacemos referencia al paquete
		com.clientsAPI.models.entity.User user = userDAO.findByUserName(username);
		
		if(user == null) {
			log.error("Login error: the user does not exist");
			throw new UsernameNotFoundException("Login error: the user does not exist");
	}
		
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRolename()))
				.peek(authority -> log.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
				
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities );
	}

	@Transactional(readOnly = true)
	@Override
	public com.clientsAPI.models.entity.User findByUserName(String username) {
		return userDAO.findByUserName(username);
	}

}
