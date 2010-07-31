package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="sala")
@NamedQuery(name="salaCampus", query="select count(s) from Sala as s where s.campus = ?1 and s.bloco = ?2 and s.numeroSala = ?3 and s.codigo <> 4?")
public class Sala implements Serializable, IModelo {

	private static final long serialVersionUID = 8984474499580892379L;

	@Id
	@GeneratedValue
	@Column(name="cd_sala")
	private Long codigo;
	
	@Column(name="nr_campus")
	private int campus;
	
	@Column(name="nr_bloco")
	private String bloco;
	
	@Column(name="nr_sala")
	private int numeroSala;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public int getCampus() {
		return campus;
	}

	public void setCampus(int campus) {
		this.campus = campus;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public int getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}
		
}
