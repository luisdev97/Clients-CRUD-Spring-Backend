package com.clientsAPI.models.services;

import java.util.List;
import com.clientsAPI.models.entity.Client;

//Interface para definir el contrato en el que marco que métodos usará mi servicio, CRUD Methods
public interface IClientService {

	public List<Client> findAll();
	
	public Client findById(Long id);

	public Client save(Client client);
	
	public void delete(Long id);
	
}
