package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entities.Aluno;
import entities.Endereco;
import entities.Sexo;
import entities.Telefone;
import entities.Uf;

public class EnderecoDAO implements InterfaceDAO {

	private Session	session;
	private Transaction transaction;
	
	public EnderecoDAO() {
		
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
		
		this.abrirSessao();
		this.session.delete(obj);
		this.comitarFecharSessao();
		
	}

	@Override
	public Object buscarPorId(int id) {
		
		this.abrirSessao();
		String hql = "select e from Endereco e where e.idEndereco = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		Endereco endereco = (Endereco) query.uniqueResult();
		this.comitarFecharSessao();
		
		return endereco;
	}

	@Override
	public List<Object> buscarTodos() {
		
		//return this.session.createCriteria(Endereco.class).list();
		this.abrirSessao();
		String hql = "select e from Endereco e";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	//	
	public Endereco buscarEnderecoPorId(int id) {
		
		this.abrirSessao();
		String hql = "select e from Endereco e where e.idEndereco = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		Endereco endereco = (Endereco) query.uniqueResult();
		this.comitarFecharSessao();
		
		return endereco;
		
	}
	
	public List<Uf> buscarUf() {
		
		//return this.session.createCriteria(Uf.class).list();
		this.abrirSessao();
		String hql = "select u from Uf u";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public Uf buscarUfPorId(int id) {
		
		this.abrirSessao();
		String hql = "select u from Uf u where u.idUf = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		Uf uf = (Uf) query.uniqueResult();
		this.comitarFecharSessao();
		
		return uf;
		
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
