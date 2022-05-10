package com.intercorpretail.challenge.service;

import java.util.List;

import com.intercorpretail.challenge.model.Cliente;

public interface ClienteService {

	int calcularEdadPromedio(List<Cliente> clients);
	
	int calcularDesviacionEstandarEdades(List<Cliente> clients);
	
}
