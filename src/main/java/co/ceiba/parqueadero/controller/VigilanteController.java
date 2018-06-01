package co.ceiba.parqueadero.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.service.VehiculoService;
import co.ceiba.parqueadero.service.VigilanteService;
import co.ceiba.parqueadero.util.Mensajes;

@RestController
public class VigilanteController {
	
	@Autowired
	private VigilanteService vigilanteService;
	@Autowired
	private VehiculoService vehiculoService;
	
	@RequestMapping(value = "/parquearVehiculo", method = RequestMethod.POST)
	public boolean parquearVehiculo(@RequestBody Vehiculo vehiculo) throws ParqueoException {
		Calendar fechaIngreso = Calendar.getInstance();
		String resultadoProceso = vigilanteService.ingresarVehiculo(vehiculo, fechaIngreso);
		return resultadoProceso == Mensajes.OPERACION_EXITOSA ? true : false ;
	}
	
	@RequestMapping(value = "/sacarVehiculo", method = RequestMethod.GET)
	public Factura sacarVehiculo(@RequestParam(value = "placa") String placa) throws ParqueoException {
		Calendar fechaSalida = Calendar.getInstance();
		Factura factura = vigilanteService.sacarVehiculo(placa, fechaSalida);
		return factura;
	}
	
	@RequestMapping(value = "/obtenerParqueados", method = RequestMethod.GET) 
	public List<Vehiculo> obtenerParqueados() throws ParqueoException{
		return vehiculoService.obtenerVehiculosParqueados();
	}
}
