package com.clientsAPI.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clientsAPI.models.entity.Client;
import com.clientsAPI.models.entity.Region;




//Interface que extiende de CrudRepository, recibe un geńerico y el tipo de dato de la clave ID
//Contaremos con los métodos para el CRUD de forma prácticamente automática
public interface IClientDAO extends JpaRepository<Client, Long>{
	
	@Query("from Region")
	public List<Region> findAllRegions();
	
}
 