package co.ceiba.parqueadero.testdatabuilder;

import co.ceiba.parqueadero.domain.Carro;

public class CarroTestDataBuilder {

	private String placa;
	private boolean estadoParqueo;

	public CarroTestDataBuilder() {
		this.placa = "ATM046";
		this.estadoParqueo = false;
	}

	public CarroTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public Carro build() {
		Carro carro = new Carro(this.placa);
		carro.setEstadoParqueo(this.estadoParqueo);
		return new Carro(this.placa);
	}

	public CarroTestDataBuilder withEstadoParqueo(boolean estadoParqueo) {
		this.estadoParqueo = estadoParqueo;
		return this;
	}
}