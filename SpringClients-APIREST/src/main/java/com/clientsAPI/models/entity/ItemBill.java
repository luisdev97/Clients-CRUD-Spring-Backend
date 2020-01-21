package com.clientsAPI.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bill_items")
public class ItemBill implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer cantity;

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
	
	public Double calculateAmount() {
		return cantity.doubleValue();
	}

	private static final long serialVersionUID = 1L;

}
