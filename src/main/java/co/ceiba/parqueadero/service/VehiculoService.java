package co.ceiba.parqueadero.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.repository.FacturaRepository;
import co.ceiba.parqueadero.util.Mensajes;

@Service
public class VehiculoService {
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	public List<Factura> obtenerVehiculosParqueados() throws ParqueoException{
		List<Factura> facturas = facturaRepository.obtenerVehiculosParqueados();
		if(facturas.isEmpty()) {
			throw new ParqueoException(Mensajes.PARQUEADERO_VACIO);
		}
		Collections.reverse(facturas);
		return facturas;
	}
	
}
