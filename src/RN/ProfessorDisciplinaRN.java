package RN;

import java.util.List;

import DAO.ProfessorDisciplinaDAO;
import entities.Disciplina;
import entities.ProfessorDisciplina;

public class ProfessorDisciplinaRN {
	
	private ProfessorDisciplinaDAO professorDisciplinaDAO = new ProfessorDisciplinaDAO();
	
	public ProfessorDisciplinaRN() {
		
		//this.professorDisciplinaDAO = DAOFactory.criarProfessorDisciplinaDAO();
		
	}
	
	public void salvar(ProfessorDisciplina pd) {
		
		this.professorDisciplinaDAO.salvar(pd);
		
	}
	
	public void excluir(ProfessorDisciplina pd) {
		
		this.professorDisciplinaDAO.excluir(pd);
		
	}
	
	public List<ProfessorDisciplina> buscarProfessorDisciplinasPorIdProfessor(int id) {
		
		return (List) this.professorDisciplinaDAO.buscarProfessorDisciplinasPorIdProfessor(id);
		
	}
	
	public List<Disciplina> buscarDisciplinasPorIdProfessor(int id) {
		
		return (List) this.professorDisciplinaDAO.buscarDisciplinasPorIdProfessor(id);
		
	}
	
	public ProfessorDisciplina buscarProfessorDisciplinaPorIdProfessorDisciplina(int idProfessor, int idDisciplina) {
		
		return (ProfessorDisciplina) this.professorDisciplinaDAO.buscarProfessorDisciplinaPorIdProfessorDisciplina(idProfessor, idDisciplina);
		
	}

}
