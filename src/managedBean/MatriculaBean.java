package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import RN.AlunoRN;
import RN.AlunoTurmaRN;
import entities.Aluno;
import entities.AlunoTurma;
import entities.Turma;

@ManagedBean
@SessionScoped
public class MatriculaBean {
	
	private Aluno aluno = new Aluno();
	private Turma turma = new Turma();
	private AlunoTurma alunoTurma = new AlunoTurma();
	private List<Aluno> alunoList = new ArrayList<Aluno>();
	private List<Aluno> alunosCadastrados = new ArrayList<Aluno>();
	private List<Aluno> alunosNaoCadastrados = new ArrayList<Aluno>();
	
	public String addAlunos() {
		
		this.alunosCadastrados = null;
		this.alunosNaoCadastrados = null;
		return "addAlunos";
		
	}
	
	public void limparVariaveis() {
		this.alunoList = null;
		this.alunosCadastrados = null;
		this.alunosNaoCadastrados = null;
	}
	
	public void salvar() {
		
		AlunoTurmaRN atRN = new AlunoTurmaRN();
		this.alunoTurma.setTurma(this.turma);
		this.alunoTurma.setAluno(this.aluno);
		atRN.salvar(this.alunoTurma);
		
		this.alunoTurma = new AlunoTurma();
		this.aluno = new Aluno();
		this.alunosCadastrados = null;
		this.alunosNaoCadastrados = null;
		
	}
	
	public void excluir() {
		
		//System.out.println("Aluno: " + this.aluno.getIdPessoa() + " | Turma: " + this.turma.getDescricao());
		AlunoTurma at = new AlunoTurma();
		AlunoTurmaRN atRN = new AlunoTurmaRN();
		
		this.alunoTurma = atRN.buscarAlunoTurmaPorIdAlunoTurma(this.aluno.getIdPessoa(), this.turma.getIdTurma());
		atRN.excluir(this.alunoTurma);
		
		this.alunosCadastrados.remove(this.aluno);
		this.alunosNaoCadastrados.add(this.aluno);
		this.alunoTurma = new AlunoTurma();
		this.aluno = new Aluno();
		
	}
	
	/*public List<Aluno> getBuscarAlunosNaoCadastrados() {
		
			AlunoTurmaRN atRN = new AlunoTurmaRN();
			return atRN.buscarAlunosNaoCadastrados(this.turma.getIdTurma());
	}
	
	public List<Aluno> getBuscarAlunosCadastrados() {
		
			AlunoTurmaRN atRN = new AlunoTurmaRN();
			return atRN.buscarAlunosPorIdTurma(this.turma.getIdTurma());
		
	}*/
	
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

	public List<Aluno> getAlunosCadastrados() {
		if(this.alunosCadastrados == null) {
			AlunoTurmaRN atRN = new AlunoTurmaRN();
			this.alunosCadastrados = atRN.buscarAlunosPorIdTurma(this.turma.getIdTurma());
		}
		return this.alunosCadastrados;
	}

	public void setAlunosCadastrados(List<Aluno> alunosCadastrados) {
		this.alunosCadastrados = alunosCadastrados;
	}

	public List<Aluno> getAlunosNaoCadastrados() {
		if(this.alunosNaoCadastrados == null) {
			AlunoTurmaRN atRN = new AlunoTurmaRN();
			this.alunosNaoCadastrados = atRN.buscarAlunosNaoCadastrados(this.turma.getIdTurma());
		}
		return this.alunosNaoCadastrados;
	}

	public void setAlunosNaoCadastrados(List<Aluno> alunosNaoCadastrados) {
		this.alunosNaoCadastrados = alunosNaoCadastrados;
	}

}
