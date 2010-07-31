package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="projetor")
public class Projetor implements Serializable, IModelo {

	private static final long serialVersionUID = 8394937598556375294L;

	@Id
	@GeneratedValue
	@Column(name="cd_projetor")
	private Long codigo;
	
	@Column(name="nr_patrimonio", unique=true)
	private String numeroPatrimonio;
	
	@Column(name="nr_campus")
	private int campus;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNumeroPatrimonio() {
		return numeroPatrimonio;
	}

	public void setNumeroPatrimonio(String numeroPatrimonio) {
		this.numeroPatrimonio = numeroPatrimonio;
	}

	public int getCampus() {
		return campus;
	}

	public void setCampus(int campus) {
		this.campus = campus;
	}
		
}
