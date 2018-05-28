package co.ceiba.parqueadero.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.converter.FacturaConverter;
import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.ValidacionCantidadVehiculos;
import co.ceiba.parqueadero.domain.ValidacionIngresoVehiculo;
import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.repository.IVehiculoRepository;

@Service
public class VigilanteService {
	
	@Autowired
	private IVehiculoRepository vehiculoRepository;
	
	private static final String NO_HAY_CELDAS_DISPONIBLES = "No hay celdas disponibles para parqueo";
	private static final String VEHICULO_NO_PUEDE_INGRESAR = "El vehículo no puede ingresar porque no se ecuentra"
			+ "dentro de un día hábil para hacerlo.";
	private static final String OPERACION_EXITOSA = "Operación Exitosa";
		
	public String ingresarVehiculo(Vehiculo vehiculoMotor) throws Exception {
		//validar que no se encuentre parqueado
		int tipoVehiculo = vehiculoMotor.getTipo();
		Calendar fecha = Calendar.getInstance();
		int nroVehiculosParqueados = vehiculoRepository.getNumeroParqueados(vehiculoMotor.getTipo());
		System.out.println(nroVehiculosParqueados);
		ValidacionIngresoVehiculo ingresoVehiculo = new ValidacionIngresoVehiculo(vehiculoMotor, fecha);
		ValidacionCantidadVehiculos validacionCantidadVehiculos = new ValidacionCantidadVehiculos(tipoVehiculo, 
				nroVehiculosParqueados);
		
		if(!ingresoVehiculo.validar())
			throw new ParqueoException(VEHICULO_NO_PUEDE_INGRESAR);
		if(!validacionCantidadVehiculos.validar())
			throw new ParqueoException(NO_HAY_CELDAS_DISPONIBLES);

		Factura factura = new Factura(vehiculoMotor, fecha);
		FacturaConverter converter = new FacturaConverter();
		vehiculoRepository.parquear(converter.toEntity(factura));
		return OPERACION_EXITOSA;
	}
}
