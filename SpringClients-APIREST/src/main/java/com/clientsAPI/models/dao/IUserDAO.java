package com.clientsAPI.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.clientsAPI.models.entity.User;

public interface IUserDAO extends CrudRepository<User,Long>{
	
	
	public User findByUserName(String username);
	
	@Query("select u from User u where u.userName =?1")
	 public User findByUserName2(String username);
	
}
