package util;

import DAO.*;

public class DAOFactory {
	
	public static AlunoDAO criarAlunoDAO() {
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return alunoDAO;
	}
	
	public static DisciplinaDAO criarDisciplinaDAO() {
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		disciplinaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return disciplinaDAO;
	}
	
	public static TurmaDAO criarTurmaDAO() {
		TurmaDAO turmaDAO = new TurmaDAO();
		turmaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return turmaDAO;
	}
	
	public static PeriodoLetivoDAO criarPeriodoLetivoDAO() {
		PeriodoLetivoDAO periodoLetivoDAO = new PeriodoLetivoDAO();
		periodoLetivoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return periodoLetivoDAO;
	}
	
	public static EnderecoDAO criarEnderecoDAO() {
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		enderecoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return enderecoDAO;
	}
	
	public static PermissaoDAO criarPermissaoDAO() {
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		permissaoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return permissaoDAO;
	}
	
	public static TelefoneDAO criarTelefoneDAO() {
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		telefoneDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return telefoneDAO;
	}
	
	public static ProfessorDAO criarProfessorDAO() {
		ProfessorDAO professorDAO = new ProfessorDAO();
		professorDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return professorDAO;
	}
	
	public static ProfessorDisciplinaDAO criarProfessorDisciplinaDAO() {
		ProfessorDisciplinaDAO professorDisciplinaDAO = new ProfessorDisciplinaDAO();
		professorDisciplinaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return professorDisciplinaDAO;
	}
	
	public static TurnoDAO criarTurnoDAO() {
		TurnoDAO turnoDAO = new TurnoDAO();
		turnoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return turnoDAO;
	}
	
	public static AnoDAO criarAnoDAO() {
		AnoDAO anoDAO = new AnoDAO();
		anoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return anoDAO;
	}
	
	public static AlunoTurmaDAO criarAlunoTurmaDAO() {
		AlunoTurmaDAO alunoTurmaDAO = new AlunoTurmaDAO();
		alunoTurmaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return alunoTurmaDAO;
	}


}
