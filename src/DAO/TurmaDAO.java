package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.Turma;

public class TurmaDAO implements InterfaceDAO {
	
	private Session	session;
	private Transaction transaction;
	private MensagemUtil msg = new MensagemUtil();
	
	public TurmaDAO() {
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
	public Turma buscarPorId(int id) {
		
		this.abrirSessao();
		String hql = "select t from Turma t where t.idTurma = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		Turma turma = (Turma) query.uniqueResult();
		this.comitarFecharSessao();
		
		return turma;
	}

	@Override
	public List<Object> buscarTodos() {
		
		//return this.session.createCriteria(Turma.class).list();
		this.abrirSessao();
		String hql = "select t from Turma t order by t.idTurma desc";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarPorIdAno(int idAno) {
		
		this.abrirSessao();
		String hql = "select t from Turma t where t.ano.idAno = :idAno";
		Query query = this.session.createQuery(hql);
		query.setInteger("idAno", idAno);
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
	
	public Session getSession(){
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
		return this.session;
	}

}
