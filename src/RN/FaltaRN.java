package RN;

import java.util.Date;
import java.util.List;

import DAO.FaltaDAO;
import entities.Aluno;
import entities.Falta;

public class FaltaRN {
	
	private FaltaDAO faltaDAO = new FaltaDAO();
	
	public void salvar(Falta falta) {
		
		Integer id = falta.getIdFalta();
		
		if(id == 0 || id == null) {
			
			this.faltaDAO.salvar(falta);
			
		} else {
			
			this.faltaDAO.atualizar(falta);
			
		}
		
	}
	
	public void excluir(Falta falta) {
		
		this.faltaDAO.excluir(falta);
		
	}
	
	public Falta buscarPorId(int id) {
		
		return (Falta) this.faltaDAO.buscarPorId(id);
		
	}
	
	public List<Falta> buscarTodos() {
		
		return (List) this.faltaDAO.buscarTodos();
		
	}
	
	public List<Falta> buscarAlunosComFaltas(int idTurma, int idAula, int idPeriodoLetivo, Date data) {
		
		return (List) this.faltaDAO.buscarAlunosComFaltas(idTurma, idAula, idPeriodoLetivo, data);
		
	}
	
	public List<Aluno> buscarAlunosSemFaltas(int idTurma, int idAula, int idPeriodoLetivo, Date data) {
		
		return (List) this.faltaDAO.buscarAlunosSemFaltas(idTurma, idAula, idPeriodoLetivo, data);
		
	}
	
	public List<Falta> buscarFaltasPorPeriodoAlunoTurma(int idPeriodoLetivo, int idAluno, int idTurma) {
		
		return (List) this.faltaDAO.buscarFaltasPorPeriodoAlunoTurma(idPeriodoLetivo, idAluno, idTurma);
		
	}
	
	public List<Falta> buscarQuantidadeFaltas(int idAluno, int idAula) {
		
		return (List) this.faltaDAO.buscarQuantidadeFaltas(idAluno, idAula);
		
	}

}
