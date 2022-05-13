package com.intercorpretail.challenge.model;

public class ClientesApiResponse {
	
	private String nombre;
	private String apellido;
	private String edad;
	private String fechaNacimiento;
	private String fechaProbableMuerte;
	
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
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getFechaProbableMuerte() {
		return fechaProbableMuerte;
	}
	public void setFechaProbableMuerte(String fechaProbableMuerte) {
		this.fechaProbableMuerte = fechaProbableMuerte;
	}

}
