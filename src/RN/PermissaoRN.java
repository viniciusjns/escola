package RN;

import util.DAOFactory;
import DAO.PermissaoDAO;
import entities.Permissao;

public class PermissaoRN {
	
	private PermissaoDAO permissaoDAO = new PermissaoDAO();
	
	public PermissaoRN() {
		
		//this.permissaoDAO = DAOFactory.criarPermissaoDAO();
		
	}
	
	public Permissao buscarPermissaoPorDescricao(String desc) {
		
		return (Permissao) this.permissaoDAO.buscarPorDescricao(desc);
		
	}

}
