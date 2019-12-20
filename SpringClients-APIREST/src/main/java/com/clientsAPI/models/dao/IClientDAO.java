package com.clientsAPI.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.clientsAPI.models.entity.Client;


public interface IClientDAO extends CrudRepository<Client, Long>{

}
