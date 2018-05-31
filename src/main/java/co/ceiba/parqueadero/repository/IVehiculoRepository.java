package co.ceiba.parqueadero.repository;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.exceptions.ParqueoException;

public interface IVehiculoRepository {
	
	void parquear(Factura factura) throws ParqueoException;
	void sacarVehiculo(String placa)throws ParqueoException;
	int getNumeroParqueados(int tipoVehiculo);
	boolean isParqueado(Vehiculo vehiculo) throws ParqueoException;
	Vehiculo getVehiculo(String placa) throws ParqueoException; 

}
