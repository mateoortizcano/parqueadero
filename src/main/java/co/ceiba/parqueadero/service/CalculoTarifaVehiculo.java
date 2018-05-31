package co.ceiba.parqueadero.service;

import java.util.Calendar;

import co.ceiba.parqueadero.domain.TipoVehiculo;
import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.util.Tarifas;

public class CalculoTarifaVehiculo {
	
	public int calcularTarifa(Vehiculo vehiculo, int horasParqueado) {
		
		int tipoVehiculo = vehiculo.getTipo();
		
		int tarifaHora = 0;
		int tarifaDia  = 0;
		int valorAPagar = 0;
		
		if(tipoVehiculo == TipoVehiculo.MOTO) {
			tarifaHora = Tarifas.VALOR_HORA_MOTO;
			tarifaDia = Tarifas.VALOR_DIA_MOTO;
			valorAPagar = calcularCobroPorCilindrajeMoto(vehiculo);
			
		} 
		else if(tipoVehiculo == TipoVehiculo.CARRO) {
			tarifaHora = Tarifas.VALOR_HORA_CARRO;
			tarifaDia = Tarifas.VALOR_DIA_CARRO;
		}
		
		int numeroDias = horasParqueado / 24;
		valorAPagar += numeroDias * tarifaDia;
		valorAPagar += calcularValor(horasParqueado, tarifaHora, tarifaDia);
		
		return valorAPagar;
	}

	public int calcularValor(int horasParqueado, int tarifaHora, int tarifaDia) {
		
		int valor = 0;
		int numeroHoras = horasParqueado % 24;
		if(numeroHoras >= 9) {
			valor += tarifaDia;
		}
		else {
			valor += tarifaHora * numeroHoras;
		}
		return valor;
	}

	public int calcularCobroPorCilindrajeMoto(Vehiculo vehiculo) {
		
		int valorAdicional;
		if(vehiculo.getCilindraje() > 500) 
			valorAdicional = Tarifas.TARIFA_EXTRA_MOTO_CILINDRAJE_MAYOR_550CC;
		else
			valorAdicional = 0;
		return valorAdicional;
	}

	public int calcularHorasParqueado(Calendar fechaIngreso, Calendar fechaSalida) {
		
		final double MILISEGUNDOS_POR_HORA = (double) 1000 * 60 * 60;
		double milisegundos =(double)  fechaSalida.getTime().getTime() - fechaIngreso.getTime().getTime();
		double horas = milisegundos / MILISEGUNDOS_POR_HORA;
		return (int) Math.ceil(horas);
	}
}
