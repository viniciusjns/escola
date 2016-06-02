package managedBean;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.exception.ConstraintViolationException;

import util.MensagemUtil;
import RN.DisciplinaRN;
import RN.ProfessorDisciplinaRN;
import entities.Disciplina;
import entities.Professor;
import entities.ProfessorDisciplina;

@ManagedBean
@SessionScoped
public class ProfessorDisciplinaBean {
	
	
	private int idDisciplinaSelecionada;
	
	private Professor professor = new Professor();
	private Disciplina disciplina = new Disciplina();
	private ProfessorDisciplina pd = new ProfessorDisciplina();
	
	private MensagemUtil msg = new MensagemUtil();
	
	private List<ProfessorDisciplina> professorDisciplinaList = null;
	
	
	public String addDisciplina() {		
		
		this.disciplina = null;
		this.professorDisciplinaList = null;
		this.idDisciplinaSelecionada = 0;
		
		return "adicionarDisciplina.jsf?faces-redirect=true";
		
	}
	
	public void salvar() {
		
		ProfessorDisciplinaRN pdRN = new ProfessorDisciplinaRN();
		DisciplinaRN disciplinaRN = new DisciplinaRN();
		
		this.disciplina = disciplinaRN.buscarPorId(idDisciplinaSelecionada);
		
		this.pd.setDisciplina(this.disciplina);
		this.pd.setProfessor(this.professor);
		pdRN.salvar(pd);
		
		this.pd = new ProfessorDisciplina();
		this.professorDisciplinaList = null;
		
	}
	
	public void excluir() {
		
		ProfessorDisciplinaRN pdRN = new ProfessorDisciplinaRN();
		pdRN.excluir(this.pd);
		
		this.pd = new ProfessorDisciplina();
		this.professorDisciplinaList = null;		
		
	}
	
	public List<ProfessorDisciplina> getListarDisciplinas() {
		
		if(this.professorDisciplinaList == null) {
			ProfessorDisciplinaRN pdRN = new ProfessorDisciplinaRN();
			this.professorDisciplinaList =  pdRN.buscarProfessorDisciplinasPorIdProfessor(this.professor.getIdPessoa());
		}
		
		return this.professorDisciplinaList;
		
	}

	
	public int getIdDisciplinaSelecionada() {
		return idDisciplinaSelecionada;
	}

	public void setIdDisciplinaSelecionada(int idDisciplinaSelecionada) {
		this.idDisciplinaSelecionada = idDisciplinaSelecionada;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public ProfessorDisciplina getPd() {
		return pd;
	}

	public void setPd(ProfessorDisciplina pd) {
		this.pd = pd;
	}

}
