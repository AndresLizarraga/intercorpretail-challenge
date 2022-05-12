package com.intercorpretail.challenge.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.google.gson.JsonObject;
import com.intercorpretail.challenge.model.Cliente;
import com.intercorpretail.challenge.repository.DaoCliente;
import com.intercorpretail.challenge.service.ClienteService;

@Controller
public class ChallengeController {
	
	@Autowired
	private DaoCliente daoCliente;
	
	@Autowired
	private ClienteService clienteService;

	/**
	* @author AndresLizarraga
	* POST API que crea un nuevo objeto 'Cliente' en la base de datos.
	* @param cliente representa un objeto de tipo 'Cliente' en formato json pasado como parametro del body en la solicitud.
	* @return el nuevo objeto 'cliente' insertado en la base de datos en formato json. **/
	@PostMapping(path="/creaCliente")
	private ResponseEntity<Object> crearCliente(@RequestBody Cliente cliente) {
		JsonObject obj = new JsonObject();
		try {
			if (cliente != null) {
		cliente.calcularFechaProbableMuerte();
		daoCliente.save(cliente);
		obj.addProperty("Status: ", 0);
		obj.addProperty("Mensaje: ", "Se creó un nuevo objeto cliente correctamente.");
			} else {
				throw new ConstraintViolationException("Ocurrió un error validando los datos. Por favor revisar el body de la solicitud",null);
			}
			} catch (TransactionSystemException e) {
				e.printStackTrace();
				obj.addProperty("Status: ", 1);
				obj.addProperty("Mensaje : ", "Ocurrio un error al crear un nuevo objeto cliente. Por favor revisar los datos ingresados en el body de la solicitud.");
				obj.addProperty("Error: ", e.toString());
				return ResponseEntity.badRequest().body(obj.toString());
			}
		      catch (NullPointerException e) {
					e.printStackTrace();
					obj.addProperty("Status: ", 1);
					obj.addProperty("Mensaje : ", "Ocurrio un error al crear un nuevo objeto cliente. Alguno de los campos del body tiene un dato null.");
					obj.addProperty("Error: ", e.toString());
					return ResponseEntity.badRequest().body(obj.toString());
		      } 
			  catch (Exception e) {
					e.printStackTrace();
					obj.addProperty("Status: ", 1);
					obj.addProperty("Mensaje : ", "Ocurrio un error al crear un nuevo objeto cliente.");
					obj.addProperty("Error: ", e.toString());
					return ResponseEntity.badRequest().body(obj.toString());
			  }
		return ResponseEntity.ok().body(obj.toString());
	}
	
	/**
	* @author AndresLizarraga
	* GET API que retorna una lista con todos los objetos de tipo 'Cliente' de la base de datos 
	* y calcula la edad promedio y la desviacion estandar de cada uno.   
	* @return un objeto json con las propiedades 'promedioEdadClientes' y 'desviacionEstandarEdades' que muestran la edad promedio de los clientes y la desviacion
	* estandar entre las edades de todos los clientes. **/
	@GetMapping(path="/kpideclientes")
	private ResponseEntity<Object> calcularPromediosClientes() {
		JsonObject obj = new JsonObject();
		try {
		List<Cliente> clientes = (List<Cliente>) daoCliente.findAll();
		
		if (clientes != null && !clientes.isEmpty()) {
		double edadPromedio = clienteService.calcularEdadPromedio(clientes);
		double desviacionEstandarEdades = clienteService.calcularDesviacionEstandarEdades(clientes);
    	obj.addProperty("Status: ", 0);
    	obj.addProperty("Mensaje: ","Se realizó correctamente el cálculo del promedio de edades y desviación estandar. ");
    	obj.addProperty("promedioEdadClientes: ", edadPromedio);
    	obj.addProperty("desviacionEstandarEdades: ", desviacionEstandarEdades); 
		} else {
			throw new EntityNotFoundException();
			}
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			obj.addProperty("Status: ", 1);
			obj.addProperty("Mensaje : ", "Ocurrio un error al consultar la base de datos. No se encontraron registros.");
			obj.addProperty("Error: ", e.toString());
			return ResponseEntity.badRequest().body(obj.toString());
		} catch (ArithmeticException e) {
			e.printStackTrace();
			obj.addProperty("Status: ", 1);
			obj.addProperty("Mensaje : ", "Ocurrio un error al realizar los calculos de la desviacion estandar.");
			obj.addProperty("Error: ", e.toString());
			return ResponseEntity.badRequest().body(obj.toString());
		}
		return ResponseEntity.ok().body(obj.toString());
	}
	
	/**s
	* @author AndresLizarraga
	* GET API que retorna una lista con todos los objetos de tipo 'Cliente' de la base de datos 
	* y la edad probable de muerte de cada uno.   
	* @return una lista con todos los clientes de la base de datos y la fecha probable de muerte de cada uno. **/
	@GetMapping(path="/listclientes")
	private ResponseEntity<Object> listarClientes() {
		JsonObject obj = new JsonObject();
		try {
		List<Cliente> clientes = (List<Cliente>) daoCliente.findAll();
		  if (clientes != null && !clientes.isEmpty()) {
				return ResponseEntity.ok().body(clientes.toString());
		  } else {
			  throw new EntityNotFoundException();
		  }
		} catch (EntityNotFoundException e) { 
			e.printStackTrace();
			obj.addProperty("Status: ", 1);
			obj.addProperty("Mensaje : ", "Ocurrio un error al consultar la base de datos. No se encontraron registros.");
			obj.addProperty("Error: ", e.toString());
			return ResponseEntity.badRequest().body(obj.toString());
		}
	}
}
