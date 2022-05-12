package com.intercorpretail.challenge.service.impl;

import java.util.List;

import com.intercorpretail.challenge.model.Cliente;
import com.intercorpretail.challenge.service.ClienteService;

public class ClienteServiceImpl implements ClienteService{

	@Override
	public float  calcularEdadPromedio(List<Cliente> clientes) {
	int numeroClientes = clientes.size();
	int sumaEdades = 0;
	float promedioEdades = 0;
	
		try {	
		sumaEdades = sumarEdadesClientes(clientes);
		promedioEdades = sumaEdades/numeroClientes;
		}
			catch (ArithmeticException e) {
				e.printStackTrace();	
			}
		return promedioEdades;
	}

	@Override
	public double calcularDesviacionEstandarEdades(List<Cliente> clientes) {
		int numeroClientes = clientes.size();
		float restoDesviacion = 0;
		float cuadradoDesviacion = 0;
		float sumaDesviaciones = 0;
		float promedioEdades = 0;
		double desviacionEstandar = 0;
			try {	
			promedioEdades = calcularEdadPromedio(clientes);
			for (Cliente cliente : clientes) {
				restoDesviacion = cliente.getEdad() - promedioEdades;
				cuadradoDesviacion = restoDesviacion * restoDesviacion;
				sumaDesviaciones = cuadradoDesviacion + sumaDesviaciones; 
				}
			desviacionEstandar = sumaDesviaciones / numeroClientes;
			desviacionEstandar = Math.sqrt(desviacionEstandar);
			}
			catch (ArithmeticException e) {
				e.printStackTrace();
			}
		return desviacionEstandar;
	}

	@Override
	public int sumarEdadesClientes(List<Cliente> clientes) {
		int sumaEdades = 0;
		for (Cliente cliente : clientes) {
			sumaEdades = sumaEdades + cliente.getEdad();
			}
		return sumaEdades;
	}

}