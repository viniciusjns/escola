package model.turma;

import java.util.List;

import model.InterfaceDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.Turma;

public class TurmaDAO implements InterfaceDAO {
	
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
	public Turma buscarPorId(int id) {
		
		String hql = "select t from Turma t where t.idTurma = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		
		return (Turma) query.uniqueResult();
	}

	@Override
	public List<Object> buscarTodos() {
		
		return this.session.createCriteria(Turma.class).list();
		
	}

}
