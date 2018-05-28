package co.ceiba.parqueadero.domaintests.unitarias;

import org.junit.Test;

import co.ceiba.parqueadero.domain.Moto;
import co.ceiba.parqueadero.service.VigilanteService;
import co.ceiba.parqueadero.testdatabuilder.MotoTestDataBuilder;

public class VigilanteTests {
	
	private Moto moto;
	
	@Test
	public void Ingresarvehiculo() {
		MotoTestDataBuilder builder = new MotoTestDataBuilder();
		builder.withPlaca("AAA333").withCilindraje(500);		
		moto = builder.build();
		VigilanteService  service = new VigilanteService();
	}
}
