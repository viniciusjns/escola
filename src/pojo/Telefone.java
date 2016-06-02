package pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the telefone database table.
 * 
 */
@Entity
@NamedQuery(name="Telefone.findAll", query="SELECT t FROM Telefone t")
public class Telefone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idTelefone;

	private String numero;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="idPessoa")
	private Pessoa pessoa;

	public Telefone() {
	}

	public int getIdTelefone() {
		return this.idTelefone;
	}

	public void setIdTelefone(int idTelefone) {
		this.idTelefone = idTelefone;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}