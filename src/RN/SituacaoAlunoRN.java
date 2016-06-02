package RN;

import java.util.List;

import util.DAOFactory;
import DAO.SituacaoAlunoDAO;
import entities.SituacaoAluno;

public class SituacaoAlunoRN {
	
	private SituacaoAlunoDAO situacaoAlunoDAO = new SituacaoAlunoDAO();
	
	public SituacaoAlunoRN() {
		
		//this.situacaoAlunoDAO = DAOFactory.criarSituacaoAlunoDAO();
		
	}
	
	public SituacaoAluno buscarPorId(int id) {
		
		return (SituacaoAluno) this.situacaoAlunoDAO.buscarPorId(id);
		
	}
	
	public List<SituacaoAluno> buscarTodos() {
		
		return (List) this.situacaoAlunoDAO.buscarTodos();
		
	}

}
