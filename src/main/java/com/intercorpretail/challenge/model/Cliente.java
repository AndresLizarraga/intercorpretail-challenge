package com.intercorpretail.challenge.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="cliente")
public class Cliente {

	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String apellido;
	private int edad;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaNacimiento;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaProbableMuerte;
	
	private Cliente() {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaProbableMuerte() {
		return fechaProbableMuerte;
	}

	public void setFechaProbableMuerte(Date fechaProbableMuerte) {
		this.fechaProbableMuerte = fechaProbableMuerte;
	}
	
}
