package com.clientsAPI.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

	@Id
	@GeneratedValue()
	private Long id;

	@Column(unique = true, length = 20)
	private String roleName;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRolename() {
		return roleName;
	}

	public void setRolename(String rolename) {
		this.roleName = rolename;
	}

	private static final long serialVersionUID = 1L;

}