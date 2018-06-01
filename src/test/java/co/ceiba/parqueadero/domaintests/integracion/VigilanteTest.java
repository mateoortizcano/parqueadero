package co.ceiba.parqueadero.domaintests.integracion;

import java.util.Calendar;

import static org.junit.Assert.fail;

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

import co.ceiba.parqueadero.domain.Carro;
import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.domain.Moto;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.repository.FacturaRepository;
import co.ceiba.parqueadero.repository.VehiculoRepository;
import co.ceiba.parqueadero.service.VigilanteService;
import co.ceiba.parqueadero.testdatabuilder.CarroTestDataBuilder;
import co.ceiba.parqueadero.testdatabuilder.FacturaTestDataBuilder;
import co.ceiba.parqueadero.testdatabuilder.MotoTestDataBuilder;
import co.ceiba.parqueadero.util.Mensajes;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class VigilanteTest {
	
	private static final String PLACA = "DCB562";
	private static final String PLACA_INICIADA_A = "ACB562";
	private Moto moto;
	private Carro carro;
	private Calendar fecha;
	@InjectMocks
	private VigilanteService  service;
	@Mock
	private VehiculoRepository vehiculoRepository;
	@Mock
	private FacturaRepository facturaRepository;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void IngresarvehiculoYaParqueado() throws ParqueoException {
		
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca(PLACA);		
		moto = builder.build();
		Mockito.when(vehiculoRepository.obtenerNumeroParqueados(moto.getTipo())).thenReturn(4);
		Mockito.when(vehiculoRepository.isParqueado(moto)).thenReturn(true);
		FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder();
		Factura factura = facturaTestDataBuilder.build();
		Mockito.when(vehiculoRepository.parquear(factura)).thenReturn(true);
		fecha = Calendar.getInstance();
		
		try {
			service.ingresarVehiculo(moto, fecha);
			fail();
		}catch(ParqueoException ex) {
			Assert.assertEquals(Mensajes.VEHICULO_YA_PARQUEADO, ex.getMessage());
		}
	}
	
	@Test
	public void IngresarvehiculoNoParqueado() throws ParqueoException {
		
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca(PLACA);		
		moto = builder.build();
		Mockito.when(vehiculoRepository.obtenerNumeroParqueados(moto.getTipo())).thenReturn(4);
		Mockito.when(vehiculoRepository.isParqueado(moto)).thenReturn(false);
		FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder();
		Factura factura = facturaTestDataBuilder.build();
		Mockito.when(vehiculoRepository.parquear(factura)).thenReturn(true);
		fecha = Calendar.getInstance();
		
		try {
			String mensaje = service.ingresarVehiculo(moto, fecha);
			Assert.assertEquals(Mensajes.OPERACION_EXITOSA, mensaje);
		}catch(ParqueoException ex) {
			fail();
		}
		
	}
	
	@Test
	public void IngresarvehiculoDiaNoPermitido() throws ParqueoException {
		
		MotoTestDataBuilder builder = new MotoTestDataBuilder().withPlaca(PLACA_INICIADA_A);		
		moto = builder.build();
		Mockito.when(vehiculoRepository.obtenerNumeroParqueados(moto.getTipo())).thenReturn(4);
		Mockito.when(vehiculoRepository.isParqueado(moto)).thenReturn(false);
		FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder();
		Factura factura = facturaTestDataBuilder.build();
		Mockito.when(vehiculoRepository.parquear(factura)).thenReturn(true);
		fecha = Calendar.getInstance();
		fecha.set(2018, 4, 30);//miercoles
		
		try {
			service.ingresarVehiculo(moto, fecha);
			fail();
		}catch(ParqueoException ex) {
			Assert.assertEquals(Mensajes.VEHICULO_NO_PUEDE_INGRESAR, ex.getMessage());
		}
	}
	
	@Test
	public void SacarVehiculoTest() throws ParqueoException {
		//Arrange
		CarroTestDataBuilder builder = new CarroTestDataBuilder().withPlaca(PLACA);		
		carro = builder.build();
		fecha = Calendar.getInstance();
		fecha.set(2018, 5, 5);
		Mockito.when(vehiculoRepository.getVehiculo(PLACA)).thenReturn(carro);
		Mockito.when(vehiculoRepository.isParqueado(carro)).thenReturn(true);
		FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder();
		Factura factura = facturaTestDataBuilder.build();
		Mockito.when(facturaRepository.actualizar(factura)).thenReturn(true);
		Mockito.when(facturaRepository.obtenerFactura(PLACA)).thenReturn(factura);
		
		try {
			//Act
			service.sacarVehiculo(carro.getPlaca(), fecha);
		} catch (ParqueoException e) {
			fail();
		}
	}
}
