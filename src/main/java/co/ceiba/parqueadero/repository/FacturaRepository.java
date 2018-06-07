package co.ceiba.parqueadero.repository;

import java.util.List;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.entity.FacturaEntity;

public interface FacturaRepository {
	
	boolean guardarFactura(FacturaEntity factura);
	
	Factura obtenerFactura(String placa);
	
	boolean actualizar(Factura factura);
	
	public List<Factura> obtenerVehiculosParqueados();
}
