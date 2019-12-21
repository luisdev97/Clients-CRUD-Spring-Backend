package com.clientsAPI.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientsAPI.models.dao.IClientDAO;
import com.clientsAPI.models.entity.Client;

@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
	private IClientDAO clientDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) clientDAO.findAll();
	}

}
