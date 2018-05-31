package co.ceiba.parqueadero.domaintests.unitarias;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import co.ceiba.parqueadero.domain.Carro;
import co.ceiba.parqueadero.domain.Moto;
import co.ceiba.parqueadero.service.CalculoTarifaVehiculo;
import co.ceiba.parqueadero.testdatabuilder.CarroTestDataBuilder;
import co.ceiba.parqueadero.testdatabuilder.MotoTestDataBuilder;
import co.ceiba.parqueadero.util.Tarifas;

public class CalculoTarifaTest {
	
	CalculoTarifaVehiculo calculoTarifa;
	Moto moto;
	Carro carro;
	static final int TARIFA_8HORAS_CARRO = 8000;
	
	@Test
	public void calcularHorasParqueadoTest() {
		//Arrange
		calculoTarifa = new CalculoTarifaVehiculo();
		Calendar fechaIngreso = Calendar.getInstance();
		fechaIngreso.set(2018, 4, 27, 2, 0);
		Calendar fechaSalida = Calendar.getInstance();
		fechaSalida.set(2018, 4, 27, 2,1);
		//Act
		int totalHoras =calculoTarifa.calcularHorasParqueado(fechaIngreso, fechaSalida);
		//Assert
		Assert.assertEquals(1, totalHoras);
	}
	
	@Test
	public void calcularCobroPorCilindrajeMayor500Test() {
		//Arrange
		calculoTarifa = new CalculoTarifaVehiculo();
		MotoTestDataBuilder dataBuilder = new MotoTestDataBuilder().withCilindraje(600);
		moto = dataBuilder.build();
		//Act
		int costo = calculoTarifa.calcularCobroPorCilindrajeMoto(moto);
		//Assert
		Assert.assertEquals(2000, costo);
		
	}
	
	@Test
	public void calcularCobroPorCilindrajeMenor500Test() {
		//Arrange
		calculoTarifa = new CalculoTarifaVehiculo();
		MotoTestDataBuilder dataBuilder = new MotoTestDataBuilder();
		dataBuilder.withCilindraje(450);
		moto = dataBuilder.build();
		//Act
		int costo = calculoTarifa.calcularCobroPorCilindrajeMoto(moto);
		//Assert
		Assert.assertEquals(0, costo);
		
	}
	
	@Test
	public void CalcularValorParqueoHoraMayorA9Test() {
		//Arrange
		calculoTarifa = new CalculoTarifaVehiculo();
		int tarifaHora = Tarifas.VALOR_HORA_CARRO;
		int tarifaDia = Tarifas.VALOR_DIA_CARRO;
		//Act
		int valor = calculoTarifa.calcularValor(11, tarifaHora, tarifaDia);
		//Assert
		Assert.assertEquals(tarifaDia, valor);
	}
	
	@Test
	public void CalcularValorParqueoHoraMenorA9Test() {
		//Arrange
		calculoTarifa = new CalculoTarifaVehiculo();
		int tarifaHora = Tarifas.VALOR_HORA_CARRO;
		int tarifaDia = Tarifas.VALOR_DIA_CARRO;
		//Act
		int valor = calculoTarifa.calcularValor(8, tarifaHora, tarifaDia);
		//Assert
		Assert.assertEquals(TARIFA_8HORAS_CARRO, valor);
	}
	
	@Test
	public void calcularTarifaCarro() {
		//Arrange
		calculoTarifa = new CalculoTarifaVehiculo();
		CarroTestDataBuilder dataBuilder = new CarroTestDataBuilder();
		carro = dataBuilder.build();
		//Act
		int valor = calculoTarifa.calcularTarifa(carro, 12);
		//Assert
		Assert.assertEquals(Tarifas.VALOR_DIA_CARRO, valor);
		
	}
	
	@Test
	public void calcularTarifaMoto() {
		//Arrange
		calculoTarifa = new CalculoTarifaVehiculo();
		MotoTestDataBuilder dataBuilder = new MotoTestDataBuilder();
		moto = dataBuilder.build();
		//Act
		int valor = calculoTarifa.calcularTarifa(moto, 3);
		//Assert
		Assert.assertEquals(Tarifas.VALOR_HORA_MOTO * 3, valor);
		
	}
}
