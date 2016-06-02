package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import RN.AlunoRN;
import RN.EnderecoRN;
import RN.SecretariaRN;
import RN.UsuarioRN;
import entities.Endereco;
import entities.Pessoa;
import entities.Sexo;
import entities.Telefone;
import entities.Uf;
import entities.Usuario;

@ManagedBean
@RequestScoped
public class SecretariaBean {
	
	private Pessoa secretaria = new Pessoa();
	private Endereco endereco = new Endereco();
	private Sexo sexo = new Sexo();
	private Uf uf = new Uf();
	private Telefone telefone1 = new Telefone();
	private Telefone telefone2 = new Telefone();
	
	private int idSexo;
	private int idUf;
	
	private List<Pessoa> secretariaList;
	private List<Pessoa> filteredSecretaria;
	private List<Usuario> usuarioList;
	
	
	public String novaSecretaria() {
		
		this.secretaria = new Pessoa();
		this.endereco = new Endereco();
		this.sexo = new Sexo();
		this.uf = new Uf();
		this.telefone1 = new Telefone();
		this.telefone2 = new Telefone();
		
		return "novaSecretaria.jsf?faces-redirect=true";
		
	}
	
	public String cancelar() {
		
		this.secretaria = null;
		this.endereco = null;
		this.sexo = null;
		this.uf = null;
		this.telefone1 = null;
		this.telefone2 = null;
		
		return "gerenciarSecretarias.jsf?faces-redirect=true";
	}
	
	public String salvar() {
		
		SecretariaRN secretariaRN = new SecretariaRN();
		this.sexo = secretariaRN.buscarSexoPorId(this.idSexo);
		
		EnderecoRN enderecoRN = new EnderecoRN();
		this.uf = enderecoRN.buscarUfPorId(idUf);
		
		this.endereco.setUf(this.uf);
		
		this.secretaria.setEndereco(this.endereco);
		this.secretaria.addTelefone(this.telefone1);
		this.secretaria.addTelefone(this.telefone2);
		this.secretaria.setSexo(this.sexo);
		
		if(secretariaRN.salvar(this.secretaria)) {
		
			this.secretaria = new Pessoa();
			this.secretariaList = null;
			
			return "gerenciarSecretarias.jsf?faces-redirect=true";
		} else {
			
			return null;
			
		}
		
	}
	
	public String editar() {
		
		this.idSexo = this.secretaria.getSexo().getIdSexo();
		this.idUf = this.endereco.getUf().getIdUf();
		/*this.telefone1.setNumero(this.secretaria.getTelefones().get(0).getNumero());
		this.telefone2.setNumero(this.secretaria.getTelefones().get(1).getNumero());*/
		
		return "novaSecretaria.jsf";
		
	}
	
	public void excluir() {
		
		/*UsuarioRN usuarioRN = new UsuarioRN();
		this.usuarioList = usuarioRN.buscarPorIdPessoa(this.secretaria.getIdPessoa());
		
		for(int i = 0; i < this.usuarioList.size(); i++) {
			
			usuarioRN.excluir(this.usuarioList.get(i));
			
		}*/
		
		SecretariaRN secretariaRN = new SecretariaRN();
		secretariaRN.excluir(this.secretaria);
		
		this.secretaria = new Pessoa();
		this.secretariaList = null;
		
	}
	
	public List<Pessoa> getBuscarTodos() {
		
		if(this.secretariaList == null || this.secretariaList.isEmpty()) {
			
			SecretariaRN secretariaRN = new SecretariaRN();
			this.secretariaList = secretariaRN.buscarTodos();
			
		}
		
		return this.secretariaList;
	}
	
	public List<Sexo> getBuscarSexo() {
		
		AlunoRN alunoRN = new AlunoRN();
		return alunoRN.buscarSexo();
		
	}
	

	public Pessoa getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Pessoa secretaria) {
		this.secretaria = secretaria;
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

	public List<Pessoa> getSecretariaList() {
		return this.secretariaList;
	}

	public void setSecretariaList(List<Pessoa> secretariaList) {
		this.secretariaList = secretariaList;
	}

	public List<Pessoa> getFilteredSecretaria() {
		return filteredSecretaria;
	}

	public void setFilteredSecretaria(List<Pessoa> filteredSecretaria) {
		this.filteredSecretaria = filteredSecretaria;
	}	
	

}
