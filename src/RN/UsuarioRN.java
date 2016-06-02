package RN;

import java.util.List;

import util.Encriptografar;
import DAO.UsuarioDAO;
import entities.Usuario;

public class UsuarioRN {
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public void salvar(Usuario usuario) {
		
		Integer codigo = usuario.getIdUsuario();
		String senha;
		
		if(codigo == 0 || codigo == null) {
			
			this.usuarioDAO.salvar(usuario);
			
		} else {
			
			senha = Encriptografar.encriptografarMD5(usuario.getSenha());
			usuario.setSenha(senha);
			this.usuarioDAO.atualizar(usuario);
			
		}
		
	}
	
	public void excluir(Usuario usuario) {
		
		this.usuarioDAO.excluir(usuario);
		
	}
	
	public List<Usuario> buscarTodos() {
		
		return (List) this.usuarioDAO.buscarTodos();
		
	}
	
	public List<Usuario> buscarPorIdPessoa(int idPessoa) {
		
		return (List) this.usuarioDAO.buscarPorIdPessoa(idPessoa);
		
	}
	
	public Usuario buscarUsuarioPorUsuario(long usuario) {
		
		return (Usuario) this.usuarioDAO.buscarUsuarioPorUsuario(usuario);
		
	}

}
