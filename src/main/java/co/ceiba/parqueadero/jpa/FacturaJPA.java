package co.ceiba.parqueadero.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.ceiba.parqueadero.entity.FacturaEntity;

@Repository
public interface FacturaJPA extends JpaRepository<FacturaEntity, Serializable>{
	
	List<FacturaEntity> findByFechaSalidaIsNull();
	
	@Query(value = "select fact.id, fact.fecha_ingreso, fact.fecha_salida, fact.precio_total_parqueo, fact.vehiculo_entity_id "
			+ "from parqueaderoschema.factura fact, parqueaderoschema.vehiculo veh where fact.vehiculo_entity_id = veh.id and "
			+ "veh.placa = ?1 and veh.estado_parqueo=true;", nativeQuery = true)
	FacturaEntity FindAllWithDescriptionQuery(String placa);
	
}
