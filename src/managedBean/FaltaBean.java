package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import util.AlunoDataModel;
import util.ContextoUtil;
import util.MensagemUtil;
import RN.AlunoRN;
import RN.AlunoTurmaRN;
import RN.AulaRN;
import RN.FaltaRN;
import RN.PeriodoLetivoRN;
import RN.ProfessorTurmaRN;
import RN.TurmaRN;
import entities.Aluno;
import entities.AlunoTurma;
import entities.Aula;
import entities.Disciplina;
import entities.Falta;
import entities.PeriodoLetivo;
import entities.Turma;

@ManagedBean
@ViewScoped
public class FaltaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Falta falta = new Falta();
	private Aula aula = new Aula();
	private PeriodoLetivo periodoLetivo = new PeriodoLetivo();
	private AlunoTurma alunoTurma = new AlunoTurma();
	
	
	private int idAnoSelecionado;
	private int idPeriodoSelecionado;
	private int idTurmaSelecionada;
	private int idDisciplinaSelecionada;
	private int idAulaSelecionada;
	
	
	private List<PeriodoLetivo> periodos;
	private List<Turma> turmas;
	private List<Turma> alunoTurmas;
	private List<Disciplina> disciplinas;
	private List<Aula> aulas;
	private List<Aluno> alunoList;
	private List<Falta> faltaList = new ArrayList<Falta>();
	private List<Falta> faltasAluno = new ArrayList<Falta>();
	
	
	private AlunoDataModel alunoDataModel;
	private ContextoBean contextoBean = ContextoUtil.getContextoBean();
	private Date data = new Date();
	private MensagemUtil msg = new MensagemUtil();

	
	public void buscarAlunos() {
		
		System.out.println("turma: " + this.idTurmaSelecionada);
		System.out.println("aula: " + this.idAulaSelecionada);
		System.out.println("periodo: " + this.idPeriodoSelecionado);
		
		this.faltaList = null;
		
		this.alunoDataModel = new AlunoDataModel(this.buscarAlunosSemFaltas());
		
	}
	
	public void salvarFrequencia() {
		
		AulaRN aulaRN = new AulaRN();
		this.aula = aulaRN.buscarPorId(this.idAulaSelecionada);
		
		PeriodoLetivoRN periodoRN = new PeriodoLetivoRN();
		this.periodoLetivo = periodoRN.buscarPorId(this.idPeriodoSelecionado);
		
		AlunoTurmaRN atRN = new AlunoTurmaRN();
		FaltaRN faltaRN = new FaltaRN();
		
		for(int i = 0; i < this.alunoList.size(); i++) {
			
			this.alunoTurma = atRN.buscarAlunoTurmaPorIdAlunoTurma(this.alunoList.get(i).getIdPessoa(), this.idTurmaSelecionada);
			
			this.falta.setAlunoTurma(this.alunoTurma);
			this.falta.setAula(this.aula);
			this.falta.setData(this.data);
			this.falta.setPeriodoLetivo(this.periodoLetivo);
			
			faltaRN.salvar(this.falta);
			
			this.falta = new Falta();
			this.alunoTurma = new AlunoTurma();
			
		}
		
		this.msg.msgSucesso("Registro(s) salvo(s) com sucesso!");
		
		this.faltaList = null;
		this.alunoList = null;
		this.alunoDataModel = new AlunoDataModel(this.buscarAlunosSemFaltas());
	}
	
	public void excluir() {
		
		FaltaRN faltaRN = new FaltaRN();
		faltaRN.excluir(this.falta);
		
		this.faltaList = null;
		this.falta = new Falta();
		this.alunoDataModel = new AlunoDataModel(this.buscarAlunosSemFaltas());
		
	}
	
	private List<Aluno> buscarAlunosSemFaltas() {
		
		FaltaRN faltaRN = new FaltaRN();
		return faltaRN.buscarAlunosSemFaltas(this.idTurmaSelecionada, this.idAulaSelecionada, this.idPeriodoSelecionado, this.data);
		
	}
	
	public void listarFaltas() {
		
		FaltaRN faltaRN = new FaltaRN();
		this.faltasAluno = faltaRN.buscarFaltasPorPeriodoAlunoTurma(this.idPeriodoSelecionado, this.contextoBean.getAlunoLogado().getIdPessoa(), this.idTurmaSelecionada);
		
	}
	
	public int quantidadeFaltas(int idAluno) {
		
		FaltaRN faltaRN = new FaltaRN();
		
		return faltaRN.buscarQuantidadeFaltas(idAluno, this.idAulaSelecionada).size();
	}

	
	public void trocarPeriodoLetivo() {
		
		if((Integer)idAnoSelecionado != null && idAnoSelecionado != 0) {
			PeriodoLetivoRN periodoRN = new PeriodoLetivoRN();
			periodos = periodoRN.buscarPorIdAno(this.idAnoSelecionado);
		} else {
			periodos = new ArrayList<PeriodoLetivo>();
		}
		
	}
	
	public void trocarTurmaProfessor() {
		
		if((Integer)idAnoSelecionado != null && idAnoSelecionado != 0) {
			ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
			this.turmas = ptRN.buscarTurmasPorIdProfessorIdAno(contextoBean.getProfessorLogado().getIdPessoa(), this.idAnoSelecionado);
		} else {
			this.turmas = new ArrayList<Turma>();
		}
		
	}
	
	public void trocarTurmaAluno() {
		
		if((Integer)idAnoSelecionado != null && idAnoSelecionado != 0) {
			
			AlunoTurmaRN atRN = new AlunoTurmaRN();
			this.alunoTurmas = atRN.buscarTurmasPorIdAlunoIdAno(this.contextoBean.getAlunoLogado().getIdPessoa(), this.idAnoSelecionado);
			
		} else {
			
			this.alunoTurmas = new ArrayList<Turma>();
			
		}
		
	}
	
	public void trocarDisciplina() {
		
        if((Integer) this.idTurmaSelecionada != null && this.idTurmaSelecionada != 0) {
        	
            ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
            disciplinas = ptRN.buscarDisciplinasProfessorTurma(this.contextoBean.getProfessorLogado().getIdPessoa(), this.idTurmaSelecionada);
            
        } else {
        	
        	this.disciplinas = new ArrayList<Disciplina>();
        	
        }
    }
	
	public void trocarAula() {
		
		if((Integer) this.idDisciplinaSelecionada != null && this.idDisciplinaSelecionada != 0) {
			
			AulaRN aulaRN = new AulaRN();
			this.aulas = aulaRN.buscarAulaIdProfessorIdAnoIdTurmaIdDisciplina(this.contextoBean.getProfessorLogado().getIdPessoa(), this.idAnoSelecionado, this.idTurmaSelecionada, this.idDisciplinaSelecionada);
			
		} else {
			
			this.aulas = new ArrayList<Aula>();
			
		}
		
	}
	
	
	//
	public int getIdAnoSelecionado() {
		return idAnoSelecionado;
	}

	public void setIdAnoSelecionado(int idAnoSelecionado) {
		this.idAnoSelecionado = idAnoSelecionado;
	}

	public List<PeriodoLetivo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<PeriodoLetivo> periodos) {
		this.periodos = periodos;
	}

	public int getIdPeriodoSelecionado() {
		return idPeriodoSelecionado;
	}


	public void setIdPeriodoSelecionado(int idPeriodoSelecionado) {
		this.idPeriodoSelecionado = idPeriodoSelecionado;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}


	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public int getIdTurmaSelecionada() {
		return idTurmaSelecionada;
	}

	public void setIdTurmaSelecionada(int idTurmaSelecionada) {
		this.idTurmaSelecionada = idTurmaSelecionada;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public int getIdDisciplinaSelecionada() {
		return idDisciplinaSelecionada;
	}

	public void setIdDisciplinaSelecionada(int idDisciplinaSelecionada) {
		this.idDisciplinaSelecionada = idDisciplinaSelecionada;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getIdAulaSelecionada() {
		return idAulaSelecionada;
	}

	public void setIdAulaSelecionada(int idAulaSelecionada) {
		this.idAulaSelecionada = idAulaSelecionada;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}


	public List<Aluno> getAlunoList() {
		return alunoList;
	}


	public void setAlunoList(List<Aluno> alunoList) {
		this.alunoList = alunoList;
	}

	public AlunoDataModel getAlunoDataModel() {
		return alunoDataModel;
	}

	public void setAlunoDataModel(AlunoDataModel alunoDataModel) {
		this.alunoDataModel = alunoDataModel;
	}

	public Falta getFalta() {
		return falta;
	}

	public void setFalta(Falta falta) {
		this.falta = falta;
	}

	public List<Falta> getFaltaList() {
		
		if(this.faltaList == null || this.faltaList.isEmpty()) {
			
			FaltaRN faltaRN = new FaltaRN();
			this.faltaList = faltaRN.buscarAlunosComFaltas(this.idTurmaSelecionada, this.idAulaSelecionada, this.idPeriodoSelecionado, this.data);
			
		}
		
		return this.faltaList;
	}

	public void setFaltaList(List<Falta> faltaList) {
		this.faltaList = faltaList;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public PeriodoLetivo getPeriodoLetivo() {
		return periodoLetivo;
	}

	public void setPeriodoLetivo(PeriodoLetivo periodoLetivo) {
		this.periodoLetivo = periodoLetivo;
	}

	public AlunoTurma getAlunoTurma() {
		return alunoTurma;
	}

	public void setAlunoTurma(AlunoTurma alunoTurma) {
		this.alunoTurma = alunoTurma;
	}

	public List<Falta> getFaltasAluno() {
		return faltasAluno;
	}

	public void setFaltasAluno(List<Falta> faltasAluno) {
		this.faltasAluno = faltasAluno;
	}

	public List<Turma> getAlunoTurmas() {
		return alunoTurmas;
	}

	public void setAlunoTurmas(List<Turma> alunoTurmas) {
		this.alunoTurmas = alunoTurmas;
	}
	

}
