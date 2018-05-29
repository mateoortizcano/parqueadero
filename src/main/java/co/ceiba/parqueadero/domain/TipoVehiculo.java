package co.ceiba.parqueadero.domain;

public abstract class TipoVehiculo {
	
	private TipoVehiculo() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static final int MOTO = 1;
	public static final int CARRO = 2;
}
