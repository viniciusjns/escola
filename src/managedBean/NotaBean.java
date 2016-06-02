package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;

import util.ContextoUtil;
import util.MensagemUtil;
import RN.AlunoTurmaRN;
import RN.AtividadeComEntregaRN;
import RN.AtividadeRN;
import RN.AtividadeSemEntregaRN;
import RN.DisciplinaRN;
import RN.NotaRN;
import RN.PeriodoLetivoRN;
import RN.ProfessorTurmaRN;
import entities.AlunoTurma;
import entities.Atividade;
import entities.AtividadeComEntrega;
import entities.AtividadeSemEntrega;
import entities.Disciplina;
import entities.DisciplinaNotas;
import entities.Nota;
import entities.PeriodoLetivo;
import entities.ProfessorDisciplina;
import entities.ProfessorDisciplinaNota;
import entities.Turma;

@ManagedBean
@ViewScoped
public class NotaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idAnoSelecionado;
	private int idTurmaSelecionada;
	private int idDisciplinaSelecionada;
	private int idPeriodoSelecionado;
	private int idAtividadeSelecionada;
	private int idAlunoTurmaSelecionado;
	
	private boolean painel1 = true;
	private boolean painel2 = true;
	
	private Atividade atividade = new Atividade();
	private ContextoBean contextoBean = ContextoUtil.getContextoBean();
	
	private List<Turma> turmas = new ArrayList<Turma>();
	private List<PeriodoLetivo> periodos = new ArrayList<PeriodoLetivo>();
	private List<Atividade> atividades = null;
	private List<AtividadeComEntrega> atvComEntregas;
	private List<AtividadeSemEntrega> atvSemEntregas;
	private List<AlunoTurma> alunoTurmaList = null;
	private List<Nota> notas;
	private List<Nota> notaList;
	private List<Disciplina> disciplinaList;
	private List<Turma> turmasAluno;
	private List<DisciplinaNotas> disciplinaNotasList = new ArrayList<DisciplinaNotas>();
	
	private List<ProfessorDisciplina> professorDisciplinaList;
	private List<ProfessorDisciplinaNota> pdnList;
	
	private MensagemUtil msg = new MensagemUtil();
	
	public void salvar() {
		
		if(!verificarNotas(this.notas)) {
			
			this.msg.msgErro("Impossível inserir registro!");
			
		} else {
			
			NotaRN notaRN = new NotaRN();
			
			for(int i = 0; i < this.notas.size(); i++) {
				
				notaRN.salvar(this.notas.get(i));
				
			}
			
			this.msg.msgSucesso("Registro(s) salvo(s) com sucesso!");
			
		}
		
	}
	
	public boolean verificarNotas(List<Nota> notas) {
		
		for(int i = 0; i < notas.size(); i++) {
			
			if(notas.get(i).getValor() > notas.get(i).getAtividade().getValor()) {
				
				this.msg.msgErro("A nota postada para o aluno " + notas.get(i).getAlunoTurma().getAluno().getNome() + " é maior que o valor da atividade!");
				return false;
				
			}
			
		}
		
		return true;
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
			this.turmasAluno = atRN.buscarTurmasPorIdAlunoIdAno(this.contextoBean.getAlunoLogado().getIdPessoa(), this.idAnoSelecionado);
		} else {
			this.turmasAluno = new ArrayList<Turma>();
		}
		
	}
	
	public void buscarAtividades() {
		
		this.atividades = new ArrayList<Atividade>();
		
		AtividadeComEntregaRN atvComEntregaRN = new AtividadeComEntregaRN();
		AtividadeSemEntregaRN atvSemEntregaRN = new AtividadeSemEntregaRN();
		
		atvComEntregas = atvComEntregaRN.buscarAtividadesPorIdProfessorIdTurmaIdPeriodoLetivo(this.contextoBean.getProfessorLogado().getIdPessoa(), idTurmaSelecionada, idPeriodoSelecionado);
		atvSemEntregas = atvSemEntregaRN.buscarAtividadesPorIdProfessorIdTurmaIdPeriodoLetivo(this.contextoBean.getProfessorLogado().getIdPessoa(), idTurmaSelecionada, idPeriodoSelecionado);
			
		for(int i = 0; i < atvComEntregas.size(); i++) {
			
			this.atividades.add(atvComEntregas.get(i));
			
		}
		
		for(int i = 0; i < atvSemEntregas.size(); i++) {
			
			this.atividades.add(atvSemEntregas.get(i));
			
		}
		
	}
	
	public void buscarAlunos() {
		
		this.alunoTurmaList = null;
		this.notas = null;
		
		if(this.alunoTurmaList == null) {
			
			AlunoTurmaRN atRN = new AlunoTurmaRN();
			this.alunoTurmaList = atRN.buscarAlunoTurmaPorIdTurmaAtivo(this.idTurmaSelecionada);
		}
		
		this.buscarNotas();
		
	}
	
	public void buscarNotas() {
		
		AtividadeRN atividadeRN = new AtividadeRN();
		this.atividade = atividadeRN.buscarPorId(this.idAtividadeSelecionada);
		
		NotaRN notaRN = new NotaRN();
		this.notas = notaRN.buscarPorIdAtividadeIdTurma(this.idAtividadeSelecionada, this.idTurmaSelecionada);
		
		if(this.notas == null || this.notas.isEmpty()) {
			for(int i = 0; i < this.alunoTurmaList.size(); i++) {
				
				Nota nota = new Nota();
				nota.setAlunoTurma(this.alunoTurmaList.get(i));
				nota.setValor(0);
				nota.setAtividade(this.atividade);
				
				this.notas.add(nota);
				
			}
		}
		
	}

	/*public void consultarNotas() {
		
		this.buscarDisciplinaPorIdTurma();
		
		this.notaList = null;
		this.disciplinaNotasList = new ArrayList<DisciplinaNotas>();
		
		if(this.notaList == null) {
			
			NotaRN notaRN = new NotaRN();
			this.notaList = notaRN.buscarPorIdAlunoIdTurmaIdPeriodo(this.contextoBean.getAlunoLogado().getIdPessoa(), this.idTurmaSelecionada, this.idPeriodoSelecionado);
			
		}
		
		for(int i = 0; i < this.disciplinaList.size(); i++) {
			DisciplinaNotas disciplinaNotas = new DisciplinaNotas();
			List<Nota> temp = new ArrayList<Nota>();
			disciplinaNotas.setDisciplina(this.disciplinaList.get(i));
			for(int j = 0; j < this.notaList.size(); j++) {
				if(this.disciplinaList.get(i).getIdDisciplina() == this.notaList.get(j).getAtividade().getProfessorTurma().getProfessorDisciplina().getDisciplina().getIdDisciplina()) {
					temp.add(this.notaList.get(j));
				}
				
			}
			disciplinaNotas.setNotas(temp);
			disciplinaNotasList.add(disciplinaNotas);
		}
		
	}*/
	
	public void consultarNotas() {
		
		this.buscarProfessorDisciplinaPorIdTurma();
		
		this.notaList = null;
		this.pdnList = new ArrayList<ProfessorDisciplinaNota>();
		
		if(this.notaList == null) {
			
			NotaRN notaRN = new NotaRN();
			this.notaList = notaRN.buscarPorIdAlunoIdTurmaIdPeriodo(this.contextoBean.getAlunoLogado().getIdPessoa(), this.idTurmaSelecionada, this.idPeriodoSelecionado);
			
		}
		
		for(int i = 0; i < this.professorDisciplinaList.size(); i++) {
			ProfessorDisciplinaNota pdn = new ProfessorDisciplinaNota();
			List<Nota> temp = new ArrayList<Nota>();
			pdn.setProfessorDisciplina(this.professorDisciplinaList.get(i));
			
			for(int j = 0; j < this.notaList.size(); j++) {
				if(this.professorDisciplinaList.get(i).getIdProfessorDisciplina() == this.notaList.get(j).getAtividade().getProfessorTurma().getProfessorDisciplina().getIdProfessorDisciplina()) {
					temp.add(this.notaList.get(j));
				}
			}
			pdn.setNotas(temp);
			pdnList.add(pdn);
		}
		
	}

	/*public void buscarDisciplinaPorIdTurma() {
		
		this.disciplinaList = null;
		
		if(this.disciplinaList == null) {
			
			DisciplinaRN disciplinaRN = new DisciplinaRN();
			this.disciplinaList = disciplinaRN.buscarDisciplinaPorIdTurma(this.idTurmaSelecionada);
			
		}
		
	}*/
	
	public void buscarProfessorDisciplinaPorIdTurma() {
		
		this.professorDisciplinaList = null;
		
		if(this.professorDisciplinaList == null) {
			
			ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
			this.professorDisciplinaList = ptRN.buscarProfessorDisciplinaIdTurma(this.idTurmaSelecionada);
			
		}
		
	}
	
	public float somarValorAtividade(List<Nota> notas) {
		
		float total = 0;
		
		for(int i = 0; i < notas.size(); i++) {
			
				total += notas.get(i).getAtividade().getValor();
			
		}
		
		return total;
		
	}
	
	public float somarNotaObtida(List<Nota> notas) {
		
		float total = 0;
		
		for(int i = 0; i < notas.size(); i++) {
			
				total += notas.get(i).getValor();
			
		}
		
		return total;
		
	}
	
	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
    }

	
	//
	public int getIdAnoSelecionado() {
		return idAnoSelecionado;
	}

	public void setIdAnoSelecionado(int idAnoSelecionado) {
		this.idAnoSelecionado = idAnoSelecionado;
	}

	public int getIdTurmaSelecionada() {
		return idTurmaSelecionada;
	}

	public void setIdTurmaSelecionada(int idTurmaSelecionada) {
		this.idTurmaSelecionada = idTurmaSelecionada;
	}

	public int getIdDisciplinaSelecionada() {
		return idDisciplinaSelecionada;
	}

	public void setIdDisciplinaSelecionada(int idDisciplinaSelecionada) {
		this.idDisciplinaSelecionada = idDisciplinaSelecionada;
	}

	public int getIdPeriodoSelecionado() {
		return idPeriodoSelecionado;
	}

	public void setIdPeriodoSelecionado(int idPeriodoSelecionado) {
		this.idPeriodoSelecionado = idPeriodoSelecionado;
	}

	public List<PeriodoLetivo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<PeriodoLetivo> periodos) {
		this.periodos = periodos;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public int getIdAtividadeSelecionada() {
		return idAtividadeSelecionada;
	}

	public void setIdAtividadeSelecionada(int idAtividadeSelecionada) {
		this.idAtividadeSelecionada = idAtividadeSelecionada;
	}

	public List<AtividadeComEntrega> getAtvComEntregas() {
		return atvComEntregas;
	}

	public void setAtvComEntregas(List<AtividadeComEntrega> atvComEntregas) {
		this.atvComEntregas = atvComEntregas;
	}

	public List<AtividadeSemEntrega> getAtvSemEntregas() {
		return atvSemEntregas;
	}

	public void setAtvSemEntregas(List<AtividadeSemEntrega> atvSemEntregas) {
		this.atvSemEntregas = atvSemEntregas;
	}

	public List<AlunoTurma> getAlunoTurmaList() {
		return alunoTurmaList;
	}

	public void setAlunoTurmaList(List<AlunoTurma> alunoTurmaList) {
		this.alunoTurmaList = alunoTurmaList;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public int getIdAlunoTurmaSelecionado() {
		return idAlunoTurmaSelecionado;
	}

	public void setIdAlunoTurmaSelecionado(int idAlunoTurmaSelecionado) {
		this.idAlunoTurmaSelecionado = idAlunoTurmaSelecionado;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public List<Nota> getNotaList() {
		return notaList;
	}

	public void setNotaList(List<Nota> notaList) {
		this.notaList = notaList;
	}
	
	public void setDisciplinaTurmaList(List<Disciplina> disciplinaList) {
		this.disciplinaList = disciplinaList;
	}

	public List<Disciplina> getDisciplinaList() {
		return disciplinaList;
	}
	
	public void setDisciplinaList(List<Disciplina> disciplinaList) {
		this.disciplinaList = disciplinaList;
	}

	public List<Turma> getTurmasAluno() {
		return turmasAluno;
	}

	public void setTurmasAluno(List<Turma> turmasAluno) {
		this.turmasAluno = turmasAluno;
	}

	public List<DisciplinaNotas> getDisciplinaNotasList() {
		return disciplinaNotasList;
	}

	public void setDisciplinaNotasList(List<DisciplinaNotas> disciplinaNotasList) {
		this.disciplinaNotasList = disciplinaNotasList;
	}

	public boolean isPainel1() {
		return painel1;
	}

	public void setPainel1(boolean painel1) {
		this.painel1 = painel1;
	}

	public boolean isPainel2() {
		return painel2;
	}

	public void setPainel2(boolean painel2) {
		this.painel2 = painel2;
	}

	public List<ProfessorDisciplina> getProfessorDisciplinaList() {
		return professorDisciplinaList;
	}

	public void setProfessorDisciplinaList(
			List<ProfessorDisciplina> professorDisciplinaList) {
		this.professorDisciplinaList = professorDisciplinaList;
	}

	public List<ProfessorDisciplinaNota> getPdnList() {
		return pdnList;
	}

	public void setPdnList(List<ProfessorDisciplinaNota> pdnList) {
		this.pdnList = pdnList;
	}
	

}
