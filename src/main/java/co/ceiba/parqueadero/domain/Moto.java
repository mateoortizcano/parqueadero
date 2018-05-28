package co.ceiba.parqueadero.domain;

public class Moto extends Vehiculo {
	
	private static final int TIPO_VEHICULO = TipoVehiculo.MOTO;
	
	
	public Moto(String placa, int cilindraje) {
		super(placa, TIPO_VEHICULO);
		super.setCilindraje(cilindraje);
		super.setEstadoParqueo(false);
	}
	
}
