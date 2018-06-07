package co.ceiba.parqueadero.converter;

import co.ceiba.parqueadero.domain.Carro;
import co.ceiba.parqueadero.domain.Moto;
import co.ceiba.parqueadero.domain.TipoVehiculo;
import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.entity.VehiculoEntity;

public class VehiculoConverter {
		
	public Vehiculo toDomain(VehiculoEntity vehiculoEntity) {
		
		Vehiculo vehiculo = new Vehiculo();
		if (vehiculoEntity.getTipoVehiculo() == TipoVehiculo.MOTO) 
			vehiculo = new Moto(vehiculoEntity.getPlaca(), vehiculoEntity.getCilidraje());
		else if (vehiculoEntity.getTipoVehiculo() == TipoVehiculo.CARRO)
			vehiculo = new Carro(vehiculoEntity.getPlaca());
		vehiculo.setEstadoParqueo(vehiculoEntity.isParqueado());
		vehiculo.setId(vehiculoEntity.getId());
		return vehiculo;
	}
	
	public VehiculoEntity toEntity(Vehiculo vehiculo) {
		
		VehiculoEntity entity = new VehiculoEntity();
		entity.setPlaca(vehiculo.getPlaca());
		entity.setTipoVehiculo(vehiculo.getTipo());
		entity.setCilidraje(vehiculo.getCilindraje());
		entity.setParqueado(vehiculo.isParqueado());
		entity.setId(vehiculo.getId());
		return entity;		
	}
}
