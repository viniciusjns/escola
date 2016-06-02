package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.AtividadeComEntrega;

public class AtividadeComEntregaDAO implements InterfaceDAO {
	
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
		String hql = "select a from AtividadeComEntrega a where a.idAtividade = :id";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		AtividadeComEntrega atividade = (AtividadeComEntrega) query.uniqueResult();
		this.comitarFecharSessao();
		
		return atividade;
	}

	@Override
	public List<Object> buscarTodos() {
		
		this.abrirSessao();
		String hql = "select a from AtividadeComEntrega a order by a.idAtividade desc";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarAtividadesPorIdProfessor(int id) {
		
		this.abrirSessao();
		String hql = "select a from AtividadeComEntrega a where a.professorTurma.professorDisciplina.professor.idPessoa = :id order by a.idAtividade";
		Query query = this.session.createQuery(hql);
		query.setInteger("id", id);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarAtividadesPorIdProfessorIdTurmaIdPeriodoLetivo(int idProfessor, int idTurma, int idPeriodoLetivo) {
		
		this.abrirSessao();
		String hql = "select a from AtividadeComEntrega a where a.professorTurma.professorDisciplina.professor.idPessoa = :idProfessor and a.professorTurma.turma.idTurma = :idTurma and a.periodoLetivo.idPeriodoLetivo = :idPeriodoLetivo";
		Query query = this.session.createQuery(hql);
		query.setInteger("idProfessor", idProfessor);
		query.setInteger("idTurma", idTurma);
		query.setInteger("idPeriodoLetivo", idPeriodoLetivo);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
	}
	
	public List<Object> buscarAtividadesEmAndamento(int idTurma, int idPeriodoLetivo) {
		
		this.abrirSessao();
		String hql = "select a from AtividadeComEntrega a where a.professorTurma.turma.idTurma = :idTurma and a.periodoLetivo.idPeriodoLetivo = :idPeriodoLetivo and (a.dataFim > CURRENT_DATE) or (a.dataFim = CURRENT_DATE and a.horaFim > CURRENT_TIME)";
		//String hql = "select * from atividade inner join atividadecomentrega on atividade.idatividade = atividadecomentrega.idatividade where atividade.idturma = :idTurma and atividade.idPeriodoLetivo = :idPeriodoLetivo and current_date between datainicio and datafim";
		Query query = this.session.createQuery(hql);
		query.setInteger("idTurma", idTurma);
		query.setInteger("idPeriodoLetivo", idPeriodoLetivo);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
		
	}
	
	public List<Object> buscarAtividadesEncerradas(int idTurma, int idPeriodoLetivo) {
		
		this.abrirSessao();
		String hql = "select a from AtividadeComEntrega a where a.professorTurma.turma.idTurma = :idTurma and a.periodoLetivo.idPeriodoLetivo = :idPeriodoLetivo and (a.dataFim < CURRENT_DATE) or (a.dataFim = CURRENT_DATE and a.horaFim < CURRENT_TIME)";
		//String hql = "select * from atividade inner join atividadecomentrega on atividade.idatividade = atividadecomentrega.idatividade where atividade.idturma = :idTurma and atividade.idPeriodoLetivo = :idPeriodoLetivo and current_date not between datainicio and datafim";
		Query query = this.session.createQuery(hql);
		query.setInteger("idTurma", idTurma);
		query.setInteger("idPeriodoLetivo", idPeriodoLetivo);
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
