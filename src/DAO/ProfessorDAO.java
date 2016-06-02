package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.Aluno;
import entities.Graduacao;
import entities.Professor;
import entities.Sexo;

public class ProfessorDAO implements InterfaceDAO {

	private Session	session;
	private Transaction transaction;
	private MensagemUtil msg = new MensagemUtil();
	
	public ProfessorDAO() {
		
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
		String hql = "select p from Professor p where p.idPessoa = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		Professor professor = (Professor) query.uniqueResult();
		this.comitarFecharSessao();
		
		return professor;
	}

	@Override
	public List<Object> buscarTodos() {
		
		//return this.session.createCriteria(Professor.class).list();
		this.abrirSessao();
		String hql = "select p from Professor p order by p.idPessoa desc";
		Query query = this.session.createQuery(hql);
		List<Object> lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public Object buscarProfessorPorUsuario(long usuario) {
		
		this.abrirSessao();
		String hql = "select u.pessoa from Usuario u where u.usuario = :usuario";
		Query query = this.session.createQuery(hql);
		query.setLong("usuario", usuario);
		Professor professor = (Professor) query.uniqueResult();
		this.comitarFecharSessao();
		
		return professor;
	}
	
	//
	public List<Sexo> buscarSexo() {
		
		//return this.session.createCriteria(Sexo.class).list();
		this.abrirSessao();
		String hql = "select s from Sexo s";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public Sexo buscarSexoPorId(int id) {

		this.abrirSessao();
		String hql = "select s from Sexo s where s.idSexo = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		Sexo sexo = (Sexo) query.uniqueResult();
		this.comitarFecharSessao();
		
		return sexo;
		
	}
	
	public List<Graduacao> buscarGraduacao() {
		
		//return this.session.createCriteria(Graduacao.class).list();
		this.abrirSessao();
		String hql = "select g from Graduacao g";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public Graduacao buscarGraduacaoPorId(int id) {
		
		this.abrirSessao();
		String hql = "select g from Graduacao g where g.idGraduacao = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		Graduacao graduacao = (Graduacao) query.uniqueResult();
		this.comitarFecharSessao();
		
		return graduacao;
		
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
