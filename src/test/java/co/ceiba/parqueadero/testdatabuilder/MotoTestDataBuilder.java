package co.ceiba.parqueadero.testdatabuilder;

import co.ceiba.parqueadero.domain.Moto;

public class MotoTestDataBuilder {
	
	private String placa;
	private int cilindraje;
	
	public MotoTestDataBuilder() {
		this.placa = "DTM435";
		this.cilindraje = 499;
	}
	
	public MotoTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public MotoTestDataBuilder withCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public Moto build() {
		return new Moto(this.placa, this.cilindraje);
	}
}
