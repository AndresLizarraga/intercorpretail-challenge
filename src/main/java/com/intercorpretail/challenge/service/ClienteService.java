package com.intercorpretail.challenge.service;

import java.util.List;
import com.intercorpretail.challenge.model.ApiResponse;
import com.intercorpretail.challenge.model.CalculationApiResponse;
import com.intercorpretail.challenge.model.Cliente;
import com.intercorpretail.challenge.model.ClientesApiResponse;

public interface ClienteService {
	
	ApiResponse crearCliente(Cliente cliente);
	
	CalculationApiResponse calcularPromediosClientes();
	
	List<ClientesApiResponse> listarClientes();
}
