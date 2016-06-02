package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.Usuario;

public class UsuarioDAO implements InterfaceDAO {
	
	private Session	session;
	private Transaction transaction;
	private MensagemUtil msg = new MensagemUtil();

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> buscarTodos() {
		
		this.abrirSessao();
		String hql = "select u from Usuario u";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarPorIdPessoa(int idPessoa) {
		
		this.abrirSessao();
		String hql = "select u from Usuario u where u.pessoa.idPessoa = :idPessoa";
		Query query = this.session.createQuery(hql);
		query.setInteger("idPessoa", idPessoa);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public Object buscarUsuarioPorUsuario(long usuario) {
		
		this.abrirSessao();
		String hql = "select u from Usuario u where u.usuario = :usuario";
		Query query = this.session.createQuery(hql);
		query.setLong("usuario", usuario);
		Usuario u = (Usuario) query.uniqueResult();
		this.comitarFecharSessao();
		
		return u;
		
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
