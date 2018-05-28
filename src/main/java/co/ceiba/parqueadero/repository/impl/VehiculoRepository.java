package co.ceiba.parqueadero.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void parquear(FacturaEntity factura) {
		facturaJPA.save(factura);
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

}
