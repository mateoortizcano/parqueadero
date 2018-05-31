package co.ceiba.parqueadero.util;

public abstract class Tarifas {
	
	private Tarifas() {
	    throw new IllegalStateException("Utility class");
	 }
	
	public final static int VALOR_HORA_CARRO = 1000;
	public final static int VALOR_HORA_MOTO = 500;
	public final static int VALOR_DIA_CARRO = 8000;
	public final static int VALOR_DIA_MOTO = 4000;
	public final static int TARIFA_EXTRA_MOTO_CILINDRAJE_MAYOR_550CC = 2000;
}
