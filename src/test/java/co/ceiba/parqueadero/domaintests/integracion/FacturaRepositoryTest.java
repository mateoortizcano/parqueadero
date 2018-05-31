package co.ceiba.parqueadero.domaintests.integracion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

import co.ceiba.parqueadero.converter.FacturaConverter;
import co.ceiba.parqueadero.domain.Carro;
import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.entity.FacturaEntity;
import co.ceiba.parqueadero.jpa.FacturaJPA;
import co.ceiba.parqueadero.repository.impl.FacturaRepositoryImpl;
import co.ceiba.parqueadero.testdatabuilder.CarroTestDataBuilder;
import co.ceiba.parqueadero.testdatabuilder.FacturaTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FacturaRepositoryTest {
	@InjectMocks
	private FacturaRepositoryImpl facturaRepository;
	@Mock
	private FacturaJPA facturaJPA;
	private static final String PLACA = "DCB123";
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void obtenerFactura() {
		//Arrange
		FacturaConverter converter = new FacturaConverter();
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().withPlaca(PLACA);
		Carro carro1 = carroTestDataBuilder.build();
		Carro carro2 = carroTestDataBuilder.withPlaca("ABCD123").build();
		FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder();
		Factura factura1 = facturaTestDataBuilder.withFechaIngreso(Calendar.getInstance()).withVehiculo(carro1).build();
		Factura factura2 = facturaTestDataBuilder.withFechaIngreso(Calendar.getInstance()).withVehiculo(carro2).build();
		List<FacturaEntity> facturas = new ArrayList<>();
		facturas.add(converter.toEntity(factura1));
		facturas.add(converter.toEntity(factura2));
		Mockito.when(facturaJPA.findByFechaSalidaIsNull()).thenReturn(facturas);
		//Act
		Factura factura = facturaRepository.obtenerFactura(PLACA);
		//Assert
		Assert.assertEquals(PLACA, factura.getVehiculo().getPlaca());
	}
}
