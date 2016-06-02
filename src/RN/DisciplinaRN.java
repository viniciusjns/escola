package RN;

import java.util.List;

import DAO.DisciplinaDAO;
import entities.Disciplina;
import util.DAOFactory;

public class DisciplinaRN {
	
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	
	public DisciplinaRN() {
		
		//this.disciplinaDAO = DAOFactory.criarDisciplinaDAO();
		
	}
	
	public void salvar(Disciplina disciplina) {
		
		Integer id = disciplina.getIdDisciplina();
		if(id == 0 || id == null) {
			
			this.disciplinaDAO.salvar(disciplina);
			
		} else {
			
			this.disciplinaDAO.atualizar(disciplina);
			
		}
		
	}
	
	public void excluir(Disciplina disciplina) {
		
		this.disciplinaDAO.excluir(disciplina);
		
	}
	
	public List<Disciplina> buscarTodos() {
		
		return (List) this.disciplinaDAO.buscarTodos();
		
	}
	
	public Disciplina buscarPorId(int id) {
		
		return this.disciplinaDAO.buscarPorId(id);
		
	}
	
	public List<Disciplina> buscarDisciplinaPorIdTurma(int idTurma) {
		
		return (List) this.disciplinaDAO.buscarDisciplinaPorIdTurma(idTurma);
		
	}

}
