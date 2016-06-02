package RN;

import java.util.List;

import util.Email;
import util.Encriptografar;
import util.MensagemUtil;
import util.ValidaCpf;
import DAO.AlunoDAO;
import entities.Aluno;
import entities.Permissao;
import entities.Sexo;
import entities.Usuario;

public class AlunoRN {
	
	private Usuario usuario = new Usuario();
	private Permissao permissao = new Permissao();
	private AlunoDAO alunoDAO = new AlunoDAO();
	private Email email = new Email();
	private MensagemUtil msg = new MensagemUtil();
	private ValidaCpf valida = new ValidaCpf();
	
	
	public boolean salvar(Aluno aluno) {
		
		Integer codigo = aluno.getIdPessoa();
		String senha;
		
		if(this.valida.isCPF(aluno.getCpf().replace(".", "").replace("-", ""))) {
			if(codigo == 0 || codigo == null) {
				
				senha = this.gerarSenha();
				
				this.alunoDAO.salvar(aluno);
				
				PermissaoRN permissaoRN = new PermissaoRN();
				this.permissao = permissaoRN.buscarPermissaoPorDescricao("ROLE_ALUNO");
				
				this.usuario.setAtivo(true);
				this.usuario.setUsuario(this.gerarUsuario());
				this.usuario.setSenha(Encriptografar.encriptografarMD5(senha));
				this.usuario.setPermissao(this.permissao);
				this.usuario.setPessoa(aluno);
				
				UsuarioRN usuarioRN = new UsuarioRN();	
				usuarioRN.salvar(this.usuario);
				
				String msg = "Usuario: " + this.usuario.getUsuario() + "\nSenha: " + senha;
				this.email.enviarEmail(aluno.getEmail(), "Bem vindo ao Sistema de Controle Acadêmico", msg);
				
				
			} else {
				
				this.alunoDAO.atualizar(aluno);
				
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
	
	public void excluir(Aluno aluno) {
		
		this.alunoDAO.excluir(aluno);
		
	}
	
	public Aluno buscarPorId(int id) {
		
		return (Aluno) this.alunoDAO.buscarPorId(id);
		
	}
	
	public List<Aluno> buscarTodos() {
		
		return (List) this.alunoDAO.buscarTodos();
		
	}
	
	public Aluno buscarAlunoPorUsuario(long usuario) {
		
		return (Aluno) this.alunoDAO.buscarAlunoPorUsuario(usuario);
		
	}
	
	
	//
	public List<Sexo> buscarSexo() {
		
		return (List) this.alunoDAO.buscarSexo();
		
	}
	
	public Sexo buscarSexoPorId(int id) {
		
		return this.alunoDAO.buscarSexoPorId(id);
	}

}
