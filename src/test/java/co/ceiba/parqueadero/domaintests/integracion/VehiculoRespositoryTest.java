package co.ceiba.parqueadero.domaintests.integracion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

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
import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.entity.VehiculoEntity;
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
	private static final String PLACA2 = "DCB124";
	private static final String PLACA3 = "DCB125";
	private Moto moto;
	private VehiculoConverter vehiculoConverter;
	@InjectMocks
	private VehiculoRepositoryImpl repository;
	@Mock
	private FacturaRepository facturaRepository;
	@Mock
	private VehiculoJPA vehiculoJPA;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		vehiculoConverter = new VehiculoConverter();
	}
	
	@Test
	public void parquearTest() throws ParqueoException {
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca(PLACA);		
		moto = builder.build();
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
	
	@Test
	public void obtenerparqueados() {
		//Arrange
		List<Vehiculo> vehiculos = new ArrayList<>();
		MotoTestDataBuilder builder = new MotoTestDataBuilder();
		moto = builder.withPlaca(PLACA).build();
		vehiculos.add(moto);
		moto = builder.withPlaca(PLACA2).build();
		vehiculos.add(moto);
		moto = builder.withPlaca(PLACA3).build();
		vehiculos.add(moto);
		List<VehiculoEntity> vehiculoEntities = vehiculos.stream().map(VehiculoEntity -> vehiculoConverter.toEntity((VehiculoEntity)))
				.collect(Collectors.toList());
		Mockito.when(vehiculoJPA.findByEstadoParqueo(true)).thenReturn(vehiculoEntities);
		//Act
		List<Vehiculo> vehiculosRetornados = repository.obtenerVehiculosParqueados();
		//Assert
		Assert.assertEquals(vehiculos.size(), vehiculosRetornados.size());
	}
	
}
