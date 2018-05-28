package co.ceiba.parqueadero.domaintests.unitarias;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import co.ceiba.parqueadero.domain.Carro;
import co.ceiba.parqueadero.domain.Moto;
import co.ceiba.parqueadero.domain.TipoVehiculo;
import co.ceiba.parqueadero.domain.ValidacionCantidadVehiculos;
import co.ceiba.parqueadero.domain.ValidacionIngresoVehiculo;
import co.ceiba.parqueadero.testdatabuilder.CarroTestDataBuilder;
import co.ceiba.parqueadero.testdatabuilder.MotoTestDataBuilder;

public class ValidacionTests {
	
	private static final int VEHICULO_INVALIDO_PARA_PARQUEAR = -1;
	private ValidacionIngresoVehiculo validacionIngresoVehiculo;
	private ValidacionCantidadVehiculos validacionCantidadVehiculos;
	private Carro carro;
	private Moto moto;
	private Calendar fecha;
	private int tipoVehiculo;
	private int nroVehiculosParqueados;
	
	@Test
	public void validarCeldasCarroSuficienteTest() {
		
		//Arrange
		this.tipoVehiculo = TipoVehiculo.CARRO;
		this.nroVehiculosParqueados = 10;
		validacionCantidadVehiculos = new ValidacionCantidadVehiculos(tipoVehiculo, nroVehiculosParqueados);
		
		//Act
		boolean message = validacionCantidadVehiculos.validar();
		
		//Assert
		Assert.assertTrue(message);
	}
	
	@Test
	public void validarCeldasMotoSuficienteTest() {
		
		//Arrange
		this.tipoVehiculo = TipoVehiculo.MOTO;
		this.nroVehiculosParqueados = 9;
		validacionCantidadVehiculos = new ValidacionCantidadVehiculos(tipoVehiculo, nroVehiculosParqueados);
		
		//Act
		boolean message = validacionCantidadVehiculos.validar();
		
		//Assert
		Assert.assertTrue(message);
	}
	
	@Test
	public void validarCeldasInsuficientesTest() {
		
		//Arrange
		this.tipoVehiculo = TipoVehiculo.MOTO;
		this.nroVehiculosParqueados = 10;
		validacionCantidadVehiculos = new ValidacionCantidadVehiculos(tipoVehiculo, nroVehiculosParqueados);
		
		//Act
		boolean message = validacionCantidadVehiculos.validar();
		
		//Assert
		Assert.assertFalse(message);
	}
	
	@Test
	public void vehiculoNoParqueableTest() {
		
		//Arrange
		this.tipoVehiculo = VEHICULO_INVALIDO_PARA_PARQUEAR;
		this.nroVehiculosParqueados = 10;
		validacionCantidadVehiculos = new ValidacionCantidadVehiculos(tipoVehiculo, nroVehiculosParqueados);
		
		//Act
		boolean message = validacionCantidadVehiculos.validar();
		
		//Assert
		Assert.assertFalse(message);
	}
	
	@Test
	public void esValidoParquearDomingo() {
		//Arrange
		fecha = Calendar.getInstance();
		fecha.set(2018, 4, 27);//Domingo
		CarroTestDataBuilder builder = new CarroTestDataBuilder();
		builder.withPlaca("AAA333");		
		carro = builder.build();
		validacionIngresoVehiculo = new ValidacionIngresoVehiculo(carro, fecha);
		
		//Act
		boolean message = validacionIngresoVehiculo.validar();
		
		//Assert
		Assert.assertTrue(message);		
	}
	
	@Test
	public void esValidoParquearLunes() {
		//Arrange
		fecha = Calendar.getInstance();
		fecha.set(2018, 4, 28);//Lunes
		MotoTestDataBuilder builder = new MotoTestDataBuilder();
		builder.withPlaca("AAA333");		
		moto = builder.build();
		validacionIngresoVehiculo = new ValidacionIngresoVehiculo(moto, fecha);
		
		//Act
		boolean message = validacionIngresoVehiculo.validar();
		
		//Assert
		Assert.assertTrue(message);		
	}
	
	@Test
	public void esInvalidoParquear() {
		//Arrange
		fecha = Calendar.getInstance();
		fecha.set(2018, 4, 29);
		System.out.println(fecha.getTime());
		CarroTestDataBuilder builder = new CarroTestDataBuilder();
		builder.withPlaca("AAA333");		
		carro = builder.build();
		validacionIngresoVehiculo = new ValidacionIngresoVehiculo(carro, fecha);
		
		//Act
		boolean message = validacionIngresoVehiculo.validar();
		
		//Assert
		Assert.assertFalse(message);		
	}
	
	@Test
	public void parquearAutoSinRestriccion() {
		//Arrange
		fecha = Calendar.getInstance();
		fecha.set(2018, 4, 30);
		CarroTestDataBuilder builder = new CarroTestDataBuilder();
		builder.withPlaca("BAA333");		
		carro = builder.build();
		validacionIngresoVehiculo = new ValidacionIngresoVehiculo(carro, fecha);
		
		//Act
		boolean message = validacionIngresoVehiculo.validar();
		
		//Assert
		Assert.assertTrue(message);		
	}
}
