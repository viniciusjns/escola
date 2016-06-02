package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.Entrega;

public class EntregaDAO implements InterfaceDAO {

	private Session	session;
	private Transaction transaction;
	private MensagemUtil msg = new MensagemUtil();
	
	@Override
	public void salvar(Object obj) {
		
		try {
			this.abrirSessao();
			this.session.save(obj);
			this.comitarFecharSessao();
		} catch(Exception ex) {
			this.rollBack();
			this.msg.msgErro("Erro ao inserir. Informe o administrador do sistema!" + ex);
		}
		
	}
	@Override
	public void atualizar(Object obj) {
		
		try {
			this.abrirSessao();
			this.session.update(obj);
			this.comitarFecharSessao();
		} catch(Exception ex) {
			this.rollBack();
			this.msg.msgErro("Erro ao inserir. Informe o administrador do sistema!" + ex);
		}
		
	}
	@Override
	public void excluir(Object obj) {
		
		try {
			this.abrirSessao();
			this.session.delete(obj);
			this.comitarFecharSessao();
			this.msg.msgSucesso("Registro excluído com sucesso!");
		} catch(ConstraintViolationException ex) {
			this.msg.msgErro("Registro em movimentação. Impossível excluir!");
			this.rollBack();
		} catch(Exception ex) {
			this.msg.msgErro("Erro ao excluir. Informe o administrador do sistema!" + ex);
			this.rollBack();
		}
		
	}
	@Override
	public Object buscarPorId(int id) {
		
		this.abrirSessao();
		String hql = "select e from Entrega e where e.idEntrega = :idEntrega";
		Query query = this.session.createQuery(hql);
		query.setInteger("idEntrega", id);
		Entrega entrega = (Entrega) query.uniqueResult();
		this.comitarFecharSessao();
		
		return entrega;
	}
	@Override
	public List<Object> buscarTodos() {
		
		this.abrirSessao();
		String hql = "select e from Entrega e";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public Object buscarEntregaPorIdAluno(int idAluno) {
		
		this.abrirSessao();
		String hql = "select e from Entrega e where e.aluno.idPessoa = :idAluno";
		Query query = this.session.createQuery(hql);
		query.setInteger("idAluno", idAluno);
		Entrega entrega = (Entrega) query.uniqueResult();
		this.comitarFecharSessao();
		
		return entrega;
		
	}
	
	public List<Object> buscarEntregasPorIdAtividade(int idAtividade) {
		
		this.abrirSessao();
		String hql = "select e from Entrega e where e.atividadeComEntrega.idAtividade = :idAtividade";
		Query query = this.session.createQuery(hql);
		query.setInteger("idAtividade", idAtividade);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public Object buscarEntregaPorIdAlunoIdAtividade(int idPessoa, int idAtividade) {
		
		this.abrirSessao();
		String hql = "select e from Entrega e where e.aluno.idPessoa = :idPessoa and e.atividadeComEntrega.idAtividade = :idAtividade";
		Query query = this.session.createQuery(hql);
		query.setInteger("idPessoa", idPessoa);
		query.setInteger("idAtividade", idAtividade);
		Entrega entrega = (Entrega) query.uniqueResult();
		this.comitarFecharSessao();
		
		return entrega;
		
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
	
	public Session getSession(){
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
		return this.session;
	}

}
