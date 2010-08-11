package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
	@NamedQuery(name="temProjetorDisponivel", query="select count(p) from Projetor as p where p not in(select r.projetor from Reserva as r where r.data = ?1 and r.sala.campus = ?2 and r.horario = ?3)"),
	@NamedQuery(name="projetoresDisponiveis", query="select p from Projetor as p where p.campus = ?2 and p not in(select r.projetor from Reserva as r where r.data = ?1 and r.sala.campus = ?2 and r.horario = ?3)"),
	@NamedQuery(name="reservaEmAberto", query="select r from Reserva as r where r.data = ?1 and r.sala.campus = ?2 and r.instalado = false order by r.horario, r.sala.numeroSala, r.sala.bloco"),
	@NamedQuery(name="projetoresReservados", query="select count(r) from Reserva as r where r.data = ?1 and r.sala.campus = ?2 and r.horario = ?3"),
	@NamedQuery(name="projetoresCount", query="select count(p) from Projetor as p where p.campus = ?1") 
})
public class Reserva implements Serializable, IModelo {

	private static final long serialVersionUID = 376780444900013139L;

	@Id
	@GeneratedValue
	@Column(name="cd_reserva")
	private Long codigo = 0L;
	
	@Column(name="dt_reserva", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	
	@Column(name="nr_horario")
	private int horario=1;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cd_sala")
	private Sala sala = new Sala();
	
	@Column(name="bool_instalado")
	private boolean instalado = false;
	
	@ManyToOne(cascade=CascadeType.ALL)
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

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Sala getSala() {
		return sala;
	}

}
