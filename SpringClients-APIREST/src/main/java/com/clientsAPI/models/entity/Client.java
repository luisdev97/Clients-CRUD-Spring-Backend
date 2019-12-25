package com.clientsAPI.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
	

 //Cuando la clase se llama igual que la tabla de la base de datos no es necesaria la notacion @Table
 @Entity
 @Table(name="clients")
 public class Client implements Serializable{
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//Cuando el nombre del atributo se llama igual al nombre del campo se puede omitir el @Column()
	
	
	@Column(nullable = false)
	@NotEmpty(message = "it can't be empty")
	@Size(min = 4 , max = 20)
	private String name;
	
	@NotEmpty(message = "it can't be empty")
	@Column(nullable = false)
	private String surname;
	
	@NotEmpty(message = "it can't be empty")
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
