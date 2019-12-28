package com.clientsAPI.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	
	@GetMapping("/clients/page/{page}")
	public Page<Client> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 2); 
		return clientService.findAll(pageable);
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
	public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result) {
		Client newClient = null;
		Map<String, Object> response = new HashMap<>();  
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
								  .stream()
								  .map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage())
								  .collect(Collectors.toList());
						
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
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
	public ResponseEntity<?> update(@Valid @RequestBody Client client, BindingResult result, @PathVariable Long id)  {
		
		Map <String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
								  .stream()
								  .map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage())
								  .collect(Collectors.toList());
						
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		
		try {
			
			Client currentClient = clientService.findById(id);
			Client modifiedClient = null;
			
			if(currentClient != null) {
				currentClient.setSurname(client.getSurname());
				currentClient.setName(client.getName());
				currentClient.setEmail(client.getEmail()) ;
				currentClient.setCreateAt(client.getCreateAt());
				
				modifiedClient = clientService.save(currentClient);
			} 
			
			
			response.put("message", "The client was modified successfully");
			response.put("client", modifiedClient);
 
		}catch(DataAccessException e) {
			response.put("message", "Error modifying client with ID " + id);
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
	
	@PostMapping("clients/upload")
	public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file, @RequestParam("id")Long id ){
		Map <String, Object> response = new HashMap<>();
		
		Client client = clientService.findById(id);
		
		if(!file.isEmpty()) {
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ","");
			Path pathFile = Paths.get("uploads").resolve(fileName).toAbsolutePath();
					
			try {
				//Si todo sale bien copy mueve el archivo subido al servidor a la ruta elegida
				Files.copy(file.getInputStream(), pathFile);
			} catch (IOException e) {
				response.put("message", "Error uploading the image " + fileName);
				response.put("error", e.getMessage() + ": " + e.getCause().getMessage());
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			client.setImg(fileName);
			clientService.save(client);
			response.put("client", client);
			response.put("message", "Image uploaded successfully -> " + fileName);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
}
 