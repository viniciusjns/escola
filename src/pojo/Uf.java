package pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the uf database table.
 * 
 */
@Entity
@NamedQuery(name="Uf.findAll", query="SELECT u FROM Uf u")
public class Uf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idUf;

	private String descricao;

	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="uf")
	private List<Endereco> enderecos;

	public Uf() {
	}

	public int getIdUf() {
		return this.idUf;
	}

	public void setIdUf(int idUf) {
		this.idUf = idUf;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco addEndereco(Endereco endereco) {
		getEnderecos().add(endereco);
		endereco.setUf(this);

		return endereco;
	}

	public Endereco removeEndereco(Endereco endereco) {
		getEnderecos().remove(endereco);
		endereco.setUf(null);

		return endereco;
	}

}