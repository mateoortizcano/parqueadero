package co.ceiba.parqueadero.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.converter.FacturaConverter;
import co.ceiba.parqueadero.converter.VehiculoConverter;
import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.entity.FacturaEntity;
import co.ceiba.parqueadero.entity.VehiculoEntity;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.jpa.IVehiculoJPA;
import co.ceiba.parqueadero.repository.IFacturaRepository;
import co.ceiba.parqueadero.repository.IVehiculoRepository;
import co.ceiba.parqueadero.util.Mensajes;

@Service
public class VehiculoRepository implements IVehiculoRepository{
	
	@Autowired
	private IFacturaRepository facturaRepository;
	@Autowired
	private IVehiculoJPA vehiculoJPA;

	@Override
	public void parquear(Factura factura) throws ParqueoException {
		FacturaConverter facturaConverter = new FacturaConverter();
		VehiculoEntity vehiculoEntity = setParqueado(factura.getVehiculo().getPlaca());
		FacturaEntity facturaEntity = facturaConverter.toEntity(factura);
		facturaEntity.setVehiculoEntity(vehiculoEntity);
		facturaRepository.guardarFactura(facturaEntity);
	}

	@Override
	public void sacarVehiculo(String placa) throws ParqueoException {
		VehiculoConverter vehiculoConverter = new VehiculoConverter();
		Vehiculo vehiculo = (getVehiculo(placa));
		vehiculo.setEstadoParqueo(false);
		vehiculoJPA.save(vehiculoConverter.toEntity(vehiculo));
	}

	@Override
	public int getNumeroParqueados(int tipoVehiculo) {
		
		List<VehiculoEntity> vehiculos = vehiculoJPA.findByEstadoParqueoAndTipoVehiculo(true, tipoVehiculo);
		return vehiculos.size();
	}

	@Override
	public boolean isParqueado(Vehiculo vehiculo) {
		
		VehiculoConverter vehiculoConverter = new VehiculoConverter();
		boolean isParqueado = false;
		VehiculoEntity vehiculoEntity;
		try {
			vehiculoEntity = vehiculoConverter.toEntity(getVehiculo(vehiculo.getPlaca()));
			isParqueado = vehiculoEntity.isParqueado();
		} catch (ParqueoException e) {
			addVehiculo(vehiculo);
		}	
		return isParqueado;
	}
	
	public void addVehiculo(Vehiculo vehiculo) {
		
		VehiculoConverter converter = new VehiculoConverter();
		vehiculoJPA.save(converter.toEntity(vehiculo));
		
	}
	
	public VehiculoEntity setParqueado(String placa) throws ParqueoException {
		
		VehiculoConverter vehiculoConverter = new VehiculoConverter();
		VehiculoEntity vehiculoEntity = vehiculoConverter.toEntity(getVehiculo(placa));
		boolean estadoParqueo = vehiculoEntity.isParqueado();
		vehiculoEntity.setParqueado(!estadoParqueo);
		vehiculoJPA.save(vehiculoEntity);
		return vehiculoEntity;
	}
	
	@Override
	public Vehiculo getVehiculo(String placa) throws ParqueoException {
		
		VehiculoConverter vehiculoConverter = new VehiculoConverter();
		VehiculoEntity vehiculoEntity = vehiculoJPA.findByPlaca(placa);
		Vehiculo vehiculo;
		if(vehiculoEntity == null)
			throw new ParqueoException(Mensajes.VEHICULO_NO_REGISTRADO);
		else
			vehiculo = vehiculoConverter.toDomain(vehiculoEntity);
		return vehiculo;
	}
	
	

}
