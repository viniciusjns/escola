package DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.Aula;

public class AulaDAO implements InterfaceDAO {
	
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
		
		this.abrirSessao();
		String hql = "select a from Aula a where a.idAula = :idAula";
		Query query = this.session.createQuery(hql);
		query.setInteger("idAula", id);
		Aula aula = (Aula) query.uniqueResult();
		this.comitarFecharSessao();
		
		return aula;
	}
	
	@Override
	public List<Object> buscarTodos() {
		
		this.abrirSessao();
		String hql = "select a from Aula a order by a.idAula desc";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
	}
	
	public Object buscarAulaProfessorTurmaDiaSemanaHoraInicioHoraFim(int idProfessorTurma, int idDiaSemana, Date horaInicio, Date horaFim) {
		
		this.abrirSessao();
		String hql = "select a from Aula a where a.professorTurma.idProfessorTurma = :idProfessorTurma "
				+ "and a.diaSemana.idDiaSemana = :idDiaSemana "
				+ "and a.horaInicio = :horaInicio "
				+ "and a.horaFim = :horaFim";
		Query query = this.session.createQuery(hql);
		query.setInteger("idProfessorTurma", idProfessorTurma);
		query.setInteger("idDiaSemana", idDiaSemana);
		query.setTime("horaInicio", horaInicio);
		query.setTime("horaFim", horaFim);
		Aula aula = (Aula) query.uniqueResult();
		this.comitarFecharSessao();
		
		return aula;
		
	}
	
	public List<Object> buscarAulaIdProfessorIdAnoIdTurmaIdDisciplina(int idProfessor, int idAno, int idTurma, int idDisciplina) {
		
		this.abrirSessao();
		String hql = "select a from Aula a where a.professorTurma.professorDisciplina.professor.idPessoa = :idProfessor "
				+ "and a.professorTurma.turma.ano.idAno = :idAno "
				+ "and a.professorTurma.turma.idTurma = :idTurma "
				+ "and a.professorTurma.professorDisciplina.disciplina.idDisciplina = :idDisciplina";
		Query query = this.session.createQuery(hql);
		query.setInteger("idProfessor", idProfessor);
		query.setInteger("idAno", idAno);
		query.setInteger("idTurma", idTurma);
		query.setInteger("idDisciplina", idDisciplina);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public void abrirSessao() {
		this.session = HibernateUtil.getSessionFactory().openSession();
		this.transaction = this.session.beginTransaction();
	}
	
	public void comitarFecharSessao() {
		this.transaction.commit();
		this.session.close();
	}
	
	public void rollBack() {
		this.transaction.rollback();
	}

}
