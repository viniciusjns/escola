package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.UploadedFile;

import util.ContextoUtil;
import util.UploadArquivo;
import RN.ProfessorTurmaRN;
import entities.Atividade;
import entities.Disciplina;
import entities.Professor;
import entities.Turma;

@ManagedBean
@SessionScoped
public class AtividadeBean {
	
	private Atividade atividade = new Atividade();
	
	private int idTurmaSelecionada = 0;
	private int idDisciplinaSelecionada = 0;
	private int idTipoAtividade = 0;
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private List<Turma> turmas = new ArrayList<Turma>();
	
	ContextoBean contextoBean = ContextoUtil.getContextoBean();
	
	private UploadArquivo uploadArquivo;
	private UploadedFile uploadedFile;
	
	public String novaAtividade() {
		
		this.limparTurma();
		this.idTurmaSelecionada = 0;
		this.atividade = new Atividade();
		this.uploadArquivo = new UploadArquivo();
		
		return "novaAtividade";
		
	}
	
	private void limparTurma() {
		
		for(int i = 0; i < this.turmas.size(); i++) {
			this.turmas.remove(i);
		}
		this.turmas = null;
	}
	
	public void salvar() {
		
		/*ProfessorDisciplinaRN pdRN = new ProfessorDisciplinaRN();
		ProfessorDisciplina pd = pdRN.buscarProfessorDisciplinaPorIdProfessorDisciplina(this.contextoBean.getProfessorLogado().getIdPessoa(), this.idDisciplinaSelecionada);
		
		TurmaRN turmaRN = new TurmaRN();
		Turma t = turmaRN.buscarPorId(this.idTurmaSelecionada);
		
		AtividadeRN atividadeRN = new AtividadeRN();
		TipoAtividade ta = atividadeRN.buscarTipoAtividadePorId(this.idTipoAtividade);
		
		this.atividade.setProfessorDisciplina(pd);
		this.atividade.setTurma(t);
		this.atividade.setTipoAtividade(ta);
		atividadeRN.salvar(this.atividade);
		
		if(this.uploadedFile != null) {
			
			this.uploadArquivo.fileUpload(this.atividade.getIdAtividade(), uploadedFile, "/atividade/");
	        this.uploadArquivo.gravar();
	        
	        Atividade a = atividadeRN.buscarPorId(this.atividade.getIdAtividade());
	        a.setCaminhoArquivo(this.uploadArquivo.getNome());
	        atividadeRN.salvar(a);
		}*/
		
		if(this.uploadedFile != null)
			System.out.println("nao nulo");
		else
			System.out.println("nulo");
		
        //return "gerenciarAtividades.jsf?faces-redirect=true";
	}
	
	public void teste() {
		this.uploadedFile = null;
		if(this.uploadedFile == null)
			System.out.println("nulo");
		else
			System.out.println("nao nulo");
	}
	
	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public int getIdTipoAtividade() {
		return idTipoAtividade;
	}
	
	public void setIdTipoAtividade(int idTipoAtividade) {
		this.idTipoAtividade = idTipoAtividade;
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
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	/******************************************************************************************************/
	/*public List<Disciplina> getBuscarDisciplinas() {
		
		ProfessorDisciplinaRN pdRN = new ProfessorDisciplinaRN();
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		Professor professor = contextoBean.getProfessorLogado();
		return pdRN.buscarDisciplinasPorIdProfessor(professor.getIdPessoa());
		
		
	}*/
	
	public List<Turma> getBuscarTurmas() {
		
		//if(this.turmas == null) {
			ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
			//ContextoBean contextoBean = ContextoUtil.getContextoBean();
			Professor professor = contextoBean.getProfessorLogado();
			this.turmas =  ptRN.buscarTurmasPorIdProfessor(professor.getIdPessoa());
		//}
		return this.turmas;
		
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
	

}
