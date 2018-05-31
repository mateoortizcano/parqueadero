package co.ceiba.parqueadero.service;

import co.ceiba.parqueadero.domain.TipoVehiculo;

public class ValidacionCantidadVehiculos implements Validacion{
	
	private static final int NRO_MAXIMO_CELDAS_CARROS = 20;
	private static final int NRO_MAXIMO_CELDAS_MOTOS  = 10;
	 
	private int tipoVehiculo;
	private int nroVehiculosParqueados;
	
	public ValidacionCantidadVehiculos(int tipoVehiculo, int nroVehiculosParqueados) {
		
		super();
		this.tipoVehiculo = tipoVehiculo;
		this.nroVehiculosParqueados = nroVehiculosParqueados;
	}

	public boolean validar() {

		boolean esPosibleParquear = true;
		int cupoMaximo;
		
		if(tipoVehiculo == TipoVehiculo.CARRO)
			cupoMaximo = NRO_MAXIMO_CELDAS_CARROS;
		else if(tipoVehiculo == TipoVehiculo.MOTO)
			cupoMaximo = NRO_MAXIMO_CELDAS_MOTOS;
		else
			cupoMaximo = 0;
		
		if(nroVehiculosParqueados >= cupoMaximo) {
			esPosibleParquear = false;
		}
		return esPosibleParquear;
	}
	
}
