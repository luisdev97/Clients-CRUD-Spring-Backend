package com.clientsAPI.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientsAPI.models.dao.IBillDAO;
import com.clientsAPI.models.dao.IClientDAO;
import com.clientsAPI.models.entity.Bill;
import com.clientsAPI.models.entity.Client;
import com.clientsAPI.models.entity.Region;

@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
	private IClientDAO clientDAO;
	
	@Autowired
	private IBillDAO billDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) clientDAO.findAll();
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Page<Client> findAll(Pageable pageable) {
		return clientDAO.findAll(pageable);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Client findById(Long id) {
		//Si lo encuentra retorna el objeto client, en caso contrario retorna null
		return clientDAO.findById(id).orElse(null);
	}
	

	@Override
	@Transactional
	public Client save(Client client) {
		//Return the saved entity
		return clientDAO.save(client);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clientDAO.deleteById(id);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegions() {
		return clientDAO.findAllRegions();
	}


	@Override
	@Transactional(readOnly = true)
	public Bill findBillById(Long id) {	
		return billDAO.findById(id).orElse(null);
	}


	@Override
	@Transactional
	public Bill saveBill(Bill bill) {
		return billDAO.save(bill);
	}


	@Override
	@Transactional
	public void deleteBill(Long id) {
		billDAO.deleteById(id);
	}
	
	

}
