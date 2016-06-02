package control;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.aluno.AlunoRN;
import pojo.Aluno;
import pojo.Endereco;
import pojo.Sexo;
import pojo.Uf;

@ManagedBean
@RequestScoped
public class AlunoBean {
	
	private Aluno aluno = new Aluno();
	private List<Aluno> alunoList;
	
	//
	private Sexo sexo = new Sexo();
	private Endereco endereco = new Endereco();
	private Uf uf = new Uf();
	private int idSexo;
	private int idUf;
	
	public String novoAluno() {
		
		this.aluno = new Aluno();
		
		return "novoAluno.jsf?faces-redirect=true";
		
	}
	
	public String salvar() {
		
		AlunoRN alunoRN = new AlunoRN();
		
		this.sexo = alunoRN.buscarSexoPorId(this.idSexo);
		this.uf = alunoRN.buscarUfPorId(this.idUf);
		
		this.aluno.setSexo(this.sexo);
		/*this.endereco = this.aluno.addEndereco(this.endereco);
		this.endereco.setUf(this.uf);*/
		
		alunoRN.salvar(this.aluno);
		
		return "gerenciarAlunos.jsf?faces-redirect=true";
		
	}
	
	public String editar() {
		
		return "novoAluno";
		
	}
	
	public String excluir() {
		
		AlunoRN alunoRN = new AlunoRN();
		alunoRN.excluir(this.aluno);
		
		this.alunoList = null;
		
		return null;
		
	}
	
	public List<Aluno> getBuscarTodos() {
		
		if(this.alunoList == null) {
			
			AlunoRN alunoRN = new AlunoRN();
			this.alunoList = alunoRN.buscarTodos();
			
		}
		
		return alunoList;
	}
	
	public List<Sexo> getBuscarSexo() {
		
		AlunoRN alunoRN = new AlunoRN();
		return alunoRN.buscarSexo();
		
	}
	
	public List<Uf> getBuscarUf() {
		
		AlunoRN alunoRN = new AlunoRN();
		return alunoRN.buscarUf();
		
	}
	
	
	//
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public int getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(int idSexo) {
		this.idSexo = idSexo;
	}
	
	public void setAlunoList(List<Aluno> alunoList) {
		this.alunoList = alunoList;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public int getIdUf() {
		return idUf;
	}

	public void setIdUf(int idUf) {
		this.idUf = idUf;
	}


}
