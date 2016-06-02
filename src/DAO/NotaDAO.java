package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import util.MensagemUtil;

public class NotaDAO implements InterfaceDAO {
	
	private Session	session;
	private Transaction transaction;
	private MensagemUtil msg = new MensagemUtil();

	@Override
	public void salvar(Object obj) {
		
		try {
			this.abrirSessao();
			this.session.save(obj);
			this.comitarFecharSessao();
		} catch(Exception ex) {
			this.rollBack();
		}
		
	}

	@Override
	public void atualizar(Object obj) {
		
		this.abrirSessao();
		this.session.update(obj);
		this.comitarFecharSessao();
		
	}

	@Override
	public void excluir(Object obj) {
		
		this.abrirSessao();
		this.session.delete(obj);
		this.comitarFecharSessao();
		
	}

	@Override
	public Object buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Object> buscarPorIdAtividadeIdTurma(int idAtividade, int idTurma) {
		
		this.abrirSessao();
		String hql = "select n from Nota n where n.atividade.idAtividade = :idAtividade and n.alunoTurma.turma.idTurma = :idTurma and n.alunoTurma.ativo = 1";
		Query query = this.session.createQuery(hql);
		query.setInteger("idAtividade", idAtividade);
		query.setInteger("idTurma", idTurma);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	/*public List<Object> buscarPorIdTurmaIdDisciplinaIdPeriodo(int idTurma, int idDisciplina, int idPeriodo) {
		
		this.abrirSessao();
		String hql = "select n from Nota n where n n.alunoTurma.turma.idTurma = :idTurma and n.atividade.professorDisciplina.disciplina.idDisciplina = :idDisciplina and n.atividade.periodoLetivo.idPeriodoLetivo = :idPeriodo";
		Query query = this.session.createQuery(hql);
		query.setInteger("idTurma", idTurma);
		query.setInteger("idDisciplina", idDisciplina);
		query.setInteger("idPeriodo", idPeriodo);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}*/
	
	public List<Object> buscarPorIdAlunoIdTurmaIdPeriodo(int idPessoa, int idTurma, int idPeriodo) {
		
		this.abrirSessao();
		String hql = "select n from Nota n where n.alunoTurma.aluno.idPessoa = :idPessoa and n.alunoTurma.turma.idTurma = :idTurma and n.atividade.periodoLetivo.idPeriodoLetivo = :idPeriodo";
		Query query = this.session.createQuery(hql);
		query.setInteger("idPessoa", idPessoa);
		query.setInteger("idTurma", idTurma);
		query.setInteger("idPeriodo", idPeriodo);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarPorIdAlunoIdPeriodo(int idPessoa, int idPeriodo) {
		
		this.abrirSessao();
		String hql = "select n from Nota n where n.alunoTurma.aluno.idPessoa = :idPessoa and n.atividade.periodoLetivo.idPeriodoLetivo = :idPeriodo";
		Query query = this.session.createQuery(hql);
		query.setInteger("idPessoa", idPessoa);
		query.setInteger("idPeriodo", idPeriodo);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public void abrirSessao() {
		this.session = HibernateUtil.getSessionFactory().openSession();
		this.transaction = this.session.beginTransaction();
		//getSession().beginTransaction();
	}
	
	public void comitarFecharSessao() {
		this.transaction.commit();
		this.session.close();
		//getSession().getTransaction().commit();
	}
	
	public void rollBack() {
		this.transaction.rollback();
	}

}
