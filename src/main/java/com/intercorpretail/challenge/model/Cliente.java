package com.intercorpretail.challenge.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="cliente")
public class Cliente {

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String nombre;
	@NotBlank
	private String apellido;
	@NotNull
	private Integer edad;
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaNacimiento;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaProbableMuerte;
	
	/**Esperanza de vida en Peru al 2021 en años 
	 * **/
	private static final int esperanzaVidaPeru = 76;
	
	public Cliente() {
	}

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

	public static int getEsperanzavidaperu() {
		return esperanzaVidaPeru;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public void calcularFechaProbableMuerte() throws NullPointerException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.fechaNacimiento);
		calendar.add(Calendar.YEAR, esperanzaVidaPeru);
		this.fechaProbableMuerte = calendar.getTime();		
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", fechaNacimiento="
				+ new SimpleDateFormat("yyyy-MM-dd").format(this.fechaNacimiento) + ", fechaProbableMuerte=" 
				+ new SimpleDateFormat("yyyy-MM-dd").format(this.fechaProbableMuerte) + "]";
	}
	
}
