package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProfessorTurma implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProfessorTurma;
	
	@ManyToOne
	@JoinColumn(name = "idProfessorDisciplina")
	private ProfessorDisciplina professorDisciplina;
	
	@ManyToOne
	@JoinColumn(name = "idTurma")
	private Turma turma;
	
	private boolean ativo;

	public int getIdProfessorTurma() {
		return idProfessorTurma;
	}

	public void setIdProfessorTurma(int idProfessorTurma) {
		this.idProfessorTurma = idProfessorTurma;
	}

	public ProfessorDisciplina getProfessorDisciplina() {
		return professorDisciplina;
	}

	public void setProfessorDisciplina(ProfessorDisciplina professorDisciplina) {
		this.professorDisciplina = professorDisciplina;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + idProfessorTurma;
		result = prime
				* result
				+ ((professorDisciplina == null) ? 0 : professorDisciplina
						.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
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
		ProfessorTurma other = (ProfessorTurma) obj;
		if (ativo != other.ativo)
			return false;
		if (idProfessorTurma != other.idProfessorTurma)
			return false;
		if (professorDisciplina == null) {
			if (other.professorDisciplina != null)
				return false;
		} else if (!professorDisciplina.equals(other.professorDisciplina))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}

}
