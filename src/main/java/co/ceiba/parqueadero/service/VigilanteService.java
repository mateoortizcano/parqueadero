package co.ceiba.parqueadero.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.repository.FacturaRepository;
import co.ceiba.parqueadero.repository.VehiculoRepository;
import co.ceiba.parqueadero.util.Mensajes;

@Service
public class VigilanteService {
	
	@Autowired
	private VehiculoRepository vehiculoRepository;
	@Autowired
	private FacturaRepository facturaRepository;
		
	public void ingresarVehiculo(Vehiculo vehiculo, Calendar fecha) throws ParqueoException {
		
		int nroVehiculosParqueados = vehiculoRepository.obtenerNumeroParqueados(vehiculo.getTipo());
		validarPosibilidadParqueo(vehiculo, fecha, nroVehiculosParqueados);
		
		Factura factura = new Factura();
		factura.setVehiculo(vehiculo);
		factura.setFechaIngreso(fecha);
		
		vehiculoRepository.parquear(factura);
		
	}

	private void validarPosibilidadParqueo(Vehiculo vehiculo, Calendar fecha,
			int nroVehiculosParqueados) throws ParqueoException {
		
		boolean isParqueado = vehiculoRepository.isParqueado(vehiculo);
		
		ValidacionIngresoVehiculo ingresoVehiculo = new ValidacionIngresoVehiculo(vehiculo, fecha);
		
		ValidacionCantidadVehiculos validacionCantidadVehiculos = new ValidacionCantidadVehiculos(
				vehiculo.getTipo(), nroVehiculosParqueados);
		if(isParqueado)
			throw new ParqueoException(Mensajes.VEHICULO_YA_PARQUEADO);
		if(!ingresoVehiculo.validar())
			throw new ParqueoException(Mensajes.VEHICULO_NO_PUEDE_INGRESAR);
		if(!validacionCantidadVehiculos.validar())
			throw new ParqueoException(Mensajes.NO_HAY_CELDAS_DISPONIBLES);
	}
	
	public Factura sacarVehiculo(String placa, Calendar fechaSalida) throws ParqueoException {
		
		Vehiculo vehiculo = vehiculoRepository.obtenerVehiculo(placa);
		boolean isParqueado = vehiculoRepository.isParqueado(vehiculo);
		if(!isParqueado)
			throw new ParqueoException(Mensajes.VEHICULO_NO_PARQUEADO);
		vehiculoRepository.sacarVehiculo(placa);
		Factura factura = facturaRepository.obtenerFactura(vehiculo.getPlaca());
		Calendar fechaIngreso = factura.getFechaIngreso();
		
		CalculoTarifaVehiculo calculoTarifaVehiculo = new CalculoTarifaVehiculo();
		int horasParqueado = calculoTarifaVehiculo.calcularHorasParqueado(fechaIngreso, fechaSalida);
		
		int valorAPagar = calculoTarifaVehiculo.calcularTarifa(vehiculo, horasParqueado);
		
		factura.setFechaSalida(fechaSalida);
		factura.setPrecioTotalParqueo(valorAPagar);
		
		facturaRepository.actualizar(factura);
		return factura;
		
	}

	
}
