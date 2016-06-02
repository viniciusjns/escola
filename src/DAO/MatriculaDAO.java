package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entities.Aluno;
import entities.Matricula;

public class MatriculaDAO implements InterfaceDAO {
	
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
		
		String hql = "select m from Matricula m where m.idMatricula = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		
		return (Matricula) query.uniqueResult();
	}

	@Override
	public List<Object> buscarTodos() {
		
		return this.session.createCriteria(Matricula.class).list();
		
	}

}
