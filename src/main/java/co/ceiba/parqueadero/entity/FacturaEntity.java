package co.ceiba.parqueadero.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "factura")
public class FacturaEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
	private VehiculoEntity vehiculoEntity;
	
	@Column(name = "fecha_ingreso")
	private Calendar fechaIngreso;
	
	@Column(name = "fecha_salida")
	private Calendar fechaSalida;
	
	@Column(name = "precio_total_parqueo")
	private int precioTotalParqueo;
	
	public VehiculoEntity getVehiculoEntity() {
		return vehiculoEntity;
	}
	
	public void setVehiculoEntity(VehiculoEntity vehiculoEntity) {
		this.vehiculoEntity = vehiculoEntity;
	}
	
	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}
	
	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public Calendar getFechaSalida() {
		return fechaSalida;
	}
	
	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getPrecioTotalParqueo() {
		return precioTotalParqueo;
	}

	public void setPrecioTotalParqueo(int precioTotalParqueo) {
		this.precioTotalParqueo = precioTotalParqueo;
	}

	public void setId(int id) {
		this.id = id;
		
	}

	public int getId() {
		return this.id;
	}
	
}
