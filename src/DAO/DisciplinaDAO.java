package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.Disciplina;

public class DisciplinaDAO implements InterfaceDAO {

	private Session	session;
	private Transaction transaction;
	private MensagemUtil msg = new MensagemUtil();
	
	public DisciplinaDAO() {
		//this.session = HibernateUtil.getSessionFactory().openSession();
		//this.transaction = this.session.beginTransaction();
	}
	
	public void setSession(Session session) {
		
		this.session = session;
	
	}
	
	@Override
	public void salvar(Object obj) {
		
		this.abrirSessao();
		this.session.save(obj);
		this.comitarFecharSessao();
	}

	@Override
	public void atualizar(Object obj) {
		
		this.abrirSessao();
		this.session.update(obj);
		this.comitarFecharSessao();
	}

	@Override
	public void excluir(Object obj) {
		
		try {
			this.abrirSessao();
			this.session.delete(obj);
			this.comitarFecharSessao();
			this.msg.msgSucesso("Registro excluído com sucesso!");
		} catch(ConstraintViolationException ex) {
			this.msg.msgErro("Registro em movimentação. Impossível excluir!");
			this.rollBack();
		} catch(Exception ex) {
			this.msg.msgErro("Erro ao excluir. Informe o administrador do sistema!" + ex);
			this.rollBack();
		}
	}

	@Override
	public Disciplina buscarPorId(int id) {
		
		this.abrirSessao();
		String hql = "select d from Disciplina d where d.idDisciplina = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		Disciplina disciplina = (Disciplina) query.uniqueResult();
		this.comitarFecharSessao();
		
		return disciplina;
	}

	@Override
	public List<Object> buscarTodos() {
		
		//return this.session.createCriteria(Disciplina.class).list();
		this.abrirSessao();
		String hql = "select d from Disciplina d order by d.idDisciplina desc";
		Query query = this.session.createQuery(hql);
		List<Object> lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarDisciplinaPorIdTurma(int idTurma) {
		
		this.abrirSessao();
		String hql = "select pt.professorDisciplina.disciplina from ProfessorTurma pt where pt.turma.idTurma = :idTurma";
		Query query = this.session.createQuery(hql);
		query.setInteger("idTurma", idTurma);
		List<Object> lista = query.list();
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
	
	public Session getSession(){
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
		return this.session;
	}

}
