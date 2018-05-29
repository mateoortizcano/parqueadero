package co.ceiba.parqueadero.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ceiba.parqueadero.entity.FacturaEntity;

@Repository
public interface IFacturaJPA extends JpaRepository<FacturaEntity, Serializable>{

}
