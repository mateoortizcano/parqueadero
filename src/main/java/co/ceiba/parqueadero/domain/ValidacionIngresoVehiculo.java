package co.ceiba.parqueadero.domain;

import java.util.Calendar;

public class ValidacionIngresoVehiculo implements Validacion {
	
	private static final int DIA_LUNES = 2;
	private static final int DIA_DOMINGO = 1;
		
	private Vehiculo vehiculoMotor;
	Calendar fechaIngreso;
	
	public ValidacionIngresoVehiculo(Vehiculo vehiculoMotor, Calendar fecha) {
		super();
		this.vehiculoMotor = vehiculoMotor;
		this.fechaIngreso = fecha;
	}
	
	public boolean validar() {
		
		boolean ans;
		String primeraLetra = vehiculoMotor.getPlaca().toUpperCase().substring(0, 1);
		int diaSemana = fechaIngreso.get(Calendar.DAY_OF_WEEK);
		
		if(primeraLetra.equals("A")) {
			if(diaSemana == DIA_LUNES || diaSemana == DIA_DOMINGO) 
				ans = true;
			else
				ans = false;
		}
		else 
			ans = true;
		return ans;
	}
}
