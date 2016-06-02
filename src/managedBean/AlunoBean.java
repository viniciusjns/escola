package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import util.MensagemUtil;
import util.ValidaCpf;
import RN.AlunoRN;
import RN.EnderecoRN;
import RN.PermissaoRN;
import RN.UsuarioRN;
import entities.Aluno;
import entities.Endereco;
import entities.Permissao;
import entities.Sexo;
import entities.Telefone;
import entities.Uf;
import entities.Usuario;

@ManagedBean
@RequestScoped
public class AlunoBean {
	
	private Aluno aluno = new Aluno();
	private Endereco endereco = new Endereco();
	private Sexo sexo = new Sexo();
	private Uf uf = new Uf();
	private Telefone telefone1 = new Telefone();
	private Telefone telefone2 = new Telefone();
	
	private int idSexo;
	private int idUf;
	
	private List<Aluno> alunoList;
	private List<Aluno> filteredAluno;
	private List<Usuario> usuarioList;
	
	private MensagemUtil msg = new MensagemUtil();
	private ValidaCpf valida = new ValidaCpf();
	
	
	public String novoAluno() {
		
		this.aluno = new Aluno();
		this.endereco = new Endereco();
		this.sexo = new Sexo();
		this.uf = new Uf();
		this.telefone1 = new Telefone();
		this.telefone2 = new Telefone();
		
		return "novoAluno.jsf?faces-redirect=true";
		
	}
	
	public String cancelar() {
		
		this.aluno = null;
		
		return "gerenciarAlunos.jsf?faces-redirect=true";
		
	}
	
	public String salvar() {
		
		AlunoRN alunoRN = new AlunoRN();
		this.sexo = alunoRN.buscarSexoPorId(this.idSexo);
		
		EnderecoRN enderecoRN = new EnderecoRN();
		this.uf = enderecoRN.buscarUfPorId(idUf);
		
		this.endereco.setUf(this.uf);
		
		this.aluno.setEndereco(this.endereco);
		this.aluno.addTelefone(this.telefone1);
		this.aluno.addTelefone(this.telefone2);
		this.aluno.setSexo(this.sexo);
		
		
		if(alunoRN.salvar(this.aluno)) {
			this.aluno = new Aluno();
			this.alunoList = null;
			
			return "gerenciarAlunos.jsf?faces-redirect=true";
			
		} else {

			return null;
		
		}
	}
	
	public String editar() {
		
		this.idSexo = this.aluno.getSexo().getIdSexo();
		this.idUf = this.endereco.getUf().getIdUf();
		this.telefone1 = this.aluno.getTelefones().get(0);
		this.telefone2 = this.aluno.getTelefones().get(1);
		
		return "novoAluno.jsf";
		
	}
	
	public void excluir() {
		
		/*UsuarioRN usuarioRN = new UsuarioRN();
		this.usuarioList = usuarioRN.buscarPorIdPessoa(this.aluno.getIdPessoa());
		
		for(int i = 0; i < this.usuarioList.size(); i++) {
			
			usuarioRN.excluir(this.usuarioList.get(i));
			
		}*/
		
		AlunoRN alunoRN = new AlunoRN();
		alunoRN.excluir(this.aluno);
		
		this.aluno = new Aluno();
		this.alunoList = null;
		
	}
	
	public List<Aluno> getBuscarTodos() {
		
		if(this.alunoList == null || this.alunoList.isEmpty()) {
			
			AlunoRN alunoRN = new AlunoRN();
			this.alunoList = alunoRN.buscarTodos();
			
		}
		
		return this.alunoList;
	}
	
	public List<Sexo> getBuscarSexo() {
		
		AlunoRN alunoRN = new AlunoRN();
		return alunoRN.buscarSexo();
		
	}
	

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public Telefone getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(Telefone telefone1) {
		this.telefone1 = telefone1;
	}

	public Telefone getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(Telefone telefone2) {
		this.telefone2 = telefone2;
	}

	public int getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(int idSexo) {
		this.idSexo = idSexo;
	}

	public int getIdUf() {
		return idUf;
	}

	public void setIdUf(int idUf) {
		this.idUf = idUf;
	}

	public List<Aluno> getAlunoList() {
		return this.alunoList;
	}

	public void setAlunoList(List<Aluno> alunoList) {
		this.alunoList = alunoList;
	}

	public List<Aluno> getFilteredAluno() {
		return filteredAluno;
	}

	public void setFilteredAluno(List<Aluno> filteredAluno) {
		this.filteredAluno = filteredAluno;
	}	
	

}
