package entities;

import java.io.Serializable;
import java.util.List;

public class ProfessorDisciplinaNota implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProfessorDisciplina professorDisciplina;
	
	private List<Nota> notas;

	public ProfessorDisciplina getProfessorDisciplina() {
		return professorDisciplina;
	}

	public void setProfessorDisciplina(ProfessorDisciplina professorDisciplina) {
		this.professorDisciplina = professorDisciplina;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notas == null) ? 0 : notas.hashCode());
		result = prime
				* result
				+ ((professorDisciplina == null) ? 0 : professorDisciplina
						.hashCode());
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
		ProfessorDisciplinaNota other = (ProfessorDisciplinaNota) obj;
		if (notas == null) {
			if (other.notas != null)
				return false;
		} else if (!notas.equals(other.notas))
			return false;
		if (professorDisciplina == null) {
			if (other.professorDisciplina != null)
				return false;
		} else if (!professorDisciplina.equals(other.professorDisciplina))
			return false;
		return true;
	}	

}
