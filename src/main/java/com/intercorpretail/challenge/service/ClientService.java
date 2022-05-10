package com.intercorpretail.challenge.service;

import java.util.List;

import com.intercorpretail.challenge.model.Client;

public interface ClientService {

	int calculateAgeAverage(Client client);
	
	int calcularStandardDeviation(List<Client> clients);
	
}
