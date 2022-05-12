package com.intercorpretail.challenge.service;

import java.util.List;

import com.intercorpretail.challenge.model.Cliente;

public interface ClienteService {

	float calcularEdadPromedio(List<Cliente> clientes) throws ArithmeticException;
	
	double calcularDesviacionEstandarEdades(List<Cliente> clientes) throws ArithmeticException;
	
	int sumarEdadesClientes(List<Cliente> clientes);
	
}
