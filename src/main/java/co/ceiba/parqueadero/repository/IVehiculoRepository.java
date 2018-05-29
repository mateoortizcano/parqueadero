package co.ceiba.parqueadero.repository;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.Vehiculo;

public interface IVehiculoRepository {
	
	void parquear(Factura factura);
	void sacarVehiculo();
	int getNumeroParqueados(int tipoVehiculo);
	public boolean isParqueado(Vehiculo vehiculo);

}
