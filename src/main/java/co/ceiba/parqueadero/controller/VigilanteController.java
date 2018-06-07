package co.ceiba.parqueadero.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VigilanteController {
	
	@Autowired
	private VigilanteService vigilanteService;
	@Autowired
	private VehiculoService vehiculoService;
	
	@RequestMapping(value = "/parquearVehiculo", method = RequestMethod.POST)
	public void parquearVehiculo(@RequestBody Vehiculo vehiculo) throws ParqueoException {
		
		Calendar fechaIngreso = Calendar.getInstance();
		vigilanteService.ingresarVehiculo(vehiculo, fechaIngreso);
	}
	
	@RequestMapping(value = "/sacarVehiculo", method = RequestMethod.GET)
	public Factura sacarVehiculo(@RequestParam(value = "placa") String placa) throws ParqueoException {
		
		Calendar fechaSalida = Calendar.getInstance();
		return vigilanteService.sacarVehiculo(placa, fechaSalida);
	}
	
	@RequestMapping(value = "/obtenerParqueados", method = RequestMethod.GET) 
	public List<Factura> obtenerParqueados() throws ParqueoException{
		
		return vehiculoService.obtenerVehiculosParqueados();
	}
}
