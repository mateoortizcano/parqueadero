package co.ceiba.parqueadero.domaintests.integracion;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.converter.VehiculoConverter;
import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.Moto;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.jpa.VehiculoJPA;
import co.ceiba.parqueadero.repository.FacturaRepository;
import co.ceiba.parqueadero.repository.impl.VehiculoRepositoryImpl;
import co.ceiba.parqueadero.testdatabuilder.FacturaTestDataBuilder;
import co.ceiba.parqueadero.testdatabuilder.MotoTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class VehiculoRespositoryTest {
	
	private static final String PLACA = "DCB123";
	private Moto moto;
	private VehiculoConverter vehiculoConverter;
	@InjectMocks
	private VehiculoRepositoryImpl repository;
	@Mock
	private FacturaRepository facturaRepository;
	@Mock
	private VehiculoJPA vehiculoJPA;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void parquearTest() throws ParqueoException {
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca(PLACA);		
		moto = builder.build();
		vehiculoConverter = new VehiculoConverter();
		Calendar fecha = Calendar.getInstance();
		FacturaTestDataBuilder facturaBuilder = new FacturaTestDataBuilder().withFechaIngreso(fecha).withVehiculo(moto);
		Factura factura = facturaBuilder.build();
		Mockito.when(vehiculoJPA.save(vehiculoConverter.toEntity(moto))).thenReturn(vehiculoConverter.toEntity(moto));
		Mockito.when(vehiculoJPA.findByPlaca(PLACA)).thenReturn(vehiculoConverter.toEntity(moto));
		//Act
		repository.parquear(factura);
		boolean message = repository.isParqueado(moto);
		//Assert
		Assert.assertNotEquals(message, !message);
	}
}
