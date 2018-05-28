package co.ceiba.parqueadero.converter;

import co.ceiba.parqueadero.domain.Carro;
import co.ceiba.parqueadero.domain.Moto;
import co.ceiba.parqueadero.domain.TipoVehiculo;
import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.entity.VehiculoEntity;
import co.ceiba.parqueadero.exceptions.ParqueoException;

public class VehiculoConverter {
	
	private static final String TIPO_VEHICULO_INVALIDO = "El tipo de vehiculo especificado no es válido";
	
	public Vehiculo toDomain(VehiculoEntity vehiculoEntity) throws ParqueoException {
		
		Vehiculo vehiculo;
		if (vehiculoEntity.getTipoVehiculo() == TipoVehiculo.MOTO) {
			vehiculo = new Moto(vehiculoEntity.getPlaca(), vehiculoEntity.getCilidraje());
		}
		else if (vehiculoEntity.getTipoVehiculo() == TipoVehiculo.CARRO) {
			vehiculo = new Carro(vehiculoEntity.getPlaca());
		}
		else 
			throw new ParqueoException(TIPO_VEHICULO_INVALIDO);
		vehiculo.setEstadoParqueo(vehiculoEntity.isParqueado());
		return vehiculo;
	}
	
	public VehiculoEntity toEntity(Vehiculo vehiculo) {
		
		VehiculoEntity entity = new VehiculoEntity();
		entity.setPlaca(vehiculo.getPlaca());
		entity.setTipoVehiculo(vehiculo.getTipo());
		entity.setCilidraje(vehiculo.getCilindraje());
		entity.setParqueado(vehiculo.isParqueado());
		return entity;		
	}
}
