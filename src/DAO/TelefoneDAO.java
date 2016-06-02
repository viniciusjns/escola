package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class TelefoneDAO implements InterfaceDAO {

	private Session	session;
	private Transaction transaction;
	
	public TelefoneDAO() {
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Object obj) {
		// TODO Auto-generated method stub
		
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
	
	public List<Object> buscarTelefonePorIdPessoa(int id) {
		
		this.abrirSessao();
		String hql = "select t from Telefone t where t.idPessoa = :id";
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
