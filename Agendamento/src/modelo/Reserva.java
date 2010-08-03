package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reserva")
public class Reserva implements Serializable, IModelo {

	private static final long serialVersionUID = 376780444900013139L;

	@Id
	@GeneratedValue
	@Column(name="cd_reserva")
	private Long codigo;
	
	@Column(name="dt_reserva")
	private Date data;
	
	@Column(name="nr_horario")
	private int horario=1;
	
	@Column(name="nr_campus")
	private int campus=1;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getHorario() {
		return horario;
	}

	public void setHorario(int horario) {
		this.horario = horario;
	}

	public int getCampus() {
		return campus;
	}

	public void setCampus(int campus) {
		this.campus = campus;
	}

}
