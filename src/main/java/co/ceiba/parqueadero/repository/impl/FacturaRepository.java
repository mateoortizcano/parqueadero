package co.ceiba.parqueadero.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.converter.FacturaConverter;
import co.ceiba.parqueadero.domain.Factura;
import co.ceiba.parqueadero.entity.FacturaEntity;
import co.ceiba.parqueadero.jpa.IFacturaJPA;
import co.ceiba.parqueadero.repository.IFacturaRepository;

@Service
public class FacturaRepository implements IFacturaRepository {
	
	@Autowired
	private IFacturaJPA facturaJPA;

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
	public void guardarFactura(FacturaEntity factura) {
		facturaJPA.save(factura);
		
	}

	@Override
	public void actualizar(Factura factura) {
		
		FacturaConverter facturaConverter = new FacturaConverter();
		facturaJPA.save(facturaConverter.toEntity(factura));
	}

}
