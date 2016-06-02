package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import util.AlunoDataModel;
import util.ContextoUtil;
import util.MensagemUtil;
import RN.AlunoRN;
import RN.AlunoTurmaRN;
import entities.Aluno;
import entities.AlunoTurma;
import entities.Turma;

@ManagedBean
@SessionScoped
public class AlunoTurmaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Aluno aluno = new Aluno();
	private Turma turma = new Turma();
	private AlunoTurma alunoTurma = new AlunoTurma();
	private List<Aluno> alunoList = new ArrayList<Aluno>();
	private List<AlunoTurma> alunosCadastrados = new ArrayList<AlunoTurma>();
	private List<Aluno> alunosNaoCadastrados = new ArrayList<Aluno>();
	
	private AlunoDataModel alunoDataModel;
	
	private MensagemUtil msg = new MensagemUtil();
	
	public AlunoTurmaBean() {
		
		//this.alunoDataModel = new AlunoDataModel(this.buscarAlunosNaoCadastrados());
		
	}
	
	public String addAlunos() {
		
		limparVariaveis();
		//System.out.println(this.turma.getIdTurma());
		this.alunoDataModel = new AlunoDataModel(this.getAlunosNaoCadastrados());
		return "adicionarAlunos.jsf?faces-redirect=true";
		
	}
	
	public void limparVariaveis() {
		this.alunoList = null;
		this.alunosCadastrados = null;
		this.alunosNaoCadastrados = null;
		this.alunoDataModel = null;
	}
	
	public void salvar() {
		
		try {
			AlunoTurmaRN atRN = new AlunoTurmaRN();
			for(int i = 0; i < this.alunoList.size(); i++) {
				this.alunoTurma.setTurma(this.turma);
				this.alunoTurma.setAluno(this.alunoList.get(i));
				this.alunoTurma.setAtivo(true);
				atRN.salvar(this.alunoTurma);
				
				this.alunoTurma = new AlunoTurma();
				this.alunosCadastrados = null;
				this.alunosNaoCadastrados = null;
				this.alunoDataModel = new AlunoDataModel(this.getAlunosNaoCadastrados());
			}
			this.msg.msgSucesso("Registro(s) inserido(s) com sucesso!");
		} catch(Exception ex) {
			this.msg.msgErro("Erro ao inserir. Informe o administrador do sistema!" + ex);
		}
		
		
		
	}
	
	public void excluir() {
		
		AlunoTurmaRN atRN = new AlunoTurmaRN();
		
		this.alunoTurma = atRN.buscarAlunoTurmaPorIdAlunoTurma(this.aluno.getIdPessoa(), this.turma.getIdTurma());
		atRN.excluir(this.alunoTurma);
		
		this.alunosCadastrados = null;
		this.alunosNaoCadastrados = null;
		this.alunoTurma = new AlunoTurma();
		this.aluno = new Aluno();
		this.alunoDataModel = new AlunoDataModel(this.getAlunosNaoCadastrados());
		
	}
	
	public void ativarDesativar() {
		
		if(this.alunoTurma.isAtivo())
			this.alunoTurma.setAtivo(false);
		else
			this.alunoTurma.setAtivo(true);
		
		AlunoTurmaRN atRN = new AlunoTurmaRN();
		atRN.salvar(this.alunoTurma);
		
	}
	
	public List<Aluno> getAlunosNaoCadastrados() {
		
		if(this.alunosNaoCadastrados == null) {
			AlunoTurmaRN atRN = new AlunoTurmaRN();
			this.alunosNaoCadastrados = atRN.buscarAlunosNaoCadastrados(this.turma.getIdTurma());
		}
		
		return this.alunosNaoCadastrados;
	}
	
	public List<AlunoTurma> getAlunosCadastrados() {

		if(this.alunosCadastrados == null) {
			AlunoTurmaRN atRN = new AlunoTurmaRN();
			this.alunosCadastrados = atRN.buscarAlunoTurmaPorIdTurma(this.turma.getIdTurma());
		}
		return this.alunosCadastrados;
		
	}
	
	public void imprimiLista() {
		
		System.out.println(this.alunosCadastrados.size());
		
	}
	
	public void escreverAlunos() {
		
		for(int i = 0; i < this.alunoList.size(); i++) {
			System.out.println("Nome: " + alunoList.get(i).getNome());
		}
		
	}

	
	//
	public AlunoTurma getAlunoTurma() {
		return alunoTurma;
	}

	public void setAlunoTurma(AlunoTurma alunoTurma) {
		this.alunoTurma = alunoTurma;
	}
	
	public List<Aluno> buscarTodosAlunos() {
		AlunoRN alunoRN = new AlunoRN();
		return alunoRN.buscarTodos();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
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

}
