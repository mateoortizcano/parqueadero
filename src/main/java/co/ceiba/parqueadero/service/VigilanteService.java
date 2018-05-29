package co.ceiba.parqueadero.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.ValidacionCantidadVehiculos;
import co.ceiba.parqueadero.domain.ValidacionIngresoVehiculo;
import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.repository.IVehiculoRepository;
import co.ceiba.parqueadero.util.Constantes;

@Service
public class VigilanteService {
	
	@Autowired
	private IVehiculoRepository vehiculoRepository;
		
	public String ingresarVehiculo(Vehiculo vehiculo, Calendar fecha) throws ParqueoException {
		
		int nroVehiculosParqueados = vehiculoRepository.getNumeroParqueados(vehiculo.getTipo());
				System.out.println(nroVehiculosParqueados);
		validarPosibilidadParqueo(vehiculo, fecha, nroVehiculosParqueados);
		
		Factura factura = new Factura(vehiculo, fecha);
		
		vehiculoRepository.parquear(factura);
		
		return Constantes.OPERACION_EXITOSA;
	}

	private void validarPosibilidadParqueo(Vehiculo vehiculo, Calendar fecha,
			int nroVehiculosParqueados) throws ParqueoException {
		
		boolean isParqueado = vehiculoRepository.isParqueado(vehiculo);
		
		ValidacionIngresoVehiculo ingresoVehiculo = new ValidacionIngresoVehiculo(vehiculo, fecha);
		
		ValidacionCantidadVehiculos validacionCantidadVehiculos = new ValidacionCantidadVehiculos(
				vehiculo.getTipo(), nroVehiculosParqueados);
		
		if(isParqueado)
			throw new ParqueoException(Constantes.VEHICULO_YA_PARQUEADO);
		if(!ingresoVehiculo.validar())
			throw new ParqueoException(Constantes.VEHICULO_NO_PUEDE_INGRESAR);
		if(!validacionCantidadVehiculos.validar())
			throw new ParqueoException(Constantes.NO_HAY_CELDAS_DISPONIBLES);
	}
}
