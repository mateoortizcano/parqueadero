package co.ceiba.parqueadero.util;

public abstract class Constantes {
	
	private Constantes() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static final String NO_HAY_CELDAS_DISPONIBLES = "No hay celdas disponibles para parqueo.";
	public static final String VEHICULO_NO_PUEDE_INGRESAR = "El vehiculo no puede ingresar, los vehiculos con placas iniciadas en A"
			+ "solo pueden ser parqueados domingos o lunes.";
	public static final String OPERACION_EXITOSA = "Operacion Exitosa.";
	public static final String VEHICULO_YA_PARQUEADO = "El vehiculo ya se encuentra parqueado.";
}
