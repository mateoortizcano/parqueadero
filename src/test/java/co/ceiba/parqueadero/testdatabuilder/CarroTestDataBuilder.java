package co.ceiba.parqueadero.testdatabuilder;

import co.ceiba.parqueadero.domain.Carro;

public class CarroTestDataBuilder {
	
	private String placa;
	
	public CarroTestDataBuilder() {
		this.placa = "ATM046";
	}
	
	public CarroTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public Carro build() {
		return new Carro(this.placa);
	}
}
