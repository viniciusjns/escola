package RN;

import java.util.List;

import DAO.AlunoTurmaDAO;
import entities.Aluno;
import entities.AlunoTurma;
import entities.Turma;

public class AlunoTurmaRN {
	
private AlunoTurmaDAO alunoTurmaDAO = new AlunoTurmaDAO();
	
	public AlunoTurmaRN() {
		
		//this.alunoTurmaDAO = DAOFactory.criarAlunoTurmaDAO();
		
	}
	
	public void salvar(AlunoTurma alunoTurma) {
		
		Integer idAlunoTurma = alunoTurma.getIdAlunoTurma();
		
		if((idAlunoTurma == 0 || idAlunoTurma == null)) {
					
			this.alunoTurmaDAO.salvar(alunoTurma);
			
		} else {
			
			this.alunoTurmaDAO.atualizar(alunoTurma);
			
		}
		
	}
	
	public void excluir(AlunoTurma alunoTurma) {
		
		this.alunoTurmaDAO.excluir(alunoTurma);
		
	}
	
	public void excluirPorId(int id) {
		
		AlunoTurma at = this.buscarPorId(id);
		this.alunoTurmaDAO.excluir(at);
		
	}
	
	public AlunoTurma buscarPorId(int id) {
		
		return (AlunoTurma) this.alunoTurmaDAO.buscarPorId(id);
		
	}
	
	public List<AlunoTurma> buscarTodos() {
		
		return (List) this.alunoTurmaDAO.buscarTodos();
		
	}
	
	public List<Aluno> buscarAlunosNaoCadastrados(int id) {
		
		return (List) this.alunoTurmaDAO.buscarAlunosNaoCadastrados(id);
		
	}
	
	public List<Aluno> buscarAlunosPorIdTurma(int id) {
	
		return (List) this.alunoTurmaDAO.buscarAlunosPorIdTurma(id);
	
	}
	
	public AlunoTurma buscarAlunoTurmaPorIdAlunoTurma(int idAluno, int idTurma) {
		
		return this.alunoTurmaDAO.buscarAlunoTurmaPorIdAlunoTurma(idAluno, idTurma);
		
	}
	
	public List<Turma> buscarTurmasPorIdAlunoIdAno(int idPessoa, int idAno) {
		
		return (List) this.alunoTurmaDAO.buscarTurmasPorIdAlunoIdAno(idPessoa, idAno);
		
	}
	
	public List<AlunoTurma> buscarAlunoTurmaPorIdTurma(int idTurma) {
		
		return (List) this.alunoTurmaDAO.buscarAlunoTurmaPorIdTurma(idTurma);
	}
	
	public List<AlunoTurma> buscarAlunoTurmaPorIdTurmaAtivo(int idTurma) {
		
		return (List) this.alunoTurmaDAO.buscarAlunoTurmaPorIdTurmaAtivo(idTurma);
		
	}
	
	public List<Aluno> buscarAlunoAtivoPorIdTurma(int idTurma) {
		
		return (List) this.alunoTurmaDAO.buscarAlunoAtivoPorIdTurma(idTurma);
		
	}

}
