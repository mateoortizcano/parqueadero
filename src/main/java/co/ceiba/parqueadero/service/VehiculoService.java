package co.ceiba.parqueadero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.repository.VehiculoRepository;
import co.ceiba.parqueadero.util.Mensajes;

@Service
public class VehiculoService {
	
	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	public List<Vehiculo> obtenerVehiculosParqueados() throws ParqueoException{
		List<Vehiculo> vehiculos = vehiculoRepository.obtenerVehiculosParqueados();
		if(vehiculos.isEmpty()) {
			throw new ParqueoException(Mensajes.PARQUEADERO_VACIO);
		}
		return vehiculos;
	}
	
}
