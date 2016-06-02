package RN;

import java.util.List;

import util.DAOFactory;
import DAO.AnoDAO;
import entities.Ano;

public class AnoRN {
	
	private AnoDAO anoDAO = new AnoDAO();
	
	public AnoRN() {
		
		//this.anoDAO = DAOFactory.criarAnoDAO();
		
	}
	
	public List<Ano> buscarTodos() {
		
		return (List) this.anoDAO.buscarTodos();
		
	}
	
	public Ano buscarPorId(int id) {
		
		return (Ano) this.anoDAO.buscarPorId(id);
		
	}

}
