package model.aluno;

import java.util.List;

import model.InterfaceDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.Aluno;
import pojo.Sexo;
import pojo.Uf;

public class AlunoDAO implements InterfaceDAO {

	private Session	session;
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	@Override
	public void salvar(Object obj) {
		
		this.session.save(obj);
		
	}

	@Override
	public void atualizar(Object obj) {
		
		this.session.update(obj);
		
	}

	@Override
	public void excluir(Object obj) {
		
		this.session.delete(obj);
		
	}

	@Override
	public Object buscarPorId(int id) {
		
		String hql = "select a from Aluno a where a.id = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		
		return (Aluno) query.uniqueResult();
	}

	@Override
	public List<Object> buscarTodos() {
		
		return this.session.createCriteria(Aluno.class).list();
		
	}
	
	//
	public List<Sexo> buscarSexo() {
		
		return this.session.createCriteria(Sexo.class).list();
		
	}
	
	public Sexo buscarSexoPorId(int id) {
		
		String hql = "select s from Sexo s where s.idSexo = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		
		return (Sexo) query.uniqueResult();
		
	}
	
	public List<Uf> buscarUf() {
		
		return this.session.createCriteria(Uf.class).list();
		
	}
	
	public Uf buscarUfPorId(int id) {
		
		String hql = "select u from Uf u where u.idUf = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		
		return (Uf) query.uniqueResult();
		
	}

}
