package com.intercorpretail.challenge.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.intercorpretail.challenge.model.ApiResponse;
import com.intercorpretail.challenge.model.CalculationApiResponse;
import com.intercorpretail.challenge.model.Cliente;
import com.intercorpretail.challenge.model.ClientesApiResponse;
import com.intercorpretail.challenge.service.ClienteService;

@RestController 
public class ChallengeController {
	
	@Autowired
	private ClienteService clienteService;

	/**
	* @author AndresLizarraga
	* POST API que crea un nuevo objeto 'Cliente' en la base de datos.
	* @param cliente representa un objeto de tipo 'Cliente' en formato json pasado como parametro del body en la solicitud.
	* @return el nuevo objeto 'cliente' insertado en la base de datos en formato json. **/
	@PostMapping(path="/creaCliente")
	private ResponseEntity<Object> crearCliente(@RequestBody Cliente cliente) {
		ApiResponse response = new ApiResponse();
			if (cliente != null) {
				response = clienteService.crearCliente(cliente);
			} 
		return ResponseEntity.ok().body(response);
	}
	
	/**
	* @author AndresLizarraga
	* GET API que retorna una lista con todos los objetos de tipo 'Cliente' de la base de datos 
	* y calcula la edad promedio y la desviacion estandar de cada uno.   
	* @return un objeto json con las propiedades 'promedioEdadClientes' y 'desviacionEstandarEdades' que muestran la edad promedio de los clientes y la desviacion
	* estandar entre las edades de todos los clientes. **/
	@GetMapping(path="/kpideclientes")
	private ResponseEntity<Object> calcularPromediosClientes() {
		CalculationApiResponse response = new CalculationApiResponse();
		response = clienteService.calcularPromediosClientes();
		 return ResponseEntity.ok().body(response);
	}
	
	/**
	* @author AndresLizarraga
	* GET API que retorna una lista con todos los objetos de tipo 'Cliente' de la base de datos 
	* y la edad probable de muerte de cada uno.   
	* @return una lista con todos los clientes de la base de datos y la fecha probable de muerte de cada uno. **/
	@GetMapping(path="/listclientes")
	private ResponseEntity<Object> listarClientes() {
		ApiResponse response = new ApiResponse();
		try {
		List<ClientesApiResponse> clientesResponse = clienteService.listarClientes();
		  if (clientesResponse != null && !clientesResponse.isEmpty()) {
				return ResponseEntity.ok().body(clientesResponse);
		  } else {
			  throw new EntityNotFoundException();
		  }
		} catch (EntityNotFoundException e) { 
			e.printStackTrace();
			response.setStatus("ERROR");
			response.setMensaje("Ocurrio un error al consultar la base de datos. No se encontraron registros.");
			response.setError("1");
			return ResponseEntity.badRequest().body(response);
		}
	}
}
