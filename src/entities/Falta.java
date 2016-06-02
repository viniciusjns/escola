package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Falta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFalta;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "idAlunoturma")
	private AlunoTurma alunoTurma;

	@ManyToOne
	@JoinColumn(name = "idPeriodoLetivo")
	private PeriodoLetivo periodoLetivo;
	
	@ManyToOne
	@JoinColumn(name = "idAula")
	private Aula aula;

	public int getIdFalta() {
		return idFalta;
	}

	public void setIdFalta(int idFalta) {
		this.idFalta = idFalta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public AlunoTurma getAlunoTurma() {
		return alunoTurma;
	}

	public void setAlunoTurma(AlunoTurma alunoTurma) {
		this.alunoTurma = alunoTurma;
	}

	public PeriodoLetivo getPeriodoLetivo() {
		return periodoLetivo;
	}

	public void setPeriodoLetivo(PeriodoLetivo periodoLetivo) {
		this.periodoLetivo = periodoLetivo;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alunoTurma == null) ? 0 : alunoTurma.hashCode());
		result = prime * result + ((aula == null) ? 0 : aula.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + idFalta;
		result = prime * result
				+ ((periodoLetivo == null) ? 0 : periodoLetivo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Falta other = (Falta) obj;
		if (alunoTurma == null) {
			if (other.alunoTurma != null)
				return false;
		} else if (!alunoTurma.equals(other.alunoTurma))
			return false;
		if (aula == null) {
			if (other.aula != null)
				return false;
		} else if (!aula.equals(other.aula))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (idFalta != other.idFalta)
			return false;
		if (periodoLetivo == null) {
			if (other.periodoLetivo != null)
				return false;
		} else if (!periodoLetivo.equals(other.periodoLetivo))
			return false;
		return true;
	}

}
