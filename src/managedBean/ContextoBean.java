package managedBean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import RN.AlunoRN;
import RN.ProfessorRN;
import RN.SecretariaRN;
import RN.UsuarioRN;
import entities.Aluno;
import entities.Pessoa;
import entities.Professor;
import entities.Usuario;

@ManagedBean
@SessionScoped
public class ContextoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Aluno alunoLogado = null;
	private Professor professorLogado = null;
	private Pessoa secretariaLogada = null;
	private Usuario usuarioLogado = null;
	
	public Aluno getAlunoLogado() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		Long usuario = Long.parseLong(external.getRemoteUser());
		
		if(this.alunoLogado == null) {
			if(usuario != null) {
				AlunoRN alunoRN = new AlunoRN();
				this.alunoLogado = alunoRN.buscarAlunoPorUsuario(usuario);
			}
		}
		
		return alunoLogado;
		
	}
	
	public Professor getProfessorLogado() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		Long usuario = Long.parseLong(external.getRemoteUser());
		
		if(this.professorLogado == null) {
			if(usuario != null) {
				ProfessorRN professorRN = new ProfessorRN();
				this.professorLogado = professorRN.buscarProfessorPorUsuario(usuario);
			}
		}
		
		return professorLogado;
		
	}
	
	public Pessoa getSecretariaLogada() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		Long usuario = Long.parseLong(external.getRemoteUser());
		
		if(this.secretariaLogada == null) {
			if(usuario != null) {
				SecretariaRN secretariaRN = new SecretariaRN();
				this.secretariaLogada = secretariaRN.buscarSecretariaPorUsuario(usuario);
			}
		}
		
		return secretariaLogada;
		
	}
	
	public Usuario getUsuarioLogado() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		Long usuario = Long.parseLong(external.getRemoteUser());
		
		if(this.usuarioLogado == null) {
			if(usuario != null) {
				UsuarioRN usuarioRN = new UsuarioRN();
				this.usuarioLogado = usuarioRN.buscarUsuarioPorUsuario(usuario);
			}
		}
		
		return usuarioLogado;
		
	}

}
