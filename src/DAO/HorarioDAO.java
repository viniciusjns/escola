package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import util.HibernateUtil;
import util.MensagemUtil;
import entities.Horario;

public class HorarioDAO implements InterfaceDAO {
	
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
		String hql = "select h from Horario h where h.idHorario = :idHorario";
		Query query = this.session.createQuery(hql);
		query.setInteger("idHorario", id);
		Horario horario = (Horario) query.uniqueResult();
		this.comitarFecharSessao();
		
		return horario;
	}
	
	@Override
	public List<Object> buscarTodos() {
		
		this.abrirSessao();
		String hql = "select h from Horario h";
		Query query = this.session.createQuery(hql);
		List lista = query.list();
		this.comitarFecharSessao();
		
		return lista;
	}
	
	public List<Object> buscarPorIdTurno(int idTurno) {
		
		this.abrirSessao();
		String hql = "select h from Horario h where h.turno.idTurno = :idTurno";
		Query query = this.session.createQuery(hql);
		query.setInteger("idTurno", idTurno);
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
