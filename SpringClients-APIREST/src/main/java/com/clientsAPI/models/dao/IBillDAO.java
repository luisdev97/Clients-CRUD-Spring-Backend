package com.clientsAPI.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.clientsAPI.models.entity.Bill;

public interface IBillDAO extends CrudRepository<Bill, Long>{

	
}
