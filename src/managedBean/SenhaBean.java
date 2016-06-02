package managedBean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import util.ContextoUtil;
import util.MensagemUtil;
import RN.UsuarioRN;
import entities.Usuario;

@ManagedBean
@ViewScoped
public class SenhaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	private String repetirSenha;
	private String novaSenha;
	
	private ContextoBean contextoBean = ContextoUtil.getContextoBean();
	private MensagemUtil msg = new MensagemUtil();
	
	public void buscarUsuario() {
		
		this.usuario = contextoBean.getUsuarioLogado();
		
	}
	
	public void salvar() {
		
		if(this.getNovaSenha().compareTo(this.getRepetirSenha()) == 0) {
			
			this.usuario.setSenha(this.getNovaSenha());
			
			UsuarioRN usuarioRN = new UsuarioRN();
			usuarioRN.salvar(this.usuario);
			
			this.msg.msgSucesso("Senha atualizada com sucesso!");
			
		} else {
			
			this.msg.msgErro("A senha repetida deve ser a mesma da nova senha!");
			
		}		
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRepetirSenha() {
		return repetirSenha;
	}

	public void setRepetirSenha(String repetirSenha) {
		this.repetirSenha = repetirSenha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
}
