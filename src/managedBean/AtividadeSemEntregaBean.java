package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import util.ContextoUtil;
import RN.AtividadeSemEntregaRN;
import RN.PeriodoLetivoRN;
import RN.ProfessorDisciplinaRN;
import RN.ProfessorTurmaRN;
import RN.TurmaRN;
import entities.AtividadeSemEntrega;
import entities.Disciplina;
import entities.PeriodoLetivo;
import entities.ProfessorDisciplina;
import entities.ProfessorTurma;
import entities.Turma;

@ManagedBean
@SessionScoped
public class AtividadeSemEntregaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private AtividadeSemEntrega atividadeSemEntrega = new AtividadeSemEntrega();
	
	private int idTurmaSelecionada;
	private int idDisciplinaSelecionada;
	private int idPeriodoSelecionado;
	private int idAnoSelecionado;
	
	private List<PeriodoLetivo> periodos;
	private List<Disciplina> disciplinas;
	private List<Turma> turmas;
	private List<AtividadeSemEntrega> atividades;
	private List<AtividadeSemEntrega> filteredAtividade;
	
	private ProfessorDisciplina pd = new ProfessorDisciplina();
	private Turma t = new Turma();
	private ProfessorTurma pt = new ProfessorTurma();
	private PeriodoLetivo pl = new PeriodoLetivo();
	
	private ContextoBean contextoBean = ContextoUtil.getContextoBean();
	
	public String novaAtividade() {
		
		//this.limparTurma();
		this.inicializarVariaveis();
		this.atividades = null;
		this.atividadeSemEntrega = new AtividadeSemEntrega();
		
		return "novaAtividadeSemEntrega.jsf?faces-redirect=true";
		
	}
	
	public String cancelar() {
		
		this.atividadeSemEntrega = null;
		
		return "gerenciarAtividades.jsf?faces-redirect=true";
	}
	
	private void inicializarVariaveis() {
		
		this.atividadeSemEntrega = new AtividadeSemEntrega();
		this.t = new Turma();
		this.pl = new PeriodoLetivo();
		this.pd = new ProfessorDisciplina();
		
		this.idDisciplinaSelecionada = 0;
		this.idPeriodoSelecionado = 0;
		this.idTurmaSelecionada = 0;
		
		this.turmas = null;
		this.disciplinas = null;
		this.atividades = null;
		
	}
	
	public String salvar() {
		
		ProfessorDisciplinaRN pdRN = new ProfessorDisciplinaRN();
		this.pd = pdRN.buscarProfessorDisciplinaPorIdProfessorDisciplina(this.contextoBean.getProfessorLogado().getIdPessoa(), this.idDisciplinaSelecionada);
		
		TurmaRN turmaRN = new TurmaRN();
		this.t = turmaRN.buscarPorId(this.idTurmaSelecionada);
		
		ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
		this.pt = ptRN.buscarProfessorTurmaIdProfessorDisciplinaIdTurma(this.pd.getIdProfessorDisciplina(), this.t.getIdTurma());
		
		PeriodoLetivoRN periodoRN = new PeriodoLetivoRN();
		this.pl = periodoRN.buscarPorId(this.idPeriodoSelecionado);
		
		AtividadeSemEntregaRN atividadeRN = new AtividadeSemEntregaRN();
		
		this.atividadeSemEntrega.setProfessorTurma(this.pt);
		this.atividadeSemEntrega.setPeriodoLetivo(this.pl);
		//this.atividadeSemEntrega.setData(this.data);
		atividadeRN.salvar(this.atividadeSemEntrega);
		
		this.inicializarVariaveis();
		
        return "gerenciarAtividades.jsf?faces-redirect=true";
	}
	
	public String editar() {
		
		this.setIdAnoSelecionado(this.atividadeSemEntrega.getPeriodoLetivo().getAno().getIdAno());
		this.setIdDisciplinaSelecionada(this.atividadeSemEntrega.getProfessorTurma().getProfessorDisciplina().getDisciplina().getIdDisciplina());
		this.setIdTurmaSelecionada(this.atividadeSemEntrega.getProfessorTurma().getTurma().getIdTurma());
		this.setIdPeriodoSelecionado(this.atividadeSemEntrega.getPeriodoLetivo().getIdPeriodoLetivo());
		//this.setData(this.atividadeSemEntrega.getData());
		this.trocarDisciplina();
		this.trocarPeriodoLetivo();
		this.trocarTurmaProfessor();
		
		return "novaAtividadeSemEntrega";
		
	}
	
	public String excluir() {
		
		AtividadeSemEntregaRN atividadeRN = new AtividadeSemEntregaRN();
		atividadeRN.excluir(this.atividadeSemEntrega);
		
		this.atividades = null;
		
		return null;
		
	}
	
	public List<AtividadeSemEntrega> getBuscarTodos() {
		
		if(this.atividades == null) {
			
			AtividadeSemEntregaRN atividadeRN = new AtividadeSemEntregaRN();
			this.atividades = atividadeRN.buscarAtividadesPorIdProfessor(this.contextoBean.getProfessorLogado().getIdPessoa());
		
		}
		
		return this.atividades;
	}

	public void trocarDisciplina() {
		
        if((Integer)idTurmaSelecionada != null && idTurmaSelecionada != 0) {  
            ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
            disciplinas = ptRN.buscarDisciplinasProfessorTurma(this.contextoBean.getProfessorLogado().getIdPessoa(), this.idTurmaSelecionada);
        	//disciplinas = disciplinaData.get(idProfessor);
        }
        else  
            disciplinas = new ArrayList<Disciplina>();
        
    }
	
	public void trocarPeriodoLetivo() {
		
		if((Integer)idAnoSelecionado != null && idAnoSelecionado != 0) {
			PeriodoLetivoRN periodoRN = new PeriodoLetivoRN();
			periodos = periodoRN.buscarPorIdAno(this.idAnoSelecionado);
		} else {
			this.periodos = new ArrayList<PeriodoLetivo>();
		}
		
	}
	
	public void trocarTurmaProfessor() {
		
		if((Integer)idAnoSelecionado != null && idAnoSelecionado != 0) {
			ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
			this.turmas =  ptRN.buscarTurmasPorIdProfessorIdAno(this.contextoBean.getProfessorLogado().getIdPessoa(), this.idAnoSelecionado);
		} else {
			System.out.println("entrou2");
			this.turmas = new ArrayList<Turma>();
		}
		
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

	public int getIdTurmaSelecionada() {
		return idTurmaSelecionada;
	}

	public void setIdTurmaSelecionada(int idTurmaSelecionada) {
		this.idTurmaSelecionada = idTurmaSelecionada;
	}

	public AtividadeSemEntrega getAtividadeSemEntrega() {
		return atividadeSemEntrega;
	}

	public void setAtividadeSemEntrega(AtividadeSemEntrega atividadeSemEntrega) {
		this.atividadeSemEntrega = atividadeSemEntrega;
	}

	public int getIdPeriodoSelecionado() {
		return idPeriodoSelecionado;
	}

	public void setIdPeriodoSelecionado(int idPeriodoSelecionado) {
		this.idPeriodoSelecionado = idPeriodoSelecionado;
	}

	public List<AtividadeSemEntrega> getFilteredAtividade() {
		return filteredAtividade;
	}

	public void setFilteredAtividade(List<AtividadeSemEntrega> filteredAtividade) {
		this.filteredAtividade = filteredAtividade;
	}

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

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	

}
