package com.intercorpretail.challenge.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import com.intercorpretail.challenge.model.ApiResponse;
import com.intercorpretail.challenge.model.CalculationApiResponse;
import com.intercorpretail.challenge.model.Cliente;
import com.intercorpretail.challenge.model.ClientesApiResponse;
import com.intercorpretail.challenge.repository.DaoCliente;
import com.intercorpretail.challenge.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private DaoCliente daoCliente;
	
	@Override
	public ApiResponse crearCliente(Cliente cliente) {
		ApiResponse response = new ApiResponse();
		try {
			if (cliente != null) {
		cliente.calcularFechaProbableMuerte();
		daoCliente.save(cliente);
		response.setStatus("OK");
		response.setMensaje("Se creó un nuevo objeto cliente correctamente.");
		response.setError("0");
			}		
			} catch (TransactionSystemException e) {
				e.printStackTrace();
				response.setStatus("ERROR");
				response.setMensaje("Ocurrio un error al crear un nuevo objeto cliente. Por favor revisar los datos ingresados en el body de la solicitud");
				response.setError("1");
				return response;
			}
		      catch (NullPointerException e) {
					e.printStackTrace();
					response.setStatus("ERROR");
					response.setMensaje("Ocurrio un error al crear un nuevo objeto cliente. Alguno de los campos del body tiene un dato null.");
					response.setError("1");
					return response;
		      } 
			  catch (Exception e) {
					e.printStackTrace();
					response.setStatus("ERROR");
					response.setMensaje("Ocurrio un error al crear un nuevo objeto cliente.");
					response.setError("1");
					return response;
			  }
		return response;
	}
	
	@Override
	public CalculationApiResponse calcularPromediosClientes() {
		CalculationApiResponse response = new CalculationApiResponse();
		try {
		List<Cliente> clientes = (List<Cliente>) daoCliente.findAll();
		if (clientes != null && !clientes.isEmpty()) {
		double edadPromedio = calcularEdadPromedio(clientes);
		double desviacionEstandarEdades = calcularDesviacionEstandarEdades(clientes);
		response.setStatus("OK");
		response.setMensaje("Se realizaron los calculos correctamente.");
		response.setError("0");
		response.setPromedioClientes(String.valueOf(edadPromedio));
		response.setDesviacionClientes(String.valueOf(desviacionEstandarEdades));
		
		} else {
			throw new EntityNotFoundException();
			}
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			response.setStatus("ERROR");
			response.setMensaje("Ocurrio un error al consultar la base de datos. No se encontraron registros.");
			response.setError("1");
			return response;
		} catch (ArithmeticException e) {
			e.printStackTrace();
			response.setStatus("ERROR");
			response.setMensaje("Ocurrio un error al realizar los calculos de la desviacion estandar.");
			response.setError("1");
			return response;
		}
		return response;
	}

	@Override
	public List<ClientesApiResponse> listarClientes() {
	List<ClientesApiResponse> clientesResponseList = new ArrayList<ClientesApiResponse>();
	ClientesApiResponse clientesResponse = new ClientesApiResponse();
	List<Cliente> clientes = (List<Cliente>) daoCliente.findAll();
		if (clientes != null && !clientes.isEmpty()) {
			for (Cliente cliente : clientes) {
				clientesResponse = mapearRespuestaClientes(cliente);
				clientesResponseList.add(clientesResponse);
				}
			return clientesResponseList;
			} else {
			throw new EntityNotFoundException();
			}
	}
	
	private ClientesApiResponse mapearRespuestaClientes(Cliente cliente) {
	ClientesApiResponse response = new ClientesApiResponse();
	response.setNombre(cliente.getNombre());
	response.setApellido(cliente.getApellido());
	response.setEdad(String.valueOf(cliente.getEdad()));
	response.setFechaNacimiento(formatearFecha(cliente.getFechaNacimiento()));
	response.setFechaProbableMuerte(formatearFecha(cliente.getFechaProbableMuerte()));
		return response;
	}
	
	private String formatearFecha(Date date) {
		String fecha = null;
		String formato = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		fecha = simpleDateFormat.format(date);
		return fecha;
	}
	
	private float calcularEdadPromedio(List<Cliente> clientes) throws ArithmeticException{
	int numeroClientes = clientes.size();
	int sumaEdades = 0;
	float promedioEdades = 0;
		try {
			if (clientes != null && clientes.size() > 0) { 
		sumaEdades = sumarEdadesClientes(clientes);
		promedioEdades = sumaEdades/numeroClientes;
			} 
		} 
			catch (ArithmeticException e) {
				e.printStackTrace();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		return promedioEdades;
	}

	private double calcularDesviacionEstandarEdades(List<Cliente> clientes) throws ArithmeticException{
		int numeroClientes = clientes.size();
		float restoDesviacion = 0;
		float cuadradoDesviacion = 0;
		float sumaDesviaciones = 0;
		float promedioEdades = 0;
		double desviacionEstandar = 0;
			try {	
			if (clientes != null && !clientes.isEmpty()) {
			promedioEdades = calcularEdadPromedio(clientes);
			for (Cliente cliente : clientes) {
				restoDesviacion = cliente.getEdad() - promedioEdades;
				cuadradoDesviacion = restoDesviacion * restoDesviacion;
				sumaDesviaciones = cuadradoDesviacion + sumaDesviaciones; 
				}
			desviacionEstandar = sumaDesviaciones / numeroClientes;
			desviacionEstandar = Math.sqrt(desviacionEstandar);
			}  
			}
			catch (ArithmeticException e) {
				e.printStackTrace();
			}
		return desviacionEstandar;
	}

	private int sumarEdadesClientes(List<Cliente> clientes) {
		int sumaEdades = 0;
		for (Cliente cliente : clientes) {
			sumaEdades = sumaEdades + cliente.getEdad();
			}
		return sumaEdades;
	}

}
