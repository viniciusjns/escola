package RN;

import java.util.List;

import DAO.AtividadeComEntregaDAO;
import entities.AtividadeComEntrega;
import entities.AtividadeSemEntrega;

public class AtividadeComEntregaRN {
	
	private AtividadeComEntregaDAO atividadeDAO = new AtividadeComEntregaDAO();
	
	public void salvar(AtividadeComEntrega atividade) {
		
		Integer id = atividade.getIdAtividade();
		
		if(id == 0 || id == null) {
			
			this.atividadeDAO.salvar(atividade);
			
		} else {
			
			this.atividadeDAO.atualizar(atividade);
			
		}
		
	}
	
	public void excluir(AtividadeComEntrega atividade) {
		
		this.atividadeDAO.excluir(atividade);
		
	}
	
	public AtividadeComEntrega buscarPorId(int id) {
		
		return (AtividadeComEntrega) this.atividadeDAO.buscarPorId(id);
		
	}
	
	public List<AtividadeComEntrega> buscarTodos() {
		
		return (List) this.atividadeDAO.buscarTodos();
		
	}
	
	public List<AtividadeComEntrega> buscarAtividadesPorIdProfessor(int id) {
		
		return (List) this.atividadeDAO.buscarAtividadesPorIdProfessor(id);
		
	}
	
	public List<AtividadeComEntrega> buscarAtividadesEmAndamento(int idTurma, int idPeriodoLetivo) {
		
		return (List) this.atividadeDAO.buscarAtividadesEmAndamento(idTurma, idPeriodoLetivo);
		
	}
	
	public List<AtividadeComEntrega> buscarAtividadesEncerradas(int idTurma, int idPeriodoLetivo) {
		
		return (List) this.atividadeDAO.buscarAtividadesEncerradas(idTurma, idPeriodoLetivo);
		
	}
	
	public List<AtividadeComEntrega> buscarAtividadesPorIdProfessorIdTurmaIdPeriodoLetivo(int idProfessor, int idTurma, int idPeriodoLetivo) {
		
		return (List) this.atividadeDAO.buscarAtividadesPorIdProfessorIdTurmaIdPeriodoLetivo(idProfessor, idTurma, idPeriodoLetivo);
		
	}

}
