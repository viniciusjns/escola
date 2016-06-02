package RN;

import java.util.List;

import DAO.ProfessorTurmaDAO;
import entities.Disciplina;
import entities.Professor;
import entities.ProfessorDisciplina;
import entities.ProfessorTurma;
import entities.Turma;

public class ProfessorTurmaRN {
	
	private ProfessorTurmaDAO professorTurmaDAO = new ProfessorTurmaDAO();
	
	public void salvar(ProfessorTurma pt) {
		
		Integer id = pt.getIdProfessorTurma();
		
		if(id == 0 || id == null) {
			
			this.professorTurmaDAO.salvar(pt);
			
		} else {
			
			this.professorTurmaDAO.atualizar(pt);
			
		}
		
	}
	
	public void excluir(ProfessorTurma pt) {
		
		this.professorTurmaDAO.excluir(pt);
		
	}
	
	public ProfessorTurma buscarPorId(int id) {
		
		return (ProfessorTurma) this.professorTurmaDAO.buscarPorId(id);
		
	}
	
	public List<ProfessorTurma> buscarPorIdTurma(int id) {
		
		return (List) this.professorTurmaDAO.buscarPorIdTurma(id);
		
	}
	
	public List<Turma> buscarTurmasPorIdProfessorIdAno(int idPessoa, int idAno) {
		
		return (List) this.professorTurmaDAO.buscarTurmasPorIdProfessorIdAno(idPessoa, idAno);
		
	}
	
	public List<Disciplina> buscarDisciplinasProfessorTurma(int idProfessor, int idTurma) {
		
		return (List) this.professorTurmaDAO.buscarDisciplinasProfessorTurma(idProfessor, idTurma);
		
	}
	
	public List<Professor> buscarProfessorIdTurma(int idTurma) {
		
		return (List) this.professorTurmaDAO.buscarProfessorIdTurma(idTurma);
		
	}
	
	public ProfessorTurma buscarProfessorTurmaIdProfessorDisciplinaIdTurma(int idProfessorDisciplina, int idTurma) {
		
		return (ProfessorTurma) this.professorTurmaDAO.buscarProfessorTurmaIdProfessorDisciplinaIdTurma(idProfessorDisciplina, idTurma);
		
	}
	
	public List<ProfessorDisciplina> buscarProfessorDisciplinaIdTurma(int idTurma) {
		
		return (List) this.professorTurmaDAO.buscarProfessorDisciplinaIdTurma(idTurma);
		
	}
	
	public List<ProfessorDisciplina> buscarProfessorDisciplinaAtivoIdTurma(int idTurma) {
		
		return (List) this.professorTurmaDAO.buscarProfessorDisciplinaAtivoIdTurma(idTurma);
		
	}

}
