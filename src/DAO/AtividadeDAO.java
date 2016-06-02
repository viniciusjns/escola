package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.Atividade;

public class AtividadeDAO implements InterfaceDAO {
	
	private Session	session;
	private Transaction transaction;
	private MensagemUtil msg = new MensagemUtil();

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
		String hql = "select a from Atividade a where a.idAtividade = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		Atividade atividade = (Atividade) query.uniqueResult();
		this.comitarFecharSessao();
		
		return atividade;
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
	
	public void rollBack() {
		this.transaction.rollback();
	}

}
