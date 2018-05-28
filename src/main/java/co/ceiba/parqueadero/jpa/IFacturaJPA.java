package co.ceiba.parqueadero.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.parqueadero.entity.FacturaEntity;

public interface IFacturaJPA extends JpaRepository<FacturaEntity, Serializable>{

}
