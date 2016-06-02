package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import util.MensagemUtil;
import RN.AlunoTurmaRN;
import RN.DisciplinaRN;
import RN.ProfessorDisciplinaRN;
import RN.ProfessorRN;
import RN.ProfessorTurmaRN;
import entities.Disciplina;
import entities.Professor;
import entities.ProfessorDisciplina;
import entities.ProfessorTurma;
import entities.Turma;

@ManagedBean
@SessionScoped
public class ProfessorTurmaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Professor professor = new Professor();
	private Disciplina disciplina = new Disciplina();
	private ProfessorDisciplina pd = new ProfessorDisciplina();
	private Turma turma = new Turma();
	private ProfessorTurma pt = new ProfessorTurma();
	
	private List<Professor> professores;
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private List<ProfessorTurma> professorTurmaList;
	
	private int idProfessor;
	private int idDisciplina;
	
	private MensagemUtil msg = new MensagemUtil();
	
	public String addProfessores() {
		
		limparVariaveis();
		return "adicionarProfessores.jsf?faces-redirect=true";
		
	}
	
	public void limparVariaveis() {
		
		this.professor = new Professor();
		this.disciplina = new Disciplina();
		this.pt = new ProfessorTurma();
		this.pd = new ProfessorDisciplina();
		this.professorTurmaList = null;
		this.professores = null;
		
	}
	
	public void salvar() {
		
		ProfessorRN professorRN = new ProfessorRN();
		DisciplinaRN disciplinaRN = new DisciplinaRN();
		ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
		ProfessorDisciplinaRN pdRN = new ProfessorDisciplinaRN();
		
		this.professor = professorRN.buscarPorId(this.idProfessor);
		this.disciplina = disciplinaRN.buscarPorId(this.idDisciplina);
		
		this.pd = pdRN.buscarProfessorDisciplinaPorIdProfessorDisciplina(this.professor.getIdPessoa(), this.disciplina.getIdDisciplina());
		
		this.pd.setProfessor(this.professor);
		this.pd.setDisciplina(this.disciplina);
		this.pt.setProfessorDisciplina(this.pd);
		this.pt.setTurma(this.turma);
		this.pt.setAtivo(true);
		
		if(existeProfessorTurma(this.pt))
			ptRN.salvar(this.pt);
		else
			this.msg.msgErro("Professor/disciplina já existente nessa turma!");
		
		limparVariaveis();
		
	}
	
	public void excluir() {
		
		ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
		ptRN.excluir(this.pt);
		
		limparVariaveis();
		
	}
	
	public void ativarDesativar() {
		
		if(this.pt.isAtivo())
			this.pt.setAtivo(false);
		else
			this.pt.setAtivo(true);
		
		ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
		ptRN.salvar(this.pt);
		
	}
	
	private boolean existeProfessorTurma(ProfessorTurma pt) {
		
		ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
		ProfessorTurma p = ptRN.buscarProfessorTurmaIdProfessorDisciplinaIdTurma(pt.getProfessorDisciplina().getIdProfessorDisciplina(), pt.getTurma().getIdTurma());
		
		if(p != null)
			return false;
		
		return true;
	}
	
	public List<Professor> getProfessores() {
		
		if(this.professores == null) {
			ProfessorRN professorRN = new ProfessorRN();
			this.professores = professorRN.buscarTodos();
		}
		
		return this.professores;
	}
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	public void trocarDisciplina() {
		
        if((Integer)idProfessor != null && idProfessor != 0) {  
            ProfessorDisciplinaRN pdRN = new ProfessorDisciplinaRN();
            disciplinas = pdRN.buscarDisciplinasPorIdProfessor(this.idProfessor);
        	//disciplinas = disciplinaData.get(idProfessor);
        } else {
        	System.out.println("entrou aqui");
            disciplinas = new ArrayList<Disciplina>();
        }
    } 
	
	
	//
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

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public ProfessorTurma getPt() {
		return pt;
	}

	public void setPt(ProfessorTurma pt) {
		this.pt = pt;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public int getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(int idProfessor) {
		this.idProfessor = idProfessor;
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public List<ProfessorTurma> getProfessorTurmaList() {
		
		if(this.professorTurmaList == null) {
			ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
			this.professorTurmaList = ptRN.buscarPorIdTurma(this.turma.getIdTurma());
		}
		return professorTurmaList;
	}

	public void setProfessorTurmaList(List<ProfessorTurma> professorTurmaList) {
		this.professorTurmaList = professorTurmaList;
	}

}
