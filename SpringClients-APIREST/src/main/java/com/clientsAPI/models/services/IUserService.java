package com.clientsAPI.models.services;

import com.clientsAPI.models.entity.User;

public interface IUserService {
	
	public User findByUserName(String username);
	
}
