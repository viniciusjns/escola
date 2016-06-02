package RN;

import java.util.List;

import util.Email;
import util.MensagemUtil;
import util.ValidaCpf;
import DAO.SecretariaDAO;
import entities.Permissao;
import entities.Pessoa;
import entities.Sexo;
import entities.Usuario;

public class SecretariaRN {
	
	private Usuario usuario = new Usuario();
	private Permissao permissao = new Permissao();
	private Email email = new Email();
	private SecretariaDAO secretariaDAO = new SecretariaDAO();
	private MensagemUtil msg = new MensagemUtil();
	private ValidaCpf valida = new ValidaCpf();
	
	public boolean salvar(Pessoa pessoa) {
		
		Integer codigo = pessoa.getIdPessoa();
		
		if(this.valida.isCPF(pessoa.getCpf().replace(".", "").replace("-", ""))) {
			if(codigo == 0 || codigo == null) {
				
				this.secretariaDAO.salvar(pessoa);
				
				PermissaoRN permissaoRN = new PermissaoRN();
				this.permissao = permissaoRN.buscarPermissaoPorDescricao("ROLE_SECRETARIA");
				
				this.usuario.setAtivo(true);
				this.usuario.setUsuario(this.gerarUsuario());
				this.usuario.setSenha(this.gerarSenha());
				this.usuario.setPermissao(this.permissao);
				this.usuario.setPessoa(pessoa);
				
				UsuarioRN usuarioRN = new UsuarioRN();
				usuarioRN.salvar(this.usuario);
				
				String msg = "Usuario: " + this.usuario.getUsuario() + "\nSenha: " + this.usuario.getSenha();
				this.email.enviarEmail(pessoa.getEmail(), "Bem vindo ao Sistema de Controle Acadêmico", msg);
				
			} else {
				
				this.secretariaDAO.atualizar(pessoa);
				
			}
			return true;
		} else {
			
			this.msg.msgErro("CPF inválido!");
			return false;
			
		}
		
	}
	
	private boolean existeUsuario(Long usuario) {
		
		UsuarioRN usuarioRN = new UsuarioRN();
		Usuario u = usuarioRN.buscarUsuarioPorUsuario(usuario);
		
		if(u != null)
			return true;
		
		return false;
		
	}
	
	private Long gerarUsuario() {
		
		long num = (long) (Math.random() * 99999999);
		
		if(!existeUsuario(num))
			return num;
		else
			this.gerarUsuario();
		
		return null;
		
	}
	
	private String gerarSenha() {
		
		int valor = (int) (Math.random() * 99999999);
		
		return String.valueOf(valor);
		
	}
	
	public void excluir(Pessoa pessoa) {
		
		this.secretariaDAO.excluir(pessoa);
		
	}
	
	public Pessoa buscarPorId(int id) {
		
		return (Pessoa) this.secretariaDAO.buscarPorId(id);
		
	}
	
	public List<Pessoa> buscarTodos() {
		
		return (List) this.secretariaDAO.buscarTodos();
		
	}
	
	public Pessoa buscarSecretariaPorUsuario(long usuario) {
		
		return (Pessoa) this.secretariaDAO.buscarSecretariaPorUsuario(usuario);
		
	}
	
	//
	public List<Sexo> buscarSexo() {
		
		return (List) this.secretariaDAO.buscarSexo();
		
	}
	
	public Sexo buscarSexoPorId(int id) {
		
		return this.secretariaDAO.buscarSexoPorId(id);
	}

}
