package com.intercorpretail.challenge.service;

import java.util.List;

import com.intercorpretail.challenge.model.Cliente;

public interface ClienteService {

	float calcularEdadPromedio(List<Cliente> clientes);
	
	double calcularDesviacionEstandarEdades(List<Cliente> clientes);
	
	int sumarEdadesClientes(List<Cliente> clientes);
	
}
