package co.ceiba.parqueadero.testdatabuilder;

import java.util.Calendar;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.Vehiculo;

public class FacturaTestDataBuilder {
	
	private Vehiculo vehiculo;
	private Calendar fechaIngreso;
	
	public FacturaTestDataBuilder() {
		super();
		this.vehiculo = new Vehiculo();
		this.fechaIngreso = Calendar.getInstance();
	}
	
	public FacturaTestDataBuilder withVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}
	
	public FacturaTestDataBuilder withFechaIngreso(Calendar fecha) {
		this.fechaIngreso = fecha;
		return this;
	}
	
	public Factura build() {
		Factura factura = new Factura();
		factura.setVehiculo(vehiculo);
		factura.setFechaIngreso(fechaIngreso);
		return factura;
	}

}
