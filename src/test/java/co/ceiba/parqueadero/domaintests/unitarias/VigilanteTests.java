package co.ceiba.parqueadero.domaintests.unitarias;

import java.util.Calendar;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.ceiba.parqueadero.domain.Moto;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.service.VigilanteService;
import co.ceiba.parqueadero.testdatabuilder.MotoTestDataBuilder;
import co.ceiba.parqueadero.util.Constantes;

public class VigilanteTests {
	
	private Moto moto;
	private Calendar fecha;
	@Autowired
	VigilanteService  service;
	
	@Test
	public void IngresarvehiculoYaParqueado() {
		/**
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca("ABC123").withCilindraje(500);		
		moto = builder.build();
		fecha = Calendar.getInstance();
		
		try {
			service.ingresarVehiculo(moto, fecha);
			fail();
		}catch(ParqueoException ex) {
			System.out.println(ex.getMessage());
			Assert.assertEquals(Constantes.VEHICULO_NO_PUEDE_INGRESAR, ex.getMessage());
		}**/
	}
}
