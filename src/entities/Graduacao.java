package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Graduacao implements Serializable {
	
	private static final long serialVersionUID = 3555786149875341708L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short idGraduacao;
	
	@Column(name = "descricao", nullable = false, length = 30)
	private String descricao;

	public short getIdGraduacao() {
		return idGraduacao;
	}

	public void setIdGraduacao(short idGraduacao) {
		this.idGraduacao = idGraduacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + idGraduacao;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Graduacao other = (Graduacao) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idGraduacao != other.idGraduacao)
			return false;
		return true;
	}
	
	

}
