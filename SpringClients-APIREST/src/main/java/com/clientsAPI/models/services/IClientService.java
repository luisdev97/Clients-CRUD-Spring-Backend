package com.clientsAPI.models.services;

import java.util.List;
import com.clientsAPI.models.entity.Client;

public interface IClientService {

	public List<Client> findAll();
	
}
