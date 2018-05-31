package co.ceiba.parqueadero.converter;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.entity.FacturaEntity;

public class FacturaConverter {
	
	public FacturaEntity toEntity(Factura factura) {
		
		FacturaEntity entity = new FacturaEntity();
		VehiculoConverter converter = new VehiculoConverter();
		entity.setVehiculoEntity(converter.toEntity(factura.getVehiculo()));
		entity.setFechaIngreso(factura.getFechaIngreso());
		entity.setFechaSalida(factura.getFechaSalida());
		entity.setPrecioTotalParqueo(factura.getPrecioTotalParqueo());
		return entity;
	}

	public Factura toDomain(FacturaEntity facturaEntity) {
		
		VehiculoConverter vehiculoConverter = new VehiculoConverter();
		Factura factura = new Factura(vehiculoConverter.toDomain(facturaEntity.getVehiculoEntity()), 
				facturaEntity.getFechaIngreso());
		factura.setFechaSalida(facturaEntity.getFechaSalida());
		factura.setPrecioTotalParqueo(facturaEntity.getPrecioTotalParqueo());
		return factura;
	}
}
