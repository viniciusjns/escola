package model.disciplina;

import java.util.List;

import model.InterfaceDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.Disciplina;

public class DisciplinaDAO implements InterfaceDAO {

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
	public Disciplina buscarPorId(int id) {
		
		String hql = "select d from Disciplina d where d.idDisciplina = :id";
		Query query = this.session.createQuery(hql);
		query.setString(id, "id");
		
		return (Disciplina) query.uniqueResult();
	}

	@Override
	public List<Object> buscarTodos() {
		
		return this.session.createCriteria(Disciplina.class).list();
		
	}

}
