package com.clientsAPI.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.clientsAPI.models.entity.Product;

public interface IProductDAO extends CrudRepository<Product, Long>{
	
	@Query("select p from Product p where p.name like ?1%")
	public List<Product> findProductByName(String name);
	
	//Metodo ya creado de hibernate
	public List<Product> findByNameStartingWithIgnoreCase(String name);
}
