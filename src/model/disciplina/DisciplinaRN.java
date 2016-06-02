package model.disciplina;

import java.util.List;

import pojo.Disciplina;
import util.DAOFactory;

public class DisciplinaRN {
	
	private DisciplinaDAO disciplinaDAO;
	
	public DisciplinaRN() {
		
		this.disciplinaDAO = DAOFactory.criarDisciplinaDAO();
		
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

}
