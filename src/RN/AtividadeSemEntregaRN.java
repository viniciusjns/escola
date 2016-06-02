package RN;

import java.util.List;

import DAO.AtividadeSemEntregaDAO;
import entities.AtividadeSemEntrega;

public class AtividadeSemEntregaRN {
	
	private AtividadeSemEntregaDAO atividadeDAO = new AtividadeSemEntregaDAO();
	
	public void salvar(AtividadeSemEntrega atividade) {
		
		Integer id = atividade.getIdAtividade();
		
		if(id == 0 || id == null) {
			
			this.atividadeDAO.salvar(atividade);
			
		} else {
			
			this.atividadeDAO.atualizar(atividade);
			
		}
		
	}
	
	public void excluir(AtividadeSemEntrega atividade) {
		
		this.atividadeDAO.excluir(atividade);
		
	}
	
	public AtividadeSemEntrega buscarPorId(int id) {
		
		return (AtividadeSemEntrega) this.atividadeDAO.buscarPorId(id);
		
	}
	
	public List<AtividadeSemEntrega> buscarTodos() {
		
		return (List) this.atividadeDAO.buscarTodos();
		
	}
	
	public List<AtividadeSemEntrega> buscarAtividadesPorIdProfessor(int id) {
		
		return (List) this.atividadeDAO.buscarAtividadesPorIdProfessor(id);
		
	}
	
	public List<AtividadeSemEntrega> buscarAtividadesPorIdProfessorIdTurmaIdPeriodoLetivo(int idProfessor, int idTurma, int idPeriodoLetivo) {
		
		return (List) this.atividadeDAO.buscarAtividadesPorIdProfessorIdTurmaIdPeriodoLetivo(idProfessor, idTurma, idPeriodoLetivo);
		
	}

}
