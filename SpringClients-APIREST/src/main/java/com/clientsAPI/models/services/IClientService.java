package com.clientsAPI.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clientsAPI.models.entity.Client;
import com.clientsAPI.models.entity.Region;

//Interface para definir el contrato en el que marco que métodos usará mi servicio, CRUD Methods
public interface IClientService {

	public List<Client> findAll();
	
	//El page es similar al list pero a través de rangos
	public Page<Client> findAll(Pageable pageable);

	public Client findById(Long id);

	public Client save(Client client);
	
	public void delete(Long id);
	
	public List<Region> findAllRegions();
	
}
