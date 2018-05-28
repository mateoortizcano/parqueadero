package co.ceiba.parqueadero.domain;

public class ValidacionCantidadVehiculos implements Validacion{
	
	private static final int NRO_MAXIMO_CELDAS_CARROS = 20;
	private static final int NRO_MAXIMO_CELDAS_MOTOS  = 10;
	 
	
	private int tipoVehiculo;
	private int nroVehiculosParqueados;
	
	public ValidacionCantidadVehiculos(int tipoVehiculo, int nroVehiculosParqueados) {
		super();
		this.tipoVehiculo = tipoVehiculo;
		this.nroVehiculosParqueados = nroVehiculosParqueados;
	}



	public boolean validar() {

		boolean ans = true;
		int nroMaxVehiculosParqueados;
		
		if(tipoVehiculo == TipoVehiculo.CARRO)
			nroMaxVehiculosParqueados = NRO_MAXIMO_CELDAS_CARROS;
		else if(tipoVehiculo == TipoVehiculo.MOTO)
			nroMaxVehiculosParqueados = NRO_MAXIMO_CELDAS_MOTOS;
		else
			nroMaxVehiculosParqueados = 0;
		
		if(nroVehiculosParqueados >= nroMaxVehiculosParqueados) {
			ans = false;
		}
		return ans;
	}
	
}
