package com.clientsAPI.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.clientsAPI.models.entity.Client;
import com.clientsAPI.models.services.IClientService;


//Para un controlador de WEB MVC usariamos solo Controller para incluir las vistas
@CrossOrigin(origins = { "http://localhost:4200/clients", "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {

	@Autowired
	//En Spring cuando se declara un beans con su tipo generico ya sea interface o clase abstracta buscará como primer candidato una clase que implemente dicha interface
	//El bean ClientServiceImpl es un tipo generico de la interface, si hubiera mas de una implementacion habia que usar un calificador en autowired
	private IClientService clientService;
		
	
	@GetMapping("/clients")
	public List<Client> index(){
		return clientService.findAll();
	}
	
	
	@GetMapping("/clients/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Client client = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			client = clientService.findById(id);
		}catch(DataAccessException e) {
			response.put("message", "Error when querying database");
			response.put("preciseMessage", e.getMessage() + ": " + e.getMostSpecificCause().getMessage() );
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(client == null) {
			response.put("message", "The client with ID " + id.toString() + " not exist");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
				
		return new ResponseEntity<Client>(client, HttpStatus.OK);  
	}
	
	
	
	@PostMapping("/clients")
	public ResponseEntity<?> create(@RequestBody Client client) {
		Client newClient = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			newClient = clientService.save(client);
		}catch(DataAccessException e) {
			response.put("message", "Error saving client to database");
			response.put("preciseMessage", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "The client was saved successfully");
		response.put("client", newClient);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	

	@PutMapping("/clients/{id}")
	public ResponseEntity<?> update(@RequestBody Client client, @PathVariable Long id) {
		
		Map <String, Object> response = new HashMap<>();
		
		try {
			
			Client currentClient = clientService.findById(id);
			Client modifiedClient = null;
			
			if(currentClient != null) {
				currentClient.setSurname(client.getSurname());
				currentClient.setName(client.getName());
				currentClient.setEmail(client.getEmail()) ;
				
				modifiedClient = clientService.save(currentClient);
			}
			
			
			response.put("message", "The client was modified successfully");
			response.put("client", modifiedClient);
 
		}catch(DataAccessException e) {
			response.put("message", "Error modifying client with ID" + id);
			response.put("preciseMessage", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		//Cuando existe un objeto con el id recibido el método save realizará un merge
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		
	}
	
	
	
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map <String, Object> response = new HashMap<>();
		
		try {
			clientService.delete(id);
		}catch(DataAccessException e) {
			response.put("message", "Error removing client with ID " + id);
			response.put("preciseMessage", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "The client was removed successfully");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
}
 