package com.clientsAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
}
