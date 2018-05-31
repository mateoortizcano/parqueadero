package co.ceiba.parqueadero.domaintests.unitarias;

import java.util.Calendar;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.domain.Moto;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.service.VigilanteService;
import co.ceiba.parqueadero.testdatabuilder.MotoTestDataBuilder;
import co.ceiba.parqueadero.util.Mensajes;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class VigilanteTest {
	
	private Moto moto;
	private Calendar fecha;
	@Autowired
	VigilanteService  service;
	
	@Test
	public void IngresarvehiculoYaParqueado() {
		
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca("DCBA123").withCilindraje(500);		
		moto = builder.build();
		fecha = Calendar.getInstance();
		
		try {
			service.ingresarVehiculo(moto, fecha);
			fail();
		}catch(ParqueoException ex) {
			Assert.assertEquals(Mensajes.VEHICULO_YA_PARQUEADO, ex.getMessage());
		}
	}
	
	@Test
	public void IngresarvehiculoDiaNoPermitido() {
		
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca("ABD123").withCilindraje(500);		
		moto = builder.build();
		fecha = Calendar.getInstance();
		fecha.set(2018, 4, 30);//miercoles
		
		try {
			service.ingresarVehiculo(moto, fecha);
			fail();
		}catch(ParqueoException ex) {
			Assert.assertEquals(Mensajes.VEHICULO_NO_PUEDE_INGRESAR, ex.getMessage());
		}
	}
	/**
	@Test
	public void numeroCeldasInsuficientes() {
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca("DCB321").withCilindraje(500);		
		moto = builder.build();
		fecha = Calendar.getInstance();
		
		try {
			service.ingresarVehiculo(moto, fecha);
			fail();
		}catch(ParqueoException ex) {
			Assert.assertEquals(Mensajes.NO_HAY_CELDAS_DISPONIBLES, ex.getMessage());
		}
	}**/
}
