package co.ceiba.parqueadero.service;

import java.util.Calendar;

import co.ceiba.parqueadero.domain.Vehiculo;

public class ValidacionIngresoVehiculo implements Validacion {
	
	private static final int DIA_LUNES = 2;
	private static final int DIA_DOMINGO = 1;
		
	private Vehiculo vehiculo;
	Calendar fechaParqueo;
	
	public ValidacionIngresoVehiculo(Vehiculo vehiculo, Calendar fecha) {
		super();
		this.vehiculo = vehiculo;
		this.fechaParqueo = fecha;
	}
	
	public boolean validar() {
		
		boolean esPosibleParquear;
		String primeraLetraPlaca = vehiculo.getPlaca().toUpperCase().substring(0, 1);
		int diaSemana = fechaParqueo.get(Calendar.DAY_OF_WEEK);
		
		if(primeraLetraPlaca.equals("A")) {
			if(diaSemana == DIA_LUNES || diaSemana == DIA_DOMINGO) 
				esPosibleParquear = true;
			else
				esPosibleParquear = false;
		}
		else 
			esPosibleParquear = true;
		return esPosibleParquear;
	}
}
