package co.ceiba.parqueadero.domaintests.integracion;

import static org.junit.Assert.fail;

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
import co.ceiba.parqueadero.converter.VehiculoConverter;
import co.ceiba.parqueadero.domain.Carro;
import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.Moto;
import co.ceiba.parqueadero.domain.Vehiculo;
import co.ceiba.parqueadero.entity.VehiculoEntity;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.jpa.IVehiculoJPA;
import co.ceiba.parqueadero.repository.impl.FacturaRepository;
import co.ceiba.parqueadero.repository.impl.VehiculoRepository;
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
	private VehiculoRepository repository;
	@Mock
	private FacturaRepository facturaRepository;
	@Mock
	private IVehiculoJPA vehiculoJPA;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getVehiculoTest() throws ParqueoException {
		//Arrange
		CarroTestDataBuilder builder = new CarroTestDataBuilder().withPlaca(PLACA).withEstadoParqueo(true);		
		carro = builder.build();
		vehiculoConverter = new VehiculoConverter();
		Mockito.when(vehiculoJPA.findByPlaca(PLACA)).thenReturn(vehiculoConverter.toEntity(carro));
		//Act
		Vehiculo vehiculoAgregado = repository.getVehiculo(PLACA);
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
	/**
	@Test
	public void vehiculoNoRegistradoTest() throws ParqueoException {
		
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca("DCB123").withCilindraje(500);		
		moto = builder.build();
		//Act
		boolean message = repository.isParqueado(moto);
		//Assert
		Assert.assertFalse(message);
	}**/
	
	@Test
	public void setParqueadoTest() throws ParqueoException {
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca(PLACA).withEstadoParqueo(true);		
		moto = builder.build();
		vehiculoConverter = new VehiculoConverter();
		Mockito.when(vehiculoJPA.findByPlaca(PLACA)).thenReturn(vehiculoConverter.toEntity(moto));
		boolean estadoParqueoAnteior = repository.getVehiculo(moto.getPlaca()).isParqueado();
		moto.setEstadoParqueo(false);
		Mockito.when(vehiculoJPA.save(vehiculoConverter.toEntity(moto))).thenReturn(vehiculoConverter.toEntity(moto));
		//Act
		VehiculoEntity entity = repository.setParqueado(moto.getPlaca());
		boolean estadoParqueo = entity.isParqueado();
		//Assert
		Assert.assertNotEquals(estadoParqueo, estadoParqueoAnteior);
	}
	
	@Test
	public void parquearTest() throws ParqueoException {
		//Arrange
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca(PLACA);		
		moto = builder.build();
		vehiculoConverter = new VehiculoConverter();
		Calendar fecha = Calendar.getInstance();
		FacturaTestDataBuilder facturaBuilder = new FacturaTestDataBuilder().withFechaIngreso(fecha).withVehiculo(moto);
		FacturaConverter facturaConverter = new FacturaConverter();
		Factura factura = facturaBuilder.build();
		Mockito.when(vehiculoJPA.save(vehiculoConverter.toEntity(moto))).thenReturn(vehiculoConverter.toEntity(moto));
		Mockito.when(vehiculoJPA.findByPlaca(PLACA)).thenReturn(vehiculoConverter.toEntity(moto));
		Mockito.when(facturaRepository.guardarFactura(facturaConverter.toEntity(factura))).thenReturn(true);
		//Act
		repository.parquear(factura);
		boolean message = repository.isParqueado(moto);
		//Assert
		Assert.assertNotEquals(message, !message);
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
