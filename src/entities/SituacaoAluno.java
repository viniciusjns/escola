package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "situacaoaluno")
public class SituacaoAluno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private short idSituacaoAluno;
	
	private String descricao;

	public short getIdSituacaoAluno() {
		return idSituacaoAluno;
	}

	public void setIdSituacaoAluno(short idSituacaoAluno) {
		this.idSituacaoAluno = idSituacaoAluno;
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
		result = prime * result + idSituacaoAluno;
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
		SituacaoAluno other = (SituacaoAluno) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idSituacaoAluno != other.idSituacaoAluno)
			return false;
		return true;
	}
	

}
