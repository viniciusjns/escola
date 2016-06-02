package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.ProfessorTurma;

public class ProfessorTurmaDAO implements InterfaceDAO {
	
	private Session	session;
	private Transaction transaction;
	private MensagemUtil msg = new MensagemUtil();

	@Override
	public void salvar(Object obj) {
		
		try {
			this.abrirSessao();
			this.session.save(obj);
			this.comitarFecharSessao();
			this.msg.msgSucesso("Registro inserido com sucesso!");
		} catch(Exception e) {
			this.rollBack();
			this.msg.msgErro("Erro ao excluir. Informe o administrador do sistema!" + e);
		}
		
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
			this.rollBack();
			this.msg.msgErro("Registro em movimentação. Impossível excluir!");
		} catch(Exception e) {
			this.rollBack();
			this.msg.msgErro("Erro ao excluir. Informe o administrador do sistema!" + e);
		}
		
	}

	@Override
	public Object buscarPorId(int id) {
		
		this.abrirSessao();
		String hql = "select pt from ProfessorTurma pt where pt.idProfessorTurma = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		ProfessorTurma pt = (ProfessorTurma) query.uniqueResult();
		this.comitarFecharSessao();
		
		return pt;
	}

	@Override
	public List<Object> buscarTodos() {
		
		this.abrirSessao();
		String hql = "select pt from ProfessorTurma pt";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
	}
	
	public List<Object> buscarPorIdTurma(int id) {
		
		this.abrirSessao();
		String hql = "select pt from ProfessorTurma pt where pt.turma.idTurma = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
	}
	
	public List<Object> buscarTurmasPorIdProfessorIdAno(int idPessoa, int idAno) {
		
		this.abrirSessao();
		String hql = "select distinct(pt.turma) from ProfessorTurma pt where pt.professorDisciplina.professor.idPessoa = :idPessoa "
				+ "and pt.turma.ano.idAno = :idAno "
				+ "and pt.ativo = 1";
		Query query = this.session.createQuery(hql);
		query.setInteger("idPessoa", idPessoa);
		query.setInteger("idAno", idAno);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarDisciplinasProfessorTurma(int idProfessor, int idTurma) {
		
		this.abrirSessao();
		String hql = "select pt.professorDisciplina.disciplina from ProfessorTurma pt where "
					+ "pt.professorDisciplina.professor.idPessoa = :idProfessor and "
					+ "pt.turma.idTurma = :idTurma and "
					+ "pt.ativo = 1";
		Query query = this.session.createQuery(hql);
		query.setInteger("idProfessor", idProfessor);
		query.setInteger("idTurma", idTurma);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
	}
	
	public List<Object> buscarProfessorIdTurma(int idTurma) {
		
		this.abrirSessao();
		String hql = "select distinct(pt.professorDisciplina.professor) from ProfessorTurma pt where pt.turma.idTurma = :idTurma";
		Query query = this.session.createQuery(hql);
		query.setInteger("idTurma", idTurma);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public Object buscarProfessorTurmaIdProfessorDisciplinaIdTurma(int idProfessorDisciplina, int idTurma) {
		
		this.abrirSessao();
		String hql = "select pt from ProfessorTurma pt where pt.professorDisciplina.idProfessorDisciplina = :idProfessorDisciplina and pt.turma.idTurma = :idTurma";
		Query query = this.session.createQuery(hql);
		query.setInteger("idProfessorDisciplina", idProfessorDisciplina);
		query.setInteger("idTurma", idTurma);
		ProfessorTurma pt = (ProfessorTurma) query.uniqueResult();
		this.comitarFecharSessao();
		
		return pt;
		
	}
	
	public List<Object> buscarProfessorDisciplinaIdTurma(int idTurma) {
		
		this.abrirSessao();
		String hql = "select pt.professorDisciplina from ProfessorTurma pt where pt.turma.idTurma = :idTurma";
		Query query = this.session.createQuery(hql);
		query.setInteger("idTurma", idTurma);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarProfessorDisciplinaAtivoIdTurma(int idTurma) {
		
		this.abrirSessao();
		String hql = "select pt.professorDisciplina from ProfessorTurma pt where pt.turma.idTurma = :idTurma and pt.ativo = 1";
		Query query = this.session.createQuery(hql);
		query.setInteger("idTurma", idTurma);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
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
