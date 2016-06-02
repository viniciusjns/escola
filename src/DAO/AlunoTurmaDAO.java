package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.Aluno;
import entities.AlunoTurma;

public class AlunoTurmaDAO implements InterfaceDAO {
	
	private Session	session;
	private Transaction transaction;
	private MensagemUtil msg = new MensagemUtil();
	
	public AlunoTurmaDAO() {
		//this.session = HibernateUtil.getSessionFactory().openSession();
		//this.transaction = this.session.beginTransaction();
	}
	
	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Object obj) {
		
		try {
			this.abrirSessao();
			this.session.save(obj);
			this.comitarFecharSessao();
			//this.msg.msgSucesso("Registro(s) inserido(s) com sucesso!");
		} catch(Exception ex) {
			this.rollBack();
			//this.msg.msgErro("Erro ao inserir. Informe o administrador do sistema!" + ex);
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
		} catch (Exception e) {
			this.rollBack();
			this.msg.msgErro("Erro ao excluir. Informe o administrador do sistema!" + e);
		}
		
	}

	@Override
	public Object buscarPorId(int id) {
		
		this.abrirSessao();
		String hql = "select at from AlunoTurma at where at.idAlunoTurma = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		AlunoTurma alunoTurma = (AlunoTurma) query.uniqueResult();
		this.comitarFecharSessao();
		
		return alunoTurma;
		
	}

	@Override
	public List<Object> buscarTodos() {
		
		//return this.session.createCriteria(AlunoTurma.class).list();
		this.abrirSessao();
		String hql = "select at from AlunoTurma at";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarAlunosNaoCadastrados(int id) {
		
		this.abrirSessao();
		String hql = "select * from Aluno a inner join Pessoa p on a.idPessoa = p.idPessoa where a.idPessoa not in (select a.idPessoa from Aluno a inner join AlunoTurma atu on atu.idPessoa = a.idPessoa where atu.idTurma = :id) order by p.nome";
		Query query = this.session.createSQLQuery(hql).addEntity(Aluno.class);
		query.setInteger("id", id);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarAlunosPorIdTurma(int id) {
		
		this.abrirSessao();
		String hql = "select * from Aluno a inner join Pessoa p on a.idPessoa = p.idPessoa inner join AlunoTurma atu on a.idPessoa = atu.idPessoa inner join Turma t on atu.idTurma = t.idTurma where t.idTurma = :id  order by p.nome";
		Query query = this.session.createSQLQuery(hql).addEntity(Aluno.class);
		query.setInteger("id", id);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public AlunoTurma buscarAlunoTurmaPorIdAlunoTurma(int idAluno, int idTurma) {
		
		this.abrirSessao();
		String hql = "select at from AlunoTurma at where at.aluno.idPessoa = :idAluno and at.turma.idTurma = :idTurma";
		Query query = this.session.createQuery(hql);
		query.setInteger("idAluno", idAluno);
		query.setInteger("idTurma", idTurma);
		AlunoTurma alunoTurma = (AlunoTurma) query.uniqueResult();
		this.comitarFecharSessao();
		
		return alunoTurma;
		
	}
	
	public List<Object> buscarTurmasPorIdAlunoIdAno(int idPessoa, int idAno) {
		
		this.abrirSessao();
		String hql = "select at.turma from AlunoTurma at where at.aluno.idPessoa = :idPessoa and at.turma.ano.idAno = :idAno";
		Query query = this.session.createQuery(hql);
		query.setInteger("idPessoa", idPessoa);
		query.setInteger("idAno", idAno);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarAlunoTurmaPorIdTurma(int idTurma) {
		
		this.abrirSessao();
		String hql = "select at from AlunoTurma at where at.turma.idTurma = :idTurma order by at.aluno.nome";
		Query query = this.session.createQuery(hql);
		query.setInteger("idTurma", idTurma);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarAlunoTurmaPorIdTurmaAtivo(int idTurma) {
		
		this.abrirSessao();
		String hql = "select at from AlunoTurma at where at.turma.idTurma = :idTurma and at.ativo = 1 order by at.aluno.nome";
		Query query = this.session.createQuery(hql);
		query.setInteger("idTurma", idTurma);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarAlunoAtivoPorIdTurma(int idTurma) {
		
		this.abrirSessao();
		String hql = "select at.aluno from AlunoTurma at where at.turma.idTurma = :idTurma and at.ativo = 1 order by at.aluno.nome";
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
