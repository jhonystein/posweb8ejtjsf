package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="reserva")
@NamedQueries({
	@NamedQuery(name="projetorDisponivel", query="select count(p) from Projetor as p where p not in(select r.projetor from Reserva as r where r.data = ?1 and r.campus = ?2 and r.horario = ?3)"),
	@NamedQuery(name="reservaEmAberto", query="select r from Reserva as r where r.data = ?1 and r.campus = ?2 and r.instalado = false"),
	@NamedQuery(name="projetoresReservados", query="select count(r) from Reserva as r where r.data = ?1 and r.campus = ?2 and r.horario = ?3"),
	@NamedQuery(name="projetoresCount", query="select count(p) from Projetor as p where p.campus = ?1") 
})
public class Reserva implements Serializable, IModelo {

	private static final long serialVersionUID = 376780444900013139L;

	@Id
	@GeneratedValue
	@Column(name="cd_reserva")
	private Long codigo;
	
	@Column(name="dt_reserva")
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	
	@Column(name="nr_horario")
	private int horario=1;
	
	@Column(name="nr_campus")
	private int campus=1;
	
	@Column(name="bool_instalado")
	private boolean instalado = false;
	
	@ManyToOne
	@JoinColumn(name="cd_projetor")
	private Projetor projetor;

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

	public void setProjetor(Projetor projetor) {
		this.projetor = projetor;
	}

	public Projetor getProjetor() {
		return projetor;
	}

	public void setInstalado(boolean instalado) {
		this.instalado = instalado;
	}

	public boolean isInstalado() {
		return instalado;
	}

}
