package DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.Aluno;
import entities.Falta;

public class FaltaDAO implements InterfaceDAO {
	
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
		} catch(Exception ex) {
			this.msg.msgErro("Erro ao excluir. Informe o administrador do sistema!" + ex);
			this.rollBack();
		}
		
	}

	@Override
	public Object buscarPorId(int id) {
		
		this.abrirSessao();
		String hql = "select f from Falta f where f.idFalta = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		Falta falta = (Falta) query.uniqueResult();
		this.comitarFecharSessao();
		
		return falta;
	}

	@Override
	public List<Object> buscarTodos() {
		
		this.abrirSessao();
		String hql = "select f from Falta f";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarAlunosComFaltas(int idTurma, int idAula, int idPeriodoLetivo, Date data) {
		
		this.abrirSessao();
		String hql = "select f from Falta f where "
				+ "f.aula.idAula = :idAula and "
				+ "f.periodoLetivo.idPeriodoLetivo = :idPeriodoLetivo and "
				+ "f.data = :data and "
				+ "f.aula.professorTurma.turma.idTurma = :idTurma "
				+ "order by f.alunoTurma.aluno.nome";
		Query query = this.session.createQuery(hql);
		query.setInteger("idAula", idAula);
		query.setInteger("idTurma", idTurma);
		query.setInteger("idPeriodoLetivo", idPeriodoLetivo);
		query.setDate("data", data);
		
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarAlunosSemFaltas(int idTurma, int idAula, int idPeriodoLetivo, Date data) {
		
		this.abrirSessao();
		String hql = "select * from aluno a inner join pessoa p on a.idPessoa = p.idPessoa "
				+ "inner join alunoTurma atu on p.idPessoa = atu.idPessoa "
				+ "inner join turma t on t.idturma = atu.idturma "
				+ "where atu.ativo = 1 and t.idturma = :idTurma and a.idPessoa not in("
				+ "select a.idpessoa from aluno a inner join "
				+ "pessoa p on a.idPessoa = p.idPessoa inner join "
				+ "alunoturma atu on p.idPessoa = atu.idpessoa inner join "
				+ "turma t on t.idturma = atu.idturma inner join "
				+ "falta f on f.idAlunoTurma = atu.idAlunoTurma where t.idturma = :idTurma and f.idAula = :idAula and f.idperiodoletivo = :idPeriodoLetivo and f.data = :data) "
				+ "order by p.nome";
		Query query = this.session.createSQLQuery(hql).addEntity(Aluno.class);
		query.setInteger("idTurma", idTurma);
		query.setInteger("idAula", idAula);
		query.setInteger("idPeriodoLetivo", idPeriodoLetivo);
		query.setDate("data", data);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarFaltasPorPeriodoAlunoTurma(int idPeriodoLetivo, int idAluno, int idTurma) {
		
		this.abrirSessao();
		String hql = "select f from Falta f where "
				+ "f.periodoLetivo.idPeriodoLetivo = :idPeriodoLetivo and "
				+ "f.alunoTurma.aluno.idPessoa = :idAluno and "
				+ "f.aula.professorTurma.turma.idTurma = :idTurma "
				+ "order by f.data";
		Query query = this.session.createQuery(hql);
		query.setInteger("idPeriodoLetivo", idPeriodoLetivo);
		query.setInteger("idAluno", idAluno);
		query.setInteger("idTurma", idTurma);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarQuantidadeFaltas(int idAluno, int idAula) {
		
		this.abrirSessao();
		String hql = "select f from Falta f where f.alunoTurma.aluno.idPessoa = :idAluno and f.aula.idAula = :idAula";
		Query query = this.session.createQuery(hql);
		query.setInteger("idAluno", idAluno);
		query.setInteger("idAula", idAula);
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

}
