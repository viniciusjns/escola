package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;


@Entity
//@PrimaryKeyJoinColumn(name="idPessoa")
public class Aluno extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "nomeMae", nullable = false, length = 80)
	private String nomeMae;

	@Column(name = "nomePai", nullable = true, length = 80)
	private String nomePai;
	
	public Aluno() {
	}

	public String getNomeMae() {
		return this.nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return this.nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
}