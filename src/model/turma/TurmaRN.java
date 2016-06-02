package model.turma;

import java.util.List;

import pojo.Turma;
import util.DAOFactory;

public class TurmaRN {
	
	private TurmaDAO turmaDAO;
	
	public TurmaRN() {
		
		this.turmaDAO = DAOFactory.criarTurmaDAO();
		
	}
	
	public void salvar(Turma turma) {
		
		Integer id = turma.getIdTurma();
		if(id == 0 || id == null) {
			
			this.turmaDAO.salvar(turma);
			
		} else {
			
			this.turmaDAO.atualizar(turma);
			
		}
		
	}
	
	public void excluir(Turma turma) {
		
		this.turmaDAO.excluir(turma);
		
	}
	
	public Turma buscarPorId(int id) {
		
		return this.turmaDAO.buscarPorId(id);
		
	}
	
	public List<Turma> buscarTodos() {
		
		return (List) this.turmaDAO.buscarTodos();
		
	}

}
