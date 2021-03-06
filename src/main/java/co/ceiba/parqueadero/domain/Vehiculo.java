package co.ceiba.parqueadero.domain;

public class Vehiculo {
	
	private int id;
	private String placa;
	private boolean estadoParqueo;
	private int tipo;
	private int cilindraje;

	public Vehiculo() {
		super();
	}
	
	public Vehiculo(String placa, int tipo) {
		super();
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje = 0;
	}
	
	public String getPlaca() {
		return this.placa;
	}
	
	public int getTipo() {
		return this.tipo;
	}

	public boolean isParqueado() {
		return estadoParqueo;
	}

	public void setEstadoParqueo(boolean estadoParqueo) {
		this.estadoParqueo = estadoParqueo;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
