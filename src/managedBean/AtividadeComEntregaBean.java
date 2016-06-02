package managedBean;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import util.ContextoUtil;
import util.UploadArquivo;
import RN.AlunoTurmaRN;
import RN.AtividadeComEntregaRN;
import RN.EntregaRN;
import RN.PeriodoLetivoRN;
import RN.ProfessorDisciplinaRN;
import RN.ProfessorTurmaRN;
import RN.TurmaRN;
import entities.AlunoTurma;
import entities.AtividadeComEntrega;
import entities.Disciplina;
import entities.PeriodoLetivo;
import entities.ProfessorDisciplina;
import entities.ProfessorTurma;
import entities.Turma;

@ManagedBean
@SessionScoped
public class AtividadeComEntregaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final int EM_ANDAMENTO = 1;
	private final int ENCERRADO = 2;
	
	private AtividadeComEntrega atividadeComEntrega = new AtividadeComEntrega();
	private AtividadeComEntrega atividadeSelecionada = new AtividadeComEntrega();
	
	private int idAnoSelecionado;
	private int idTurmaSelecionada;
	private int idDisciplinaSelecionada;
	private int idPeriodoSelecionado;
	private int idSituacao;
	
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private List<Turma> turmas = new ArrayList<Turma>();
	private List<Turma> alunoTurmas = new ArrayList<Turma>();
	private List<PeriodoLetivo> periodos = new ArrayList<PeriodoLetivo>();
	
	private List<AtividadeComEntrega> atividades;
	private List<AtividadeComEntrega> atividadesComEntrega;
	private List<AtividadeComEntrega> filteredAtividade;
	
	private ProfessorDisciplina pd = new ProfessorDisciplina();
	private Turma t = new Turma();
	private PeriodoLetivo pl = new PeriodoLetivo();
	private ProfessorTurma pt = new ProfessorTurma();
	
	private Date dataInicio = new Date();
	private Date horaInicio = new Date();
	private ContextoBean contextoBean = ContextoUtil.getContextoBean();
	private UploadArquivo uploadArquivo = new UploadArquivo();
	private UploadedFile uploadedFile = null;
	private StreamedContent scfile;
	
	public String novaAtividade() {
		
		this.uploadedFile = null;
		this.idTurmaSelecionada = 0;
		this.atividadeComEntrega = new AtividadeComEntrega();
		
		return "novaAtividadeComEntrega.jsf?faces-redirect=true";
		
	}
	
	public String cancelar() {
		
		this.atividadeComEntrega = null;
		
		return "gerenciarAtividades.jsf?faces-redirect=true";
	}
	
	private void inicializarVariaveis() {
		
		this.atividadeComEntrega = new AtividadeComEntrega();
		this.t = new Turma();
		this.pl = new PeriodoLetivo();
		this.pd = new ProfessorDisciplina();
		this.dataInicio = new Date();
		this.horaInicio = new Date();
		
		this.idDisciplinaSelecionada = 0;
		this.idPeriodoSelecionado = 0;
		this.idTurmaSelecionada = 0;
		this.idSituacao = 0;
		
		this.turmas = null;
		this.disciplinas = null;
		this.atividades = null;
		this.atividadesComEntrega = null;
		
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
		
		AtividadeComEntregaRN atividadeRN = new AtividadeComEntregaRN();
		
		this.atividadeComEntrega.setProfessorTurma(this.pt);
		this.atividadeComEntrega.setPeriodoLetivo(this.pl);
		this.atividadeComEntrega.setDataInicio(this.dataInicio);
		this.atividadeComEntrega.setHoraInicio(this.horaInicio);
		//this.atividadeComEntrega.setCaminhoArquivo(null);
		atividadeRN.salvar(this.atividadeComEntrega);
		
		System.out.println(this.uploadedFile.getSize());
		
		if(this.uploadedFile.getSize() != 0) {
			
			this.uploadArquivo = new UploadArquivo();
			this.uploadArquivo.fileUpload(this.atividadeComEntrega.getIdAtividade(), uploadedFile, "/atividade/");
	        this.uploadArquivo.gravar();
	        
	        AtividadeComEntrega a = atividadeRN.buscarPorId(this.atividadeComEntrega.getIdAtividade());
	        a.setCaminhoArquivo(this.uploadArquivo.getNome());
	        atividadeRN.salvar(a);
		}
		
		this.inicializarVariaveis();
		
        return "gerenciarAtividades.jsf?faces-redirect=true";
	}
	
	public String editar() {
		
		this.setIdAnoSelecionado(this.atividadeComEntrega.getPeriodoLetivo().getAno().getIdAno());
		this.setIdDisciplinaSelecionada(this.atividadeComEntrega.getProfessorTurma().getProfessorDisciplina().getDisciplina().getIdDisciplina());
		this.setIdTurmaSelecionada(this.atividadeComEntrega.getProfessorTurma().getTurma().getIdTurma());
		this.setIdPeriodoSelecionado(this.atividadeComEntrega.getPeriodoLetivo().getIdPeriodoLetivo());
		this.setDataInicio(this.atividadeComEntrega.getDataInicio());
		this.setHoraInicio(this.atividadeComEntrega.getHoraInicio());
		
		this.trocarDisciplina();
		this.trocarPeriodoLetivo();
		this.trocarTurmaProfessor();
		
		return "novaAtividadeComEntrega";
		
	}
	
	public String excluir() {
		
		AtividadeComEntregaRN atividadeRN = new AtividadeComEntregaRN();
		atividadeRN.excluir(this.atividadeComEntrega);
		
		File file = new File(this.getRealPath() + "/atividade/" + this.atividadeComEntrega.getIdAtividade());
		
		if(this.deleteDir(file))
			System.out.println("sucesso");
		else
			System.out.println("erro");
		
		this.atividades = null;
		this.atividadeComEntrega = null;
		
		return null;
		
	}
	
	public List<AtividadeComEntrega> getAtividadesComEntrega() {
		
		return this.atividadesComEntrega;
		
	}
	
	public void buscarAtividades() {
		
		this.atividadesComEntrega = null;
		if(this.idSituacao == this.EM_ANDAMENTO) {
		
			if(this.atividadesComEntrega == null) {
				AtividadeComEntregaRN atividadeRN = new AtividadeComEntregaRN();
				this.atividadesComEntrega = atividadeRN.buscarAtividadesEmAndamento(idTurmaSelecionada, idPeriodoSelecionado);
				this.setSituacao(true);
			}
			
		} else if(this.idSituacao == this.ENCERRADO) {
			
			if(this.atividadesComEntrega == null) {
				AtividadeComEntregaRN atividadeRN = new AtividadeComEntregaRN();
				this.atividadesComEntrega = atividadeRN.buscarAtividadesEncerradas(idTurmaSelecionada, idPeriodoSelecionado);
				this.setSituacao(false);
			}
			
		} else {
			this.atividadeComEntrega = null;
		}
		
	}
	
	private void setSituacao(boolean b) {
		
		for(int i = 0; i < this.atividadesComEntrega.size(); i++) {
			this.atividadesComEntrega.get(i).setEmAndamento(b);
		}
		
	}
	
	public List<AtividadeComEntrega> getBuscarTodos() {
		
		if(this.atividades == null) {
			
			AtividadeComEntregaRN atividadeRN = new AtividadeComEntregaRN();
			this.atividades = atividadeRN.buscarAtividadesPorIdProfessor(this.contextoBean.getProfessorLogado().getIdPessoa());
			
		}
		
		return this.atividades;
		
	}

	public AtividadeComEntrega getAtividadeComEntrega() {
		return atividadeComEntrega;
	}

	public void setAtividadeComEntrega(AtividadeComEntrega atividadeComEntrega) {
		this.atividadeComEntrega = atividadeComEntrega;
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
			periodos = new ArrayList<PeriodoLetivo>();
		}
		
	}
	
	public void trocarTurmaProfessor() {
		
		if((Integer)idAnoSelecionado != null && idAnoSelecionado != 0) {
			ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
			this.turmas =  ptRN.buscarTurmasPorIdProfessorIdAno(this.contextoBean.getProfessorLogado().getIdPessoa(), this.idAnoSelecionado);
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
	
	public boolean isPodeEntregar() {
		
		AlunoTurmaRN atRN = new AlunoTurmaRN();
		AlunoTurma alunoTurma = atRN.buscarAlunoTurmaPorIdAlunoTurma(this.contextoBean.getAlunoLogado().getIdPessoa(), this.atividadeSelecionada.getProfessorTurma().getTurma().getIdTurma());
		
		return alunoTurma.isAtivo();
		
	}
	
	/*-----------------------------------------------------------------*/
	
	/* METODOS PARA MANUSEIO DE UPLOAD DE ARQUIVO */
	public StreamedContent getScFile() {
    	InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).
    			getResourceAsStream("/atividade/" + this.atividadeSelecionada.getIdAtividade() + "/" + this.atividadeSelecionada.getCaminhoArquivo());
		scfile = new DefaultStreamedContent(stream, null, this.atividadeSelecionada.getCaminhoArquivo());
    	
    	return this.scfile;
    }
	
	private boolean deleteDir(File dir) {  
	    if (dir.isDirectory()) {  
	        String[] children = dir.list();  
	        for (int i = 0; i < children.length; i++) {  
	            boolean success = deleteDir(new File(dir, children[i]));  
	            if (!success) {  
	                return false;  
	            }  
	        }  
	    }
	  
	    // The directory is now empty so delete it  
	    return dir.delete();  
	}
	
	private String getRealPath() {
    	ServletContext sContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();

        return sContext.getRealPath("/");
    }
	/*--------------------------------------------*/
	
	
	/* GETS E SETS */
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

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public int getIdPeriodoSelecionado() {
		return idPeriodoSelecionado;
	}

	public void setIdPeriodoSelecionado(int idPeriodoSelecionado) {
		this.idPeriodoSelecionado = idPeriodoSelecionado;
	}

	public void setAtividades(List<AtividadeComEntrega> atividades) {
		this.atividades = atividades;
	}

	public List<AtividadeComEntrega> getFilteredAtividade() {
		return filteredAtividade;
	}

	public void setFilteredAtividade(List<AtividadeComEntrega> filteredAtividade) {
		this.filteredAtividade = filteredAtividade;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(int idSituacao) {
		this.idSituacao = idSituacao;
	}

	public AtividadeComEntrega getAtividadeSelecionada() {
		return atividadeSelecionada;
	}

	public void setAtividadeSelecionada(AtividadeComEntrega atividadeSelecionada) {
		this.atividadeSelecionada = atividadeSelecionada;
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

	public List<Turma> getAlunoTurmas() {
		return alunoTurmas;
	}

	public void setAlunoTurmas(List<Turma> alunoTurmas) {
		this.alunoTurmas = alunoTurmas;
	}	

}
