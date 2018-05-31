package co.ceiba.parqueadero.repository;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.exceptions.ParqueoException;

public interface VehiculoRepository {
	
	boolean parquear(Factura factura) throws ParqueoException;
	boolean sacarVehiculo(String placa)throws ParqueoException;
	int obtenerNumeroParqueados(int tipoVehiculo);
	boolean isParqueado(Vehiculo vehiculo) throws ParqueoException;
	Vehiculo getVehiculo(String placa) throws ParqueoException; 

}
