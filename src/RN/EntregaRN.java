package RN;

import java.util.List;

import DAO.EntregaDAO;
import entities.Entrega;

public class EntregaRN {
	
private EntregaDAO entregaDAO = new EntregaDAO();
	
	public EntregaRN() {
		
		//this.disciplinaDAO = DAOFactory.criarDisciplinaDAO();
		
	}
	
	public void salvar(Entrega entrega) {
		
		Integer id = entrega.getIdEntrega();
		if(id == 0 || id == null) {
			
			this.entregaDAO.salvar(entrega);
			
		} else {
			
			this.entregaDAO.atualizar(entrega);
			
		}
		
	}
	
	public void excluir(Entrega entrega) {
		
		this.entregaDAO.excluir(entrega);
		
	}
	
	public List<Entrega> buscarTodos() {
		
		return (List) this.entregaDAO.buscarTodos();
		
	}
	
	public Entrega buscarPorId(int id) {
		
		return (Entrega) this.entregaDAO.buscarPorId(id);
		
	}
	
	public Entrega buscarEntregaPorIdAluno(int idAluno) {
		
		return (Entrega) this.entregaDAO.buscarEntregaPorIdAluno(idAluno);
		
	}
	
	public List<Entrega> buscarEntregasPorIdAtividade(int idAtividade) {
		
		return (List) this.entregaDAO.buscarEntregasPorIdAtividade(idAtividade);
		
	}
	
	public Entrega buscarEntregaPorIdAlunoIdAtividade(int idPessoa, int idAtividade) {
		
		return (Entrega) this.entregaDAO.buscarEntregaPorIdAlunoIdAtividade(idPessoa, idAtividade);
		
	}

}
