package RN;

import java.util.List;

import util.Email;
import util.MensagemUtil;
import util.ValidaCpf;
import DAO.ProfessorDAO;
import entities.Aluno;
import entities.Graduacao;
import entities.Permissao;
import entities.Professor;
import entities.Sexo;
import entities.Usuario;

public class ProfessorRN {
	
	private Usuario usuario = new Usuario();
	private Permissao permissao = new Permissao();
	private ProfessorDAO professorDAO = new ProfessorDAO();
	private Email email = new Email();
	private ValidaCpf valida = new ValidaCpf();
	private MensagemUtil msg = new MensagemUtil();
	
	
	public boolean salvar(Professor professor) {
		
		Integer codigo = professor.getIdPessoa();
		
		if(this.valida.isCPF(professor.getCpf().replace(".", "").replace("-", ""))) {
			if(codigo == 0 || codigo == null) {
				
				this.professorDAO.salvar(professor);
				
				PermissaoRN permissaoRN = new PermissaoRN();
				this.permissao = permissaoRN.buscarPermissaoPorDescricao("ROLE_PROFESSOR");
				
				this.usuario.setAtivo(true);
				this.usuario.setUsuario(this.gerarUsuario());
				this.usuario.setSenha(this.gerarSenha());
				this.usuario.setPermissao(this.permissao);
				this.usuario.setPessoa(professor);
				
				UsuarioRN usuarioRN = new UsuarioRN();
				usuarioRN.salvar(this.usuario);
				
				String msg = "Usuario: " + this.usuario.getUsuario() + "\nSenha: " + this.usuario.getSenha();
				this.email.enviarEmail(professor.getEmail(), "Bem vindo ao Sistema de Controle Acadêmico", msg);
				
			} else {
				
				this.professorDAO.atualizar(professor);
				
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
	
	public void excluir(Professor professor) {
		
		this.professorDAO.excluir(professor);
		
	}
	
	public Professor buscarPorId(int id) {
		
		return (Professor) this.professorDAO.buscarPorId(id);
		
	}
	
	public List<Professor> buscarTodos() {
		
		return (List) this.professorDAO.buscarTodos();
		
	}
	
	public Professor buscarProfessorPorUsuario(long usuario) {
		
		return (Professor) this.professorDAO.buscarProfessorPorUsuario(usuario);
		
	}
	
	
	//
	public List<Sexo> buscarSexo() {
		
		return this.professorDAO.buscarSexo();
		
	}
	
	public Sexo buscarSexoPorId(int id) {
		
		return this.professorDAO.buscarSexoPorId(id);
	}
	
	public List<Graduacao> buscarGraduacao() {
		
		return this.professorDAO.buscarGraduacao();
		
	}
	
	public Graduacao buscarGraduacaoPorId(int id) {
		
		return this.professorDAO.buscarGraduacaoPorId(id);
	}

}
