package co.ceiba.parqueadero.repository;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.entity.FacturaEntity;

public interface IFacturaRepository {
	
	void guardarFactura(FacturaEntity factura);
	Factura obtenerFactura(String placa);
	void actualizar(Factura factura);
}
