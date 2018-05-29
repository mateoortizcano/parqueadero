package co.ceiba.parqueadero.testdatabuilder;

import java.util.Calendar;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.Vehiculo;

public class FacturatestDataBuilder {
	private Vehiculo vehiculo;
	private Calendar fechaIngreso;
	public FacturatestDataBuilder() {
		super();
		this.vehiculo = new Vehiculo();
		this.fechaIngreso = Calendar.getInstance();
	}
	
	public FacturatestDataBuilder withVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}
	
	public FacturatestDataBuilder withFechaIngreso(Calendar fecha) {
		this.fechaIngreso = fecha;
		return this;
	}
	
	public Factura build() {
		return new Factura(vehiculo, fechaIngreso);
	}

}
