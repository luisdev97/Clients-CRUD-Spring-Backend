package com.clientsAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clientsAPI.models.entity.Bill;
import com.clientsAPI.models.services.IClientService;

@CrossOrigin(origins = { "http://localhost:4200" }) @RequestMapping("/api")
@RestController
public class BillRestController {

	@Autowired IClientService clientService;
	
	
	@GetMapping("/bills/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Bill show(@PathVariable Long id) {
		return clientService.findBillById(id);
	}
	
}
