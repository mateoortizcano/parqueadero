package co.ceiba.parqueadero.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ceiba.parqueadero.entity.VehiculoEntity;

@Repository
public interface VehiculoJPA extends JpaRepository<VehiculoEntity, Serializable>{
	
	List<VehiculoEntity> findByEstadoParqueoAndTipoVehiculo(boolean estadoParqueo, int tipoVehiculo);
	
	VehiculoEntity findByPlaca(String placa);
}
