package RN;

import DAO.AtividadeDAO;
import entities.Atividade;

public class AtividadeRN {
	
	private AtividadeDAO atividadeDAO = new AtividadeDAO();
	
	public Atividade buscarPorId(int id) {
		
		return (Atividade) this.atividadeDAO.buscarPorId(id);
		
	}

}
