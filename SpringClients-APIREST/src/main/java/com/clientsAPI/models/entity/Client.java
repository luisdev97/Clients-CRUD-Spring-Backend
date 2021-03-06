package com.clientsAPI.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Cuando la clase se llama igual que la tabla de la base de datos no es necesaria la notacion @Table
@Entity
@Table(name = "clients")
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Cuando el nombre del atributo se llama igual al nombre del campo se puede
	// omitir el @Column()

	@Column(nullable = false)
	@NotEmpty(message = "it can't be empty")
	@Size(min = 4, max = 20)
	private String name;

	@NotEmpty(message = "it can't be empty")
	@Column(nullable = false)
	private String surname;

	@NotEmpty(message = "it can't be empty")
	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull(message = "it can't be empty")
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	private String img;

	// Con la carga perezosa, cada vez que invoquemos el atributo mediante el getter
	// se realizará la carga
	@NotNull(message = "it can't be empty")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Region region;

	@JsonIgnoreProperties({ "client", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy="client", cascade = CascadeType.ALL)
	private List<Bill> bills;
	
	public Client() {
		this.bills =  new ArrayList<>();
	}

	/*
	 * @PrePersist public void prePersist() { createAt = new Date(); }
	 */

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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	private static final long serialVersionUID = 1L;
}
