package model.periodoLetivo;

import java.util.List;

import pojo.Disciplina;
import pojo.PeriodoLetivo;
import util.DAOFactory;

public class PeriodoLetivoRN {
	
	private PeriodoLetivoDAO periodoLetivoDAO;
	
	public PeriodoLetivoRN() {
		
		this.periodoLetivoDAO = DAOFactory.criarPeriodoLetivoDAO();
		
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
	
	public List<PeriodoLetivo> buscarTodos() {
		
		return (List) this.periodoLetivoDAO.buscarTodos();
		
	}

}
