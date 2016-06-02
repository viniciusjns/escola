package RN;

import java.util.List;

import DAO.SerieDAO;
import entities.Serie;

public class SerieRN {
	
	private SerieDAO serieDAO = new SerieDAO();
	
	public void salvar(Serie serie) {
		
		Integer codigo = serie.getIdSerie();
		
		if(codigo == 0 || codigo == null) {
			
			this.serieDAO.salvar(serie);
			
		} else {
			
			this.serieDAO.atualizar(serie);
			
		}
		
	}
	
	public void excluir(Serie serie) {
		
		this.serieDAO.excluir(serie);
		
	}
	
	public Serie buscarPorId(int id) {
		
		return (Serie) this.serieDAO.buscarPorId(id);
		
	}
	
	public List<Serie> buscarTodos() {
		
		return (List) this.serieDAO.buscarTodos();
		
	}

}
