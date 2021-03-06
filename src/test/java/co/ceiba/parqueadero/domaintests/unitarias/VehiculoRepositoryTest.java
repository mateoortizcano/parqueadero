package co.ceiba.parqueadero.domaintests.unitarias;

import static org.junit.Assert.fail;

import java.util.ArrayList;
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
import co.ceiba.parqueadero.converter.VehiculoConverter;
import co.ceiba.parqueadero.domain.Carro;
import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.Moto;
import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.entity.FacturaEntity;
import co.ceiba.parqueadero.entity.VehiculoEntity;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.jpa.FacturaJPA;
import co.ceiba.parqueadero.jpa.VehiculoJPA;
import co.ceiba.parqueadero.repository.impl.FacturaRepositoryImpl;
import co.ceiba.parqueadero.repository.impl.VehiculoRepositoryImpl;
import co.ceiba.parqueadero.testdatabuilder.CarroTestDataBuilder;
import co.ceiba.parqueadero.testdatabuilder.FacturaTestDataBuilder;
import co.ceiba.parqueadero.testdatabuilder.MotoTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class VehiculoRepositoryTest {
	
	private static final String PLACA = "DCB123";
	private Moto moto;
	private Carro carro;
	private VehiculoConverter vehiculoConverter;
	@InjectMocks
	private VehiculoRepositoryImpl repository;
	@InjectMocks
	private FacturaRepositoryImpl facturaRepositoryImpl;
	@Mock
	private VehiculoJPA vehiculoJPA;
	@Mock
	private FacturaJPA facturaJPA;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void obtenerparqueados() {
		//Arrange
		FacturaConverter facturaConverter = new FacturaConverter();
		List<FacturaEntity> facturas = new ArrayList<>();
		FacturaTestDataBuilder facturaBuilder = new FacturaTestDataBuilder();
		Factura factura = facturaBuilder.build();
		facturas.add(facturaConverter.toEntity(factura));
		facturas.add(facturaConverter.toEntity(factura));
		
		Mockito.when(facturaJPA.findByFechaSalidaIsNull()).thenReturn(facturas);
		//Act
		List<Factura> vehiculosRetornados = facturaRepositoryImpl.obtenerVehiculosParqueados();
		//Assert
		Assert.assertEquals(facturas.size(), vehiculosRetornados.size());
	}
	
	@Test
	public void getVehiculoTest() throws ParqueoException {
		//Arrange
		CarroTestDataBuilder builder = new CarroTestDataBuilder().withPlaca(PLACA).withEstadoParqueo(true);		
		carro = builder.build();
		vehiculoConverter = new VehiculoConverter();
		Mockito.when(vehiculoJPA.findByPlaca(PLACA)).thenReturn(vehiculoConverter.toEntity(carro));
		//Act
		Vehiculo vehiculoAgregado = repository.obtenerVehiculo(PLACA);
		//Assert
		Assert.assertEquals(PLACA, vehiculoAgregado.getPlaca());
	}
	
	@Test
	public void isNoParqueadoTest() throws ParqueoException {
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca(PLACA).withEstadoParqueo(false);		
		moto = builder.build();
		vehiculoConverter = new VehiculoConverter();
		Mockito.when(vehiculoJPA.findByPlaca(PLACA)).thenReturn(vehiculoConverter.toEntity(moto));
		//Act
		boolean message = repository.isParqueado(moto);
		//Assert
		Assert.assertFalse(message);
	}
	
	@Test
	public void isParqueadoTest() throws ParqueoException {
		
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca(PLACA).withEstadoParqueo(true);		
		moto = builder.build();
		vehiculoConverter = new VehiculoConverter();
		Mockito.when(vehiculoJPA.findByPlaca(PLACA)).thenReturn(vehiculoConverter.toEntity(moto));
		//Act
		boolean message = repository.isParqueado(moto);
		//Assert
		Assert.assertTrue(message);
	}
	
	@Test
	public void agregarVehiculoTest() throws ParqueoException {
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca(PLACA).withEstadoParqueo(true);		
		moto = builder.build();
		vehiculoConverter = new VehiculoConverter();
		Mockito.when(vehiculoJPA.save(vehiculoConverter.toEntity(moto))).thenReturn(vehiculoConverter.toEntity(moto));
		//Act
		try {
			repository.agregarVehiculo(moto);
		}catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void setParqueadoTest() throws ParqueoException {
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca(PLACA).withEstadoParqueo(true);		
		moto = builder.build();
		vehiculoConverter = new VehiculoConverter();
		Mockito.when(vehiculoJPA.findByPlaca(PLACA)).thenReturn(vehiculoConverter.toEntity(moto));
		boolean estadoParqueoAnteior = repository.obtenerVehiculo(moto.getPlaca()).isParqueado();
		moto.setEstadoParqueo(false);
		Mockito.when(vehiculoJPA.save(vehiculoConverter.toEntity(moto))).thenReturn(vehiculoConverter.toEntity(moto));
		//Act
		VehiculoEntity entity = repository.setParqueado(moto.getPlaca());
		boolean estadoParqueo = entity.isParqueado();
		//Assert
		Assert.assertNotEquals(estadoParqueo, estadoParqueoAnteior);
	}
	
	@Test
	public void sacarVehiculoTest() throws ParqueoException {
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca(PLACA).withEstadoParqueo(true);		
		moto = builder.build();
		vehiculoConverter = new VehiculoConverter();
		Mockito.when(vehiculoJPA.save(vehiculoConverter.toEntity(moto))).thenReturn(vehiculoConverter.toEntity(moto));
		VehiculoConverter vehiculoConverter = new VehiculoConverter();
		Mockito.when(vehiculoJPA.findByPlaca(PLACA)).thenReturn(vehiculoConverter.toEntity(moto));
		//Act
		try {
			repository.sacarVehiculo(PLACA);
		}catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void obtenerNumeroParqueadostest() {
		//Arrange
		int tipoVehiculoMoto = 1;
		List<VehiculoEntity> listaPrueba = new  ArrayList<>();
		Mockito.when(vehiculoJPA.findByEstadoParqueoAndTipoVehiculo(true, tipoVehiculoMoto)).thenReturn(listaPrueba);
		//Act
		int nroVehiculos = repository.obtenerNumeroParqueados(tipoVehiculoMoto);
		//Assert
		Assert.assertEquals(listaPrueba.size(), nroVehiculos);
	}
	
}
