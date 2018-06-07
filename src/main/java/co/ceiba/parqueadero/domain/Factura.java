package co.ceiba.parqueadero.domain;

import java.util.Calendar;

public class Factura {
	
	private int id;
	private Vehiculo vehiculo;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	private int precioTotalParqueo;
	
	public Factura() {
		super();
		this.id = 0;
		this.fechaSalida = null;
		this.precioTotalParqueo = 0;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Calendar getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getPrecioTotalParqueo() {
		return precioTotalParqueo;
	}

	public void setPrecioTotalParqueo(int precioTotalParqueo) {
		this.precioTotalParqueo = precioTotalParqueo;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
		
	}

}
