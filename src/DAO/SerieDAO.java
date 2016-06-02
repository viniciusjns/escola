package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entities.Serie;

public class SerieDAO implements InterfaceDAO {
	
	private Session	session;
	private Transaction transaction;

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
		
		this.abrirSessao();
		this.session.delete(obj);
		this.comitarFecharSessao();
		
	}

	@Override
	public Object buscarPorId(int id) {
		
		this.abrirSessao();
		String hql = "select s from Serie s where s.idSerie = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		Serie serie = (Serie) query.uniqueResult();
		this.comitarFecharSessao();
		
		return serie;
	}

	@Override
	public List<Object> buscarTodos() {
		
		//return this.session.createCriteria(Aluno.class).list();
		this.abrirSessao();
		String hql = "select s from Serie s";
		Query query = this.session.createQuery(hql);
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

}
