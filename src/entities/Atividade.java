package entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Atividade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAtividade;
	
	@Column(name = "titulo", nullable = false, length =50)
	private String titulo;
	
	@Column(name = "valor", nullable = false)
	private float valor;
	
	@ManyToOne
	@JoinColumn(name = "idProfessorTurma")
	private ProfessorTurma professorTurma;

	@ManyToOne
	@JoinColumn(name = "idPeriodoLetivo")
	private PeriodoLetivo periodoLetivo;

	public int getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(int idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public ProfessorTurma getProfessorTurma() {
		return professorTurma;
	}

	public void setProfessorTurma(ProfessorTurma professorTurma) {
		this.professorTurma = professorTurma;
	}

	public PeriodoLetivo getPeriodoLetivo() {
		return periodoLetivo;
	}

	public void setPeriodoLetivo(PeriodoLetivo periodoLetivo) {
		this.periodoLetivo = periodoLetivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAtividade;
		result = prime * result
				+ ((periodoLetivo == null) ? 0 : periodoLetivo.hashCode());
		result = prime * result
				+ ((professorTurma == null) ? 0 : professorTurma.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + Float.floatToIntBits(valor);
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
		Atividade other = (Atividade) obj;
		if (idAtividade != other.idAtividade)
			return false;
		if (periodoLetivo == null) {
			if (other.periodoLetivo != null)
				return false;
		} else if (!periodoLetivo.equals(other.periodoLetivo))
			return false;
		if (professorTurma == null) {
			if (other.professorTurma != null)
				return false;
		} else if (!professorTurma.equals(other.professorTurma))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (Float.floatToIntBits(valor) != Float.floatToIntBits(other.valor))
			return false;
		return true;
	}

	
}
