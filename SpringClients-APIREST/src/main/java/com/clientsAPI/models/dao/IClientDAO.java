package com.clientsAPI.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.clientsAPI.models.entity.Client;


//Interface que extiende de CrudRepository, recibe un geńerico y el tipo de dato de la clave ID
//Contaremos con los métodos para el CRUD de forma prácticamente automática
public interface IClientDAO extends CrudRepository<Client, Long>{

}
 