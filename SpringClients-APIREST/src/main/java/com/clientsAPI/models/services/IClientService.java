package com.clientsAPI.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clientsAPI.models.entity.Bill;
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
	
	public Bill findBillById(Long id);
	
	public Bill saveBill(Bill bill);
	
	public void deleteBillById(Long id);
	
	//Implementamos las operaciones CRUD del DAO de facturas ya que un service es un DAO manager, puede tener varios atributos de distintos DAO si están relacionados
	
}
