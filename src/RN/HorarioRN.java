package RN;

import java.util.List;

import DAO.HorarioDAO;
import entities.Horario;

public class HorarioRN {
	
	HorarioDAO horarioDAO = new HorarioDAO();
	
	public void salvar(Horario horario) {
		
		Integer id = horario.getIdHorario();
		
		if(id == 0 || id == null) {
			
			this.horarioDAO.salvar(horario);
			
		} else {
			
			this.horarioDAO.atualizar(horario);
			
		}
		
	}
	
	public void excluir(Horario horario) {
		
		this.horarioDAO.excluir(horario);
		
	}
	
	public Horario buscarPorId(int id) {
		
		return (Horario) this.horarioDAO.buscarPorId(id);
		
	}
	
	public List<Horario> buscarTodos() {
		
		return (List) this.horarioDAO.buscarTodos();
		
	}
	
	public List<Horario> buscarPorIdTurno(int idTurno) {
		
		return (List) this.horarioDAO.buscarPorIdTurno(idTurno);
		
	}

}
