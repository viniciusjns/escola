package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entities.Ano;
import entities.Turno;

public class AnoDAO implements InterfaceDAO {
	
	private Session	session;
	private Transaction transaction;
	
	public AnoDAO() {
		//this.session = HibernateUtil.getSessionFactory().openSession();
		//this.transaction = this.session.beginTransaction();
	}
	
	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object buscarPorId(int id) {
		
		this.abrirSessao();
		String hql = "select a from Ano a where a.idAno = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		Ano ano = (Ano) query.uniqueResult();
		this.comitarFecharSessao();
		
		return ano;
		
	}

	@Override
	public List<Object> buscarTodos() {
		
		//return this.session.createCriteria(Ano.class).list();
		this.abrirSessao();
		String hql = "select a from Ano a";
		Query query = this.session.createQuery(hql);
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
	
	public Session getSession(){
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
		return this.session;
	}

}
