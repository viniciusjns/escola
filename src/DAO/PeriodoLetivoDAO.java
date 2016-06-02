package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.Disciplina;
import entities.PeriodoLetivo;

public class PeriodoLetivoDAO implements InterfaceDAO {

	private Session	session;
	private Transaction transaction;
	private MensagemUtil msg = new MensagemUtil();
	
	public PeriodoLetivoDAO() {
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
			this.msg.msgSucesso("Registro exclu�do com sucesso!");
		} catch(ConstraintViolationException ex) {
			this.msg.msgErro("Registro em movimenta��o. Imposs�vel excluir!");
			this.rollBack();
		} catch(Exception ex) {
			this.msg.msgErro("Erro ao excluir. Informe o administrador do sistema!" + ex);
			this.rollBack();
		}
		
	}

	@Override
	public Object buscarPorId(int id) {
		
		this.abrirSessao();
		String hql = "select p from PeriodoLetivo p where p.idPeriodoLetivo = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		PeriodoLetivo pl = (PeriodoLetivo) query.uniqueResult();
		this.comitarFecharSessao();
		
		return pl;
	}

	@Override
	public List<Object> buscarTodos() {
		
		this.abrirSessao();
		//return this.session.createCriteria(PeriodoLetivo.class).list();
		String hql = "select p from PeriodoLetivo p order by p.idPeriodoLetivo desc";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarPorIdAno(int idAno) {
		
		this.abrirSessao();
		String hql = "select p from PeriodoLetivo p where p.ano.idAno = :idAno";
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
