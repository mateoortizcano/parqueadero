package co.ceiba.parqueadero.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "vehiculo")
public class VehiculoEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name =  "id")
	private int id;
	@Column(name =  "placa")
	private String placa;
	@Column(name =  "cilindraje")
	private int cilidraje;
	@Column(name =  "estadoParqueo")
	private boolean estadoParqueo;
	@Column(name =  "tipoVehiculo")
	private int tipoVehiculo;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getCilidraje() {
		return cilidraje;
	}

	public void setCilidraje(int cilidraje) {
		this.cilidraje = cilidraje;
	}

	public boolean isParqueado() {
		return estadoParqueo;
	}

	public void setParqueado(boolean parqueado) {
		this.estadoParqueo = parqueado;
	}

	public int getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(int tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
