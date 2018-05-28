package co.ceiba.parqueadero.repository;

import co.ceiba.parqueadero.entity.FacturaEntity;

public interface IVehiculoRepository {
	
	void parquear(FacturaEntity factura);
	void sacarVehiculo();
	int getNumeroParqueados(int tipoVehiculo);
}
