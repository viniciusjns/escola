package pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idUsuario;

	private byte bloqueado;

	private int matricula;

	private int senha;

	//bi-directional many-to-one association to Permissao
	@ManyToOne
	@JoinColumn(name="idPermissao")
	private Permissao permissao;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="idPessoa")
	private Pessoa pessoa;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public byte getBloqueado() {
		return this.bloqueado;
	}

	public void setBloqueado(byte bloqueado) {
		this.bloqueado = bloqueado;
	}

	public int getLogin() {
		return this.matricula;
	}

	public void setLogin(int login) {
		this.matricula = login;
	}

	public int getSenha() {
		return this.senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public Permissao getPermissao() {
		return this.permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}