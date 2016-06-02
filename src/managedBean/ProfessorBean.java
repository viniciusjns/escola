package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import RN.EnderecoRN;
import RN.ProfessorRN;
import RN.UsuarioRN;
import entities.Endereco;
import entities.Graduacao;
import entities.Professor;
import entities.Sexo;
import entities.Telefone;
import entities.Uf;
import entities.Usuario;

@ManagedBean
@RequestScoped
public class ProfessorBean {
	
	private Professor professor = new Professor();
	private Endereco endereco = new Endereco();
	private Sexo sexo = new Sexo();
	private Uf uf = new Uf();
	private Telefone telefone1 = new Telefone();
	private Telefone telefone2 = new Telefone();
	private Graduacao graduacao = new Graduacao();
	
	private int idSexo;
	private int idUf;
	private int idGraduacao;
	
	private List<Professor> professorList;
	private List<Professor> filteredProfessor;
	private SelectItem[] filteredGraduacao;
	private List<Usuario> usuarioList;
	
	
	public ProfessorBean() {
		
		ProfessorRN professorRN = new ProfessorRN();
		this.filteredGraduacao = this.createFilterOptions(professorRN.buscarGraduacao());
		
	}
	
	
	public String novoProfessor() {
		
		this.professor = new Professor();
		this.endereco = new Endereco();
		this.sexo = new Sexo();
		this.uf = new Uf();
		this.telefone1 = new Telefone();
		this.telefone2 = new Telefone();
		
		return "novoProfessor.jsf?faces-redirect=true";
		
	}
	
	public String cancelar() {
		
		this.professor = null;
		
		return "gerenciarProfessores.jsf?faces-redirect=true";
		
	}
	
	public String salvar() {
		
		ProfessorRN professorRN = new ProfessorRN();
		this.sexo = professorRN.buscarSexoPorId(this.idSexo);
		this.graduacao = professorRN.buscarGraduacaoPorId(this.idGraduacao);
		
		EnderecoRN enderecoRN = new EnderecoRN();
		this.uf = enderecoRN.buscarUfPorId(idUf);
		
		this.endereco.setUf(this.uf);
		
		this.professor.setEndereco(this.endereco);
		this.professor.addTelefone(this.telefone1);
		this.professor.addTelefone(this.telefone2);
		this.professor.setSexo(this.sexo);
		this.professor.setGraduacao(this.graduacao);
		
		if(professorRN.salvar(this.professor)) {
			this.professor = new Professor();
			this.professorList = null;
			
			return "gerenciarProfessores.jsf?faces-redirect=true";
		} else {
			
			return null;
			
		}
		
	}
	
	public String editar() {
		
		this.idGraduacao = this.professor.getGraduacao().getIdGraduacao();
		this.idSexo = this.professor.getSexo().getIdSexo();
		this.idUf = this.endereco.getUf().getIdUf();
		this.telefone1 = this.professor.getTelefones().get(0);
		this.telefone2 = this.professor.getTelefones().get(1);
		
		return "novoProfessor.jsf";
		
	}
	
	public void excluir() {
		
		/*UsuarioRN usuarioRN = new UsuarioRN();
		this.usuarioList = usuarioRN.buscarPorIdPessoa(this.professor.getIdPessoa());
		
		for(int i = 0; i < this.usuarioList.size(); i++) {
			
			usuarioRN.excluir(this.usuarioList.get(i));
			
		}*/
		
		ProfessorRN professorRN = new ProfessorRN();
		professorRN.excluir(this.professor);
		
		this.professor = new Professor();
		this.professorList = null;
		
	}
	
	public List<Professor> getBuscarTodos() {
		
		if(this.professorList == null || this.professorList.isEmpty()) {
			
			ProfessorRN professorRN = new ProfessorRN();
			this.professorList = professorRN.buscarTodos();
			
		}
		
		return this.professorList;
	}
	
	public List<Graduacao> getBuscarGraduacao() {
		
		ProfessorRN professorRN = new ProfessorRN();
		return professorRN.buscarGraduacao();
		
	}
	
	public List<Sexo> getBuscarSexo() {
		
		ProfessorRN professorRN = new ProfessorRN();
		return professorRN.buscarSexo();
		
	}
	
	private SelectItem[] createFilterOptions(List<Graduacao> data)  {  
        SelectItem[] options = new SelectItem[data.size() + 1];  
  
        options[0] = new SelectItem("", "Selecione");  
        for(int i = 0; i < data.size(); i++) {  
            options[i + 1] = new SelectItem(data.get(i).getDescricao(), data.get(i).getDescricao());  
        }  
  
        return options;  
    }
	

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
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

	public List<Professor> getProfessorList() {
		return this.professorList;
	}

	public void setProfessorList(List<Professor> professorList) {
		this.professorList = professorList;
	}

	public List<Professor> getFilteredProfessor() {
		return filteredProfessor;
	}

	public void setFilteredProfessor(List<Professor> filteredProfessor) {
		this.filteredProfessor = filteredProfessor;
	}

	public Graduacao getGraduacao() {
		return graduacao;
	}

	public void setGraduacao(Graduacao graduacao) {
		this.graduacao = graduacao;
	}

	public int getIdGraduacao() {
		return idGraduacao;
	}

	public void setIdGraduacao(int idGraduacao) {
		this.idGraduacao = idGraduacao;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public SelectItem[] getFilteredGraduacao() {
		return filteredGraduacao;
	}

	public void setFilteredGraduacao(SelectItem[] filteredGraduacao) {
		this.filteredGraduacao = filteredGraduacao;
	}

}
