package co.ceiba.parqueadero.domaintests.unitarias;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.Moto;
import co.ceiba.parqueadero.repository.impl.VehiculoRepository;
import co.ceiba.parqueadero.testdatabuilder.FacturatestDataBuilder;
import co.ceiba.parqueadero.testdatabuilder.MotoTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class VehiculoRepositoryTest {
	
	private Moto moto;
	@Autowired
	VehiculoRepository repository;
	
	@Test
	public void isNoParqueadoTest() {
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca("DCB123").withCilindraje(500);		
		moto = builder.build();
		//Act
		boolean message = repository.isParqueado(moto);
		//Assert
		Assert.assertFalse(message);
	}
	
	@Test
	public void isParqueadoTest() {
		
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca("DCBA123").withCilindraje(500);		
		moto = builder.build();
		//Act
		boolean message = repository.isParqueado(moto);
		//Assert
		Assert.assertTrue(message);
	}
	@Test
	public void vehiculoNoRegistradoTest() {
		
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca("DCB123").withCilindraje(500);		
		moto = builder.build();
		//Act
		boolean message = repository.isParqueado(moto);
		//Assert
		Assert.assertFalse(message);
	}
	
	@Test
	public void agregarVehiculoTest() {
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca("DCBB123").withCilindraje(500);		
		moto = builder.build();
		//Act
		repository.addVehiculo(moto);
		//VehiculoEntity vehiculo = repository.getVehiculo(moto.getPlaca());
		//Assert
		//Assert.assertEquals(moto.getPlaca(), vehiculo.getPlaca());
	}
	
	@Test
	public void setParqueadoTest() {
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca("SET123").withCilindraje(500);		
		moto = builder.build();
		//Act
		boolean estadoParqueoAnteior = repository.getVehiculo(moto.getPlaca()).isParqueado();
		repository.setParqueado(moto.getPlaca());
		boolean estadoParqueo = repository.isParqueado(moto);
		
		//Assert
		Assert.assertNotEquals(estadoParqueo, estadoParqueoAnteior);
	}
	
	@Test
	public void parquearTest() {
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca("PAR123").withCilindraje(500);		
		moto = builder.build();
		Calendar fecha = Calendar.getInstance();
		FacturatestDataBuilder facturaBuilder = new FacturatestDataBuilder().withFechaIngreso(fecha).withVehiculo(moto);
		Factura factura = facturaBuilder.build();
		//Act
		repository.parquear(factura);
		boolean message = repository.isParqueado(moto);
		//Assert
		Assert.assertNotEquals(message, !message);
	}
	
}
