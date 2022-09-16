package com.gestion.empleados.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre", length = 100, nullable = false)
	private String Nombre;

	@Column(name = "email", length = 80, nullable = false, unique = true)
	private String Email;

	@Column(name = "gender", length = 15, nullable = false)
	private String Gender;
	
	@Column(name = "estatus", length = 10, nullable = false)
	private String Estatus;	

	public Empleado() {

	}

	public Empleado(Long id, String nombre, String email, String gender, String estatus) {
		this.id = id;
		Nombre = nombre;
		Email = email;
		Gender = gender;
		Estatus = estatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getEstatus() {
		return Estatus;
	}

	public void setEstatus(String estatus) {
		Estatus = estatus;
	}

}
