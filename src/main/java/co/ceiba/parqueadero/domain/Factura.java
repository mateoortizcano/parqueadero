package co.ceiba.parqueadero.domain;

import java.util.Calendar;

public class Factura {
	
	private Vehiculo vehiculo;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	
	public Factura(Vehiculo vehiculo, Calendar fecha) {
		super();
		this.vehiculo = vehiculo;
		this.fechaIngreso = fecha;
		this.fechaSalida = null;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
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
