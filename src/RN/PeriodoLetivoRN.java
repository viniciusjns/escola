package RN;

import java.util.List;

import DAO.PeriodoLetivoDAO;
import entities.Disciplina;
import entities.PeriodoLetivo;
import util.DAOFactory;

public class PeriodoLetivoRN {
	
	private PeriodoLetivoDAO periodoLetivoDAO = new PeriodoLetivoDAO();
	
	public PeriodoLetivoRN() {
		
		//this.periodoLetivoDAO = DAOFactory.criarPeriodoLetivoDAO();
		
	}
	
	public void salvar(PeriodoLetivo periodoLetivo) {
		
		Integer id = periodoLetivo.getIdPeriodoLetivo();
		if(id == 0 || id == null) {
			
			this.periodoLetivoDAO.salvar(periodoLetivo);
			
		} else {
			
			this.periodoLetivoDAO.atualizar(periodoLetivo);
			
		}
		
	}
	
	public void excluir(PeriodoLetivo periodoLetivo) {
		
		this.periodoLetivoDAO.excluir(periodoLetivo);
		
	}
	
	public PeriodoLetivo buscarPorId(int id) {
		
		return (PeriodoLetivo) this.periodoLetivoDAO.buscarPorId(id);
		
	}
	
	public List<PeriodoLetivo> buscarTodos() {
		
		return (List) this.periodoLetivoDAO.buscarTodos();
		
	}
	
	public List<PeriodoLetivo> buscarPorIdAno(int idAno) {
		
		return (List) this.periodoLetivoDAO.buscarPorIdAno(idAno);
		
	}

}
