package RN;

import java.util.List;

import util.MensagemUtil;
import DAO.NotaDAO;
import entities.Nota;

public class NotaRN {
	
	private NotaDAO notaDAO = new NotaDAO();
	private MensagemUtil msg = new MensagemUtil();
	
	public void salvar(Nota nota) {
		
		Integer id = nota.getIdNota();
		
		/*if(nota.getValor() > nota.getAtividade().getValor())
			this.msg.msgErro("A nota postada para o aluno " + nota.getAlunoTurma().getAluno().getNome() + " é maior que o valor da atividade!");
		*/
		if(id == 0 || id == null) {
			
			this.notaDAO.salvar(nota);
			
		} else {
			
			this.notaDAO.atualizar(nota);
			
		}
		
	}
	
	public void excluir(Nota nota) {
		
		this.notaDAO.excluir(nota);
		
	}
	
	public List<Nota> buscarPorIdAtividadeIdTurma(int idAtividade, int idTurma) {
		
		return (List) this.notaDAO.buscarPorIdAtividadeIdTurma(idAtividade, idTurma);
		
	}
	
	/*public List<Nota> buscarPorIdTurmaIdDisciplinaIdPeriodo(int idTurma, int idDisciplina, int idPeriodo) {
		
		return (List) this.notaDAO.buscarPorIdTurmaIdDisciplinaIdPeriodo(idTurma, idDisciplina, idPeriodo);
		
	}*/
	
	public List<Nota> buscarPorIdAlunoIdTurmaIdPeriodo(int idPessoa, int idTurma, int idPeriodo) {
		
		return (List) this.notaDAO.buscarPorIdAlunoIdTurmaIdPeriodo(idPessoa, idTurma, idPeriodo);
		
	}
	
	public List<Nota> buscarPorIdAlunoIdPeriodo(int idPessoa, int idPeriodo) {
		
		return (List) this.notaDAO.buscarPorIdAlunoIdPeriodo(idPessoa, idPeriodo);
		
	}

}
