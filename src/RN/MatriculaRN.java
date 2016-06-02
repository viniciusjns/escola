package RN;

import java.util.List;

import util.DAOFactory;
import DAO.MatriculaDAO;
import entities.Matricula;

public class MatriculaRN {
	
	private MatriculaDAO matriculaDAO;
	
	public MatriculaRN() {
		
		this.matriculaDAO = DAOFactory.criarMatriculaDAO();
		
	}
	
	public void salvar(Matricula matricula) {
		
		Integer id = matricula.getIdMatricula();
		if(id == 0 || id == null) {
			
			this.matriculaDAO.salvar(matricula);
			
		} else {
			
			this.matriculaDAO.atualizar(matricula);
			
		}
		
	}
	
	public void excluir(Matricula matricula) {
		
		this.matriculaDAO.excluir(matricula);
		
	}
	
	public Matricula buscarPorId(int id) {
		
		return (Matricula) this.matriculaDAO.buscarPorId(id);
		
	}
	
	public List<Matricula> buscarTodos() {
		
		return (List) this.matriculaDAO.buscarTodos();
		
	}

}
