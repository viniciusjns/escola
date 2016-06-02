package RN;

import java.util.List;

import DAO.DiaSemanaDAO;
import entities.DiaSemana;

public class DiaSemanaRN {
	
	private DiaSemanaDAO diaSemanaDAO = new DiaSemanaDAO();
	
	public DiaSemana buscarPorId(int id) {
		
		return (DiaSemana) this.diaSemanaDAO.buscarPorId(id);
		
	}
	
	public List<DiaSemana> buscarTodos() {
		
		return (List) this.diaSemanaDAO.buscarTodos();
		
	}

}
