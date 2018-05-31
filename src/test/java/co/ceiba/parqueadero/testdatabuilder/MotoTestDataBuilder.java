package co.ceiba.parqueadero.testdatabuilder;

import co.ceiba.parqueadero.domain.Moto;

public class MotoTestDataBuilder {
	
	private String placa;
	private int cilindraje;
	private boolean estadoParqueo;
	
	public MotoTestDataBuilder() {
		this.placa = "DTM435";
		this.cilindraje = 499;
		this.estadoParqueo = false;
	}
	
	public MotoTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public MotoTestDataBuilder withCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public MotoTestDataBuilder withEstadoParqueo(boolean estadoParqueo) {
		this.estadoParqueo = estadoParqueo;
		return this;
	}
	
	public Moto build() {
		Moto moto = new Moto(this.placa, this.cilindraje);
		moto.setEstadoParqueo(this.estadoParqueo);
		return moto;
	}
}
