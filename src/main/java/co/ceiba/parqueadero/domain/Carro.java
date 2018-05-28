package co.ceiba.parqueadero.domain;

public class Carro extends Vehiculo {
	
	private static final int TIPO_VEHICULO = TipoVehiculo.CARRO;

	public Carro(String placa) {
		super(placa, TIPO_VEHICULO);
	}

}
