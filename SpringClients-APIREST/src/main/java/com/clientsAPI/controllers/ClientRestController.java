package com.clientsAPI.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@CrossOrigin(origins = { "http://localhost:4200/clients" })
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
	public Client show(@PathVariable Long id) {
		return clientService.findById(id);
	}
	
	
	@PostMapping("/clients")
	@ResponseStatus(HttpStatus.CREATED)
	public Client create(@RequestBody Client client) {
		return clientService.save(client);
	}
	
	@PutMapping("/clients/{id}")
	public Client update(@RequestBody Client client, @PathVariable Long id) {
		Client currentClient = clientService.findById(id);
		currentClient.setSurname(client.getSurname());
		currentClient.setName(client.getName());
		currentClient.setEmail(client.getEmail()) ;
		//Cuando existe un objeto con el id recibido el método save realizará un merge
		return clientService.save(currentClient);
		
	}
	
	
	@DeleteMapping("/clients/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clientService.delete(id);
	}
	
}
 