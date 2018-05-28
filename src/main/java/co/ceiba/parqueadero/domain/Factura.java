package co.ceiba.parqueadero.domain;

import java.util.Calendar;

public class Factura {
	
	private Vehiculo vehiculoMotor;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	
	public Factura(Vehiculo vehiculoMotor, Calendar fecha) {
		super();
		this.vehiculoMotor = vehiculoMotor;
		this.fechaIngreso = fecha;
		this.fechaSalida = null;
	}

	public Vehiculo getVehiculoMotor() {
		return vehiculoMotor;
	}

	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}

	public Calendar getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	
	
	
}
