package com.intercorpretail.challenge.model;

public class CalculationApiResponse {

	private String status;
	private String mensaje;
	private String error;
	private String promedioClientes;
	private String desviacionClientes;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getPromedioClientes() {
		return promedioClientes;
	}
	public void setPromedioClientes(String promedioClientes) {
		this.promedioClientes = promedioClientes;
	}
	public String getDesviacionClientes() {
		return desviacionClientes;
	}
	public void setDesviacionClientes(String desviacionClientes) {
		this.desviacionClientes = desviacionClientes;
	}
	
}
