package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Entrega implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEntrega;
	
	@Column(name = "arquivo", nullable = true, length = 100)
	private String arquivo;
	
	@Lob
	@Column(name = "descricao", nullable = true)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dataEntrega", nullable = false)
	private Date dataEntrega;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "horaEntrega", nullable = false)
	private Date horaEntrega;
	
	@ManyToOne
	@JoinColumn(name = "idAtividade")
	private AtividadeComEntrega atividadeComEntrega;
	
	@ManyToOne
	@JoinColumn(name = "idPessoa")
	private Aluno aluno;

	public int getIdEntrega() {
		return idEntrega;
	}

	public void setIdEntrega(int idEntrega) {
		this.idEntrega = idEntrega;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public AtividadeComEntrega getAtividadeComEntrega() {
		return atividadeComEntrega;
	}

	public void setAtividadeComEntrega(AtividadeComEntrega atividadeComEntrega) {
		this.atividadeComEntrega = atividadeComEntrega;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	

	public Date getHoraEntrega() {
		return horaEntrega;
	}

	public void setHoraEntrega(Date horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((arquivo == null) ? 0 : arquivo.hashCode());
		result = prime
				* result
				+ ((atividadeComEntrega == null) ? 0 : atividadeComEntrega
						.hashCode());
		result = prime * result
				+ ((dataEntrega == null) ? 0 : dataEntrega.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((horaEntrega == null) ? 0 : horaEntrega.hashCode());
		result = prime * result + idEntrega;
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
		Entrega other = (Entrega) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (arquivo == null) {
			if (other.arquivo != null)
				return false;
		} else if (!arquivo.equals(other.arquivo))
			return false;
		if (atividadeComEntrega == null) {
			if (other.atividadeComEntrega != null)
				return false;
		} else if (!atividadeComEntrega.equals(other.atividadeComEntrega))
			return false;
		if (dataEntrega == null) {
			if (other.dataEntrega != null)
				return false;
		} else if (!dataEntrega.equals(other.dataEntrega))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (horaEntrega == null) {
			if (other.horaEntrega != null)
				return false;
		} else if (!horaEntrega.equals(other.horaEntrega))
			return false;
		if (idEntrega != other.idEntrega)
			return false;
		return true;
	}

}
