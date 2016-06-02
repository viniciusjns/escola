package model.periodoLetivo;

import java.util.List;

import model.InterfaceDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.Disciplina;
import pojo.PeriodoLetivo;

public class PeriodoLetivoDAO implements InterfaceDAO {

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
	public PeriodoLetivo buscarPorId(int id) {
		
		String hql = "select p from PeriodoLetivo p where p.idPeriodoLetivo = :id";
		Query query = this.session.createQuery(hql);
		query.setString(id, "id");
		
		return (PeriodoLetivo) query.uniqueResult();
	}

	@Override
	public List<Object> buscarTodos() {
		
		return this.session.createCriteria(PeriodoLetivo.class).list();
		
	}

}
