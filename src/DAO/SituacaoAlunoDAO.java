package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entities.Disciplina;
import entities.SituacaoAluno;

public class SituacaoAlunoDAO implements InterfaceDAO {
	
	private Session	session;
	
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
		
		String hql = "select s from SituacaoAluno s where s.idSituacaoAluno = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		
		return (SituacaoAluno) query.uniqueResult();
	}

	@Override
	public List<Object> buscarTodos() {
		
		return this.session.createCriteria(SituacaoAluno.class).list();
		
	}

}
