package co.ceiba.parqueadero.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ceiba.parqueadero.entity.VehiculoEntity;

@Repository
public interface IVehiculoJPA extends JpaRepository<VehiculoEntity, Serializable>{
	/**
	@Transactional
	@Query("select count(*) from vehiculo where estado_parqueo =true;")
	int contarParqueados();**/
	
	List<VehiculoEntity> findByEstadoParqueoAndTipoVehiculo(boolean estadoParqueo, int tipoVehiculo);
	
	VehiculoEntity findByPlaca(String placa);
}
