package co.ceiba.parqueadero.util;

public abstract class Tarifas {
	
	private Tarifas() {
	    throw new IllegalStateException("Utility class");
	 }
	
	public static final int VALOR_HORA_CARRO = 1000;
	public static final int VALOR_HORA_MOTO = 500;
	public static final int VALOR_DIA_CARRO = 8000;
	public static final int VALOR_DIA_MOTO = 4000;
	public static final int TARIFA_EXTRA_MOTO_CILINDRAJE_MAYOR_550CC = 2000;
}
