package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.ProfessorDisciplina;

public class ProfessorDisciplinaDAO implements InterfaceDAO {
	
	private Session	session;
	private Transaction transaction;
	private MensagemUtil msg = new MensagemUtil();
	
	public ProfessorDisciplinaDAO() {
		//this.session = HibernateUtil.getSessionFactory().openSession();
		//this.transaction = this.session.beginTransaction();
	}
	
	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Object obj) {
		
		try {
			this.abrirSessao();
			this.session.save(obj);
			this.comitarFecharSessao();
			this.msg.msgSucesso("Registro inserido com sucesso!");
		} catch(Exception ex) {
			this.rollBack();
			this.msg.msgErro("Erro ao excluir. Informe o administrador do sistema!" + ex);
		}
	}

	@Override
	public void atualizar(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Object obj) {
		
		try {
			this.abrirSessao();
			this.session.delete(obj);
			this.comitarFecharSessao();
			this.msg.msgSucesso("Registro excluído com sucesso!");
		} catch(ConstraintViolationException ex) {
			this.rollBack();
			this.msg.msgErro("Registro em movimentação. Impossível excluir!");
		} catch (Exception e) {
			this.rollBack();
			this.msg.msgErro("Erro ao excluir. Informe o administrador do sistema!" + e);
		}
	}

	@Override
	public Object buscarPorId(int id) {

		return null;
	}

	@Override
	public List<Object> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Object> buscarProfessorDisciplinasPorIdProfessor(int id) {
		
		this.abrirSessao();
		String hql = "select pd from ProfessorDisciplina pd where pd.professor.idPessoa = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarDisciplinasPorIdProfessor(int id) {
		
		this.abrirSessao();
		String hql = "select pd.disciplina from ProfessorDisciplina pd where pd.professor.idPessoa = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public Object buscarProfessorDisciplinaPorIdProfessorDisciplina(int idProfessor, int idDisciplina) {
		
		this.abrirSessao();
		String hql = "select pd from ProfessorDisciplina pd where pd.professor.idPessoa = :idProfessor and pd.disciplina.idDisciplina = :idDisciplina";
		Query query = this.session.createQuery(hql);
		query.setInteger("idProfessor", idProfessor);
		query.setInteger("idDisciplina", idDisciplina);
		ProfessorDisciplina pd = (ProfessorDisciplina) query.uniqueResult();
		this.comitarFecharSessao();
		
		return pd;
		
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
