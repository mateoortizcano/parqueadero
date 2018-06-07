package co.ceiba.parqueadero.domaintests.integracion;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.entity.FacturaEntity;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.jpa.FacturaJPA;
import co.ceiba.parqueadero.repository.FacturaRepository;
import co.ceiba.parqueadero.service.VehiculoService;
import co.ceiba.parqueadero.util.Mensajes;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class VehiculoServiceTest {
	
	@InjectMocks
	private VehiculoService vehiculoService;
	@Mock
	private FacturaJPA facturaJPA;
	@Mock
	private FacturaRepository facturaRepository;

	@Test
	public void obtenerparqueadosIgualCero() {
		//Arrange
		List<FacturaEntity> facturaEntities = new ArrayList<>();
		Mockito.when(facturaJPA.findByFechaSalidaIsNull()).thenReturn(facturaEntities);
		//Act
		try {
			vehiculoService.obtenerVehiculosParqueados();
			fail();
		} catch (ParqueoException e) {
			Assert.assertEquals(Mensajes.PARQUEADERO_VACIO, e.getMessage());
		}
		
	}
}
