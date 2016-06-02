package RN;

import java.util.List;

import util.DAOFactory;
import DAO.TelefoneDAO;
import entities.Telefone;

public class TelefoneRN {
	
	private TelefoneDAO telefoneDAO = new TelefoneDAO();
	
	public TelefoneRN() {
		
		//this.telefoneDAO = DAOFactory.criarTelefoneDAO();
		
	}
	
	public void salvar(Telefone telefone) {
		
		this.telefoneDAO.salvar(telefone);
		
	}

}
