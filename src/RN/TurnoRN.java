package RN;

import java.util.List;

import util.DAOFactory;
import DAO.TurnoDAO;
import entities.Turno;

public class TurnoRN {
	
	private TurnoDAO turnoDAO = new TurnoDAO();
	
	public TurnoRN() {
		
		//this.turnoDAO = DAOFactory.criarTurnoDAO();
		
	}
	
	public List<Turno> buscarTodos() {
		
		return (List) this.turnoDAO.buscarTodos();
		
	}
	
	public Turno buscarPorId(int id) {
		
		return (Turno) this.turnoDAO.buscarPorId(id);
		
	}

}
