package co.ceiba.parqueadero.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ceiba.parqueadero.entity.FacturaEntity;

@Repository
public interface FacturaJPA extends JpaRepository<FacturaEntity, Serializable>{
	
	List<FacturaEntity> findByFechaSalidaIsNull();
	
}
