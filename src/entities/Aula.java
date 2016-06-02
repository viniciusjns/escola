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
public class Aula implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAula;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "horaInicio", nullable = false)
	private Date horaInicio;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "horaFim", nullable = false)
	private Date horaFim;
	
	@ManyToOne
	@JoinColumn(name = "idProfessorTurma")
	private ProfessorTurma professorTurma;
	
	@ManyToOne
	@JoinColumn(name = "idDiaSemana")
	private DiaSemana diaSemana;

	public int getIdAula() {
		return idAula;
	}

	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public ProfessorTurma getProfessorTurma() {
		return professorTurma;
	}

	public void setProfessorTurma(ProfessorTurma professorTurma) {
		this.professorTurma = professorTurma;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((diaSemana == null) ? 0 : diaSemana.hashCode());
		result = prime * result + ((horaFim == null) ? 0 : horaFim.hashCode());
		result = prime * result
				+ ((horaInicio == null) ? 0 : horaInicio.hashCode());
		result = prime * result + idAula;
		result = prime * result
				+ ((professorTurma == null) ? 0 : professorTurma.hashCode());
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
		Aula other = (Aula) obj;
		if (diaSemana == null) {
			if (other.diaSemana != null)
				return false;
		} else if (!diaSemana.equals(other.diaSemana))
			return false;
		if (horaFim == null) {
			if (other.horaFim != null)
				return false;
		} else if (!horaFim.equals(other.horaFim))
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		if (idAula != other.idAula)
			return false;
		if (professorTurma == null) {
			if (other.professorTurma != null)
				return false;
		} else if (!professorTurma.equals(other.professorTurma))
			return false;
		return true;
	}

}
