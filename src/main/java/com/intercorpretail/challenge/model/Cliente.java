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
	
	private Cliente() {}
	
	public String getName() {
		return nombre;
	}
	public void setName(String name) {
		this.nombre = name;
	}
	public String getLastName() {
		return apellido;
	}
	public void setLastName(String lastName) {
		this.apellido = lastName;
	}
	public int getAge() {
		return edad;
	}
	public void setAge(int age) {
		this.edad = age;
	}
	public Date getBirthday() {
		return fechaNacimiento;
	}
	public void setBirthday(Date birthday) {
		this.fechaNacimiento = birthday;
	}
	
}
