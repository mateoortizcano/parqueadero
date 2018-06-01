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

import co.ceiba.parqueadero.entity.VehiculoEntity;
import co.ceiba.parqueadero.exceptions.ParqueoException;
import co.ceiba.parqueadero.jpa.VehiculoJPA;
import co.ceiba.parqueadero.repository.VehiculoRepository;
import co.ceiba.parqueadero.service.VehiculoService;
import co.ceiba.parqueadero.util.Mensajes;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class VehiculoServiceTest {
	
	@InjectMocks
	private VehiculoService vehiculoService;
	@Mock
	private VehiculoJPA vehiculoJPA;
	@Mock
	private VehiculoRepository vehiculoRepository;

	@Test
	public void obtenerparqueadosIgualCero() {
		//Arrange
		List<VehiculoEntity> vehiculoEntities = new ArrayList<>();
		Mockito.when(vehiculoJPA.findByEstadoParqueo(true)).thenReturn(vehiculoEntities);
		//Act
		try {
			vehiculoService.obtenerVehiculosParqueados();
			fail();
		} catch (ParqueoException e) {
			Assert.assertEquals(Mensajes.PARQUEADERO_VACIO, e.getMessage());
		}
		
	}
}
