package com.clientsAPI.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bill_items")
public class ItemBill implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer cantity;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"}) 
	@ManyToOne(fetch= FetchType.LAZY)
	private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantity() {
		return cantity;
	}

	public void setCantity(Integer cantity) {
		this.cantity = cantity;
	}
	
	public Double getAmount() {
		return cantity.doubleValue() * product.getPrice();
	}

	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	private static final long serialVersionUID = 1L;

}
