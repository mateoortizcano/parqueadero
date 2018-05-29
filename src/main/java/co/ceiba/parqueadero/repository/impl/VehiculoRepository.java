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
import co.ceiba.parqueadero.jpa.IFacturaJPA;
import co.ceiba.parqueadero.jpa.IVehiculoJPA;
import co.ceiba.parqueadero.repository.IVehiculoRepository;

@Service
public class VehiculoRepository implements IVehiculoRepository{
	
	@Autowired
	private IFacturaJPA facturaJPA;
	@Autowired
	private IVehiculoJPA vehiculoJPA;

	@Override
	public void parquear(Factura factura) {
		FacturaConverter facturaConverter = new FacturaConverter();
		VehiculoEntity vehiculoEntity = setParqueado(factura.getVehiculo().getPlaca());
		FacturaEntity facturaEntity = facturaConverter.toEntity(factura);
		facturaEntity.setVehiculoEntity(vehiculoEntity);
		facturaJPA.save(facturaEntity);
	}

	@Override
	public void sacarVehiculo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumeroParqueados(int tipoVehiculo) {
		
		List<VehiculoEntity> vehiculos = vehiculoJPA.findByEstadoParqueoAndTipoVehiculo(true, tipoVehiculo);
		return vehiculos.size();
	}

	@Override
	public boolean isParqueado(Vehiculo vehiculo) {
		
		boolean isParqueado = false;
		VehiculoEntity vehiculoEntity = getVehiculo(vehiculo.getPlaca());
		
		if(vehiculoEntity != null) {
			isParqueado = vehiculoEntity.isParqueado();
		}
		else {
			addVehiculo(vehiculo);
		}		
		return isParqueado;
	}
	
	public void addVehiculo(Vehiculo vehiculo) {
		
		VehiculoConverter converter = new VehiculoConverter();
		vehiculoJPA.save(converter.toEntity(vehiculo));
	}
	
	public VehiculoEntity setParqueado(String placa) {
		
		VehiculoEntity vehiculoEntity = getVehiculo(placa);
		boolean estadoParqueo = vehiculoEntity.isParqueado();
		vehiculoEntity.setParqueado(!estadoParqueo);
		vehiculoJPA.save(vehiculoEntity);
		return vehiculoEntity;
	}
	
	public VehiculoEntity getVehiculo(String placa) {
		
		VehiculoEntity vehiculoEntity = vehiculoJPA.findByPlaca(placa);
		return vehiculoEntity;
	}
	
	

}
