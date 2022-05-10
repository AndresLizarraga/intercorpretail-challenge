package com.intercorpretail.challenge.service;

import java.util.List;

import com.intercorpretail.challenge.model.Cliente;

public interface ClienteService {

	int calculateAgeAverage(Cliente client);
	
	int calcularStandardDeviation(List<Cliente> clients);
	
}
