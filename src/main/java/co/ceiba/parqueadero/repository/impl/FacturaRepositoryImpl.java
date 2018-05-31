package co.ceiba.parqueadero.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.converter.FacturaConverter;
import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.entity.FacturaEntity;
import co.ceiba.parqueadero.jpa.FacturaJPA;
import co.ceiba.parqueadero.repository.FacturaRepository;

@Service
public class FacturaRepositoryImpl implements FacturaRepository {
	
	@Autowired
	private FacturaJPA facturaJPA;

	@Override
	public Factura obtenerFactura(String placa) {
		
		FacturaConverter facturaConverter = new FacturaConverter();
		List<FacturaEntity> facturasEntity = facturaJPA.findByFechaSalidaIsNull();
		FacturaEntity facturaEntity = new FacturaEntity();
		for (FacturaEntity factura : facturasEntity) {
			if(factura.getVehiculoEntity().getPlaca() == placa) {
				facturaEntity = factura;
				break;
			}
		}
		return facturaConverter.toDomain(facturaEntity);
	}

	@Override
	public boolean guardarFactura(FacturaEntity factura) {
		
		facturaJPA.save(factura);
		return true;
	}

	@Override
	public boolean actualizar(Factura factura) {
		
		FacturaConverter facturaConverter = new FacturaConverter();
		facturaJPA.save(facturaConverter.toEntity(factura));
		return true;
	}

}
