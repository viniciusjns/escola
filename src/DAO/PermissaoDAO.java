package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entities.Permissao;

public class PermissaoDAO implements InterfaceDAO {
	
	private Session	session;
	private Transaction transaction;
	
	public PermissaoDAO() {
		
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
		String hql = "select p from Permissao p where p.idPermissao = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		Permissao permissao = (Permissao) query.uniqueResult();
		this.comitarFecharSessao();
		
		return permissao;
		
	}
	
	public Object buscarPorDescricao(String descricao) {
		
		this.abrirSessao();
		String hql = "select p from Permissao p where p.descricao = :descricao";
		Query query = this.session.createQuery(hql);
		query.setString("descricao", descricao);
		Permissao permissao = (Permissao) query.uniqueResult();
		this.comitarFecharSessao();
		
		return permissao;
		
	}

	@Override
	public List<Object> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
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
