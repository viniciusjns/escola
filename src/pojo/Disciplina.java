package pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@NamedQuery(name="Disciplina.findAll", query="SELECT d FROM Disciplina d")
public class Disciplina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDisciplina;

	@Column(name = "descricao", nullable = false, length = 50)
	@NotEmpty(message = "Informe a descrição")
	@Size(max = 50, min = 1, message = "A descrição deve ter de {min} até {max} caracteres")
	private String descricao;
/*
	//bi-directional many-to-many association to Professor
	@ManyToMany
	@JoinTable(
		name="professordisciplina"
		, joinColumns={
			@JoinColumn(name="idDisciplina")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idPessoa")
			}
		)
	private List<Professor> professors;
*/
	public Disciplina() {
	}

	public Integer getIdDisciplina() {
		return this.idDisciplina;
	}

	public void setIdDisciplina(Integer idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
/*
	public List<Professor> getProfessors() {
		return this.professors;
	}

	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}
*/
}