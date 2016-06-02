package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import RN.AlunoTurmaRN;
import RN.UsuarioRN;
import entities.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioBean {
	
	private List<Usuario> filteredUsuario;
	private List<Usuario> usuarioList;
	
	private Usuario usuario = new Usuario();
	
	public void ativarDesativar() {
		
		if(this.usuario.isAtivo())
			this.usuario.setAtivo(false);
		else
			this.usuario.setAtivo(true);
		
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		
	}
	
	public void excluir() {
		
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		
		this.usuario = new Usuario();
		this.usuarioList = null;
		
	}

	public List<Usuario> getFilteredUsuario() {
		return filteredUsuario;
	}

	public void setFilteredUsuario(List<Usuario> filteredUsuario) {
		this.filteredUsuario = filteredUsuario;
	}

	public List<Usuario> getUsuarioList() {
		
		if(this.usuarioList == null || this.usuarioList.isEmpty()) {
			
			UsuarioRN usuarioRN = new UsuarioRN();
			this.usuarioList = usuarioRN.buscarTodos();
			
		}
		
		return this.usuarioList;
		
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	

}
