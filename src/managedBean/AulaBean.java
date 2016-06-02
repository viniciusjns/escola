package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import util.MensagemUtil;
import RN.AnoRN;
import RN.AulaRN;
import RN.DiaSemanaRN;
import RN.ProfessorDisciplinaRN;
import RN.ProfessorTurmaRN;
import RN.TurmaRN;
import entities.Ano;
import entities.Aula;
import entities.DiaSemana;
import entities.Disciplina;
import entities.Professor;
import entities.ProfessorDisciplina;
import entities.ProfessorTurma;
import entities.Turma;

@ManagedBean
@SessionScoped
public class AulaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;


	private Aula aula = new Aula();
	private Ano ano = new Ano();
	private ProfessorTurma professorTurma = new ProfessorTurma();
	private ProfessorDisciplina professorDisciplina = new ProfessorDisciplina();
	private DiaSemana diaSemana = new DiaSemana();
	
	
	private List<Aula> aulaList;
	private List<Aula> filteredAula;
	private List<Turma> turmaList;
	private List<Professor> professorList;
	private List<Disciplina> disciplinaList;
	
	
	private int idAnoSelecionado;
	private int idTurmaSelecionada;
	private int idProfessorSelecionado;
	private int idDisciplinaSelecionada;
	private int idDiaSemanaSelecionado;
	
	private boolean anoCorrente = true;
	private int anoSelecionado = 1;
	
	private MensagemUtil msg = new MensagemUtil();
	
	
	public String novaAula() {
		
		this.aula = new Aula();
		
		return "novaAula.jsf?faces-redirect=true";
		
	}
	
	public String cancelar() {
	
		this.aula = null;
		this.diaSemana = null;
		this.ano = null;
		this.professorDisciplina = null;
		this.professorTurma = null;
		
		return "gerenciarAulas.jsf?faces-redirect=true";
		
	}
	
	public String salvar() {
		
		/*AnoRN anoRN = new AnoRN();
		this.ano = anoRN.buscarPorId(this.idAnoSelecionado);*/
		
		DiaSemanaRN diaRN = new DiaSemanaRN();
		this.diaSemana = diaRN.buscarPorId(this.idDiaSemanaSelecionado);
		
		ProfessorDisciplinaRN pdRN = new ProfessorDisciplinaRN();
		this.professorDisciplina = pdRN.buscarProfessorDisciplinaPorIdProfessorDisciplina(this.idProfessorSelecionado, this.idDisciplinaSelecionada);
		
		ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
		this.professorTurma = ptRN.buscarProfessorTurmaIdProfessorDisciplinaIdTurma(this.professorDisciplina.getIdProfessorDisciplina(), this.idTurmaSelecionada);
		
		this.aula.setProfessorTurma(this.professorTurma);
		this.aula.setDiaSemana(this.diaSemana);
		
		AulaRN aulaRN = new AulaRN();
		
		if(existeAula(this.aula)) {
			aulaRN.salvar(this.aula);
			return "gerenciarAulas.jsf?faces-redirect=true";
		} else
			this.msg.msgErro("Essa aula já está cadastrada no sistema!");
		
		return null;
		
	}
	
	public String editar() {
		
		this.idAnoSelecionado = this.aula.getProfessorTurma().getTurma().getAno().getIdAno();
		this.idDiaSemanaSelecionado = this.aula.getDiaSemana().getIdDiaSemana();
		this.idDisciplinaSelecionada = this.aula.getProfessorTurma().getProfessorDisciplina().getDisciplina().getIdDisciplina();
		this.idProfessorSelecionado = this.aula.getProfessorTurma().getProfessorDisciplina().getProfessor().getIdPessoa();
		this.idTurmaSelecionada = this.aula.getProfessorTurma().getTurma().getIdTurma();
		
		this.trocarDisciplina();
		this.trocarProfessor();
		this.trocarTurma();
		
		return "novaAula.jsf?faces-redirect=true";
	}
	
	public String excluir() {
		
		AulaRN aulaRN = new AulaRN();
		aulaRN.excluir(this.aula);
		
		this.aulaList = null;
		
		return null;
		
	}

	private boolean existeAula(Aula aula) {
		
		AulaRN aulaRN = new AulaRN();
		Aula a = aulaRN.buscarAulaProfessorTurmaDiaSemanaHoraInicioHoraFim(
				aula.getProfessorTurma().getIdProfessorTurma(), 
				aula.getDiaSemana().getIdDiaSemana(), 
				aula.getHoraInicio(), 
				aula.getHoraFim());
		
		/*System.out.println("professor turma: " + a.getProfessorTurma().getIdProfessorTurma());
		System.out.println("dia semana: " + a.getDiaSemana().getIdDiaSemana());
		System.out.println("hora inicio/fim: " + a.getHoraInicio() + " - " + a.getHoraFim());*/
		
		if(a != null)
			return false;
		else
			return true;
	}
	
	public List<Ano> getBuscarAno() {
		
		AnoRN anoRN = new AnoRN();
		return anoRN.buscarTodos();
		
	}
	
	public void trocarTurma() {
		
		if((Integer) this.idAnoSelecionado != null || this.idAnoSelecionado != 0) {
			
			TurmaRN turmaRN = new TurmaRN();
			this.turmaList = turmaRN.buscarPorIdAno(this.idAnoSelecionado);
			
		} else {
			
			this.turmaList = new ArrayList<Turma>();
			
		}
		
	}
	
	public void trocarProfessor() {
		
		if((Integer) this.idTurmaSelecionada != null || this.idTurmaSelecionada != 0) {
			
			ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
			this.professorList = ptRN.buscarProfessorIdTurma(this.idTurmaSelecionada);
			
		} else {
			
			this.professorList = new ArrayList<Professor>();
			
		}
		
	}
	
	public void trocarDisciplina() {
		
		if((Integer) this.idProfessorSelecionado != null || this.idProfessorSelecionado != 0) {
			
			ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
			this.disciplinaList = ptRN.buscarDisciplinasProfessorTurma(this.idProfessorSelecionado, this.idTurmaSelecionada);
			
		} else {
			
			this.disciplinaList = new ArrayList<Disciplina>();
			
		}
		
	}
	
	public List<DiaSemana> getBuscarTodosDiaSemana() {
		
		DiaSemanaRN diaSemanaRN = new DiaSemanaRN();
		return diaSemanaRN.buscarTodos();
		
	}


	//
	public Aula getAula() {
		return aula;
	}


	public void setAula(Aula aula) {
		this.aula = aula;
	}


	public List<Aula> getAulaList() {
		
		this.aulaList = null;
		
		if(this.aulaList == null) {
			
			AulaRN aulaRN = new AulaRN();
			this.aulaList = aulaRN.buscarTodos();
			
		}
		
		return aulaList;
	}


	public void setAulaList(List<Aula> aulaList) {
		this.aulaList = aulaList;
	}

	public int getIdAnoSelecionado() {
		return idAnoSelecionado;
	}

	public void setIdAnoSelecionado(int idAnoSelecionado) {
		this.idAnoSelecionado = idAnoSelecionado;
	}

	public List<Turma> getTurmaList() {
		return turmaList;
	}

	public void setTurmaList(List<Turma> turmaList) {
		this.turmaList = turmaList;
	}

	public int getIdTurmaSelecionada() {
		return idTurmaSelecionada;
	}

	public void setIdTurmaSelecionada(int idTurmaSelecionada) {
		this.idTurmaSelecionada = idTurmaSelecionada;
	}

	public List<Aula> getFilteredAula() {
		return filteredAula;
	}

	public void setFilteredAula(List<Aula> filteredAula) {
		this.filteredAula = filteredAula;
	}

	public int getIdProfessorSelecionado() {
		return idProfessorSelecionado;
	}

	public void setIdProfessorSelecionado(int idProfessorSelecionado) {
		this.idProfessorSelecionado = idProfessorSelecionado;
	}

	public List<Professor> getProfessorList() {
		return professorList;
	}

	public void setProfessorList(List<Professor> professorList) {
		this.professorList = professorList;
	}

	public List<Disciplina> getDisciplinaList() {
		return disciplinaList;
	}

	public void setDisciplinaList(List<Disciplina> disciplinaList) {
		this.disciplinaList = disciplinaList;
	}

	public int getIdDisciplinaSelecionada() {
		return idDisciplinaSelecionada;
	}

	public void setIdDisciplinaSelecionada(int idDisciplinaSelecionada) {
		this.idDisciplinaSelecionada = idDisciplinaSelecionada;
	}

	public int getIdDiaSemanaSelecionado() {
		return idDiaSemanaSelecionado;
	}

	public void setIdDiaSemanaSelecionado(int idDiaSemanaSelecionado) {
		this.idDiaSemanaSelecionado = idDiaSemanaSelecionado;
	}

	public Ano getAno() {
		return ano;
	}

	public void setAno(Ano ano) {
		this.ano = ano;
	}

	public ProfessorTurma getProfessorTurma() {
		return professorTurma;
	}

	public void setProfessorTurma(ProfessorTurma professorTurma) {
		this.professorTurma = professorTurma;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public ProfessorDisciplina getProfessorDisciplina() {
		return professorDisciplina;
	}

	public void setProfessorDisciplina(ProfessorDisciplina professorDisciplina) {
		this.professorDisciplina = professorDisciplina;
	}

	public boolean isAnoCorrente() {
		return anoCorrente;
	}

	public void setAnoCorrente(boolean anoCorrente) {
		this.anoCorrente = anoCorrente;
	}

	public int getAnoSelecionado() {
		return anoSelecionado;
	}

	public void setAnoSelecionado(int anoSelecionado) {
		this.anoSelecionado = anoSelecionado;
	}
	
	

}
