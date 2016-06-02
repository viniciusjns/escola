package RN;

import java.util.Date;
import java.util.List;

import DAO.AulaDAO;
import entities.Aula;
import entities.DiaSemana;

public class AulaRN {
	
	AulaDAO aulaDAO = new AulaDAO();
	
	public void salvar(Aula aula) {
		
		Integer id = aula.getIdAula();
		
		if(id == 0 || id == null) {
			
			this.aulaDAO.salvar(aula);
			
		} else {
			
			this.aulaDAO.atualizar(aula);
			
		}
		
	}
	
	public void excluir(Aula aula) {
		
		this.aulaDAO.excluir(aula);
		
	}
	
	public Aula buscarPorId(int id) {
		
		return (Aula) this.aulaDAO.buscarPorId(id);
		
	}
	
	public Aula buscarAulaProfessorTurmaDiaSemanaHoraInicioHoraFim(int idProfessorTurma, int idDiaSemana, Date horaInicio, Date horaFim) {
		
		return (Aula) this.aulaDAO.buscarAulaProfessorTurmaDiaSemanaHoraInicioHoraFim(idProfessorTurma, idDiaSemana, horaInicio, horaFim);
		
	}
	
	public List<Aula> buscarTodos() {
		
		return (List) this.aulaDAO.buscarTodos();
		
	}
	
	public List<Aula> buscarAulaIdProfessorIdAnoIdTurmaIdDisciplina(int idProfessor, int idAno, int idTurma, int idDisciplina) {
		
		return (List) this.aulaDAO.buscarAulaIdProfessorIdAnoIdTurmaIdDisciplina(idProfessor, idAno, idTurma, idDisciplina);
		
	}

}
