package co.ceiba.parqueadero.converter;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.entity.FacturaEntity;

public class FacturaConverter {
	
	public FacturaEntity toEntity(Factura factura) {
		
		FacturaEntity facturaEntity = new FacturaEntity();
		VehiculoConverter vehiculoConverter = new VehiculoConverter();
		facturaEntity.setVehiculoEntity(vehiculoConverter.toEntity(factura.getVehiculo()));
		facturaEntity.setId(factura.getId());
		facturaEntity.setFechaIngreso(factura.getFechaIngreso());
		facturaEntity.setFechaSalida(factura.getFechaSalida());
		facturaEntity.setPrecioTotalParqueo(factura.getPrecioTotalParqueo());
		return facturaEntity;
	}

	public Factura toDomain(FacturaEntity facturaEntity) {
		
		VehiculoConverter vehiculoConverter = new VehiculoConverter();
		Factura factura = new Factura();
		factura.setVehiculo(vehiculoConverter.toDomain(facturaEntity.getVehiculoEntity()));
		factura.setFechaIngreso(facturaEntity.getFechaIngreso());
		factura.setId(facturaEntity.getId());
		factura.setFechaSalida(facturaEntity.getFechaSalida());
		factura.setPrecioTotalParqueo(facturaEntity.getPrecioTotalParqueo());
		return factura;
	}
}
