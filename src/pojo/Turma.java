package pojo;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;


@Entity
@NamedQuery(name="Turma.findAll", query="SELECT t FROM Turma t")
public class Turma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTurma;

	@Column(name = "descricao", nullable = false, length = 30)
	@NotEmpty(message = "Informe a descrição")
	@Size(max = 30, min = 1, message = "A descrição deve ter de {min} até {max} caracteres")
	private String descricao;

	@Column(name = "sala", nullable = false, length = 10)
	@NotEmpty(message = "Informe a sala")
	@Size(max = 10, min = 1, message = "A sala deve ter de {min} até {max} caracteres")
	private String sala;
/*
	//bi-directional many-to-one association to Atividade
	@OneToMany(mappedBy="turma")
	private List<Atividade> atividades;

	//bi-directional many-to-one association to Aula
	@OneToMany(mappedBy="turma")
	private List<Aula> aulas;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="turma")
	private List<Matricula> matriculas;
*/
	public Turma() {
	}

	public Integer getIdTurma() {
		return this.idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSala() {
		return this.sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}
/*
	public List<Atividade> getAtividades() {
		return this.atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public Atividade addAtividade(Atividade atividade) {
		getAtividades().add(atividade);
		atividade.setTurma(this);

		return atividade;
	}

	public Atividade removeAtividade(Atividade atividade) {
		getAtividades().remove(atividade);
		atividade.setTurma(null);

		return atividade;
	}

	public List<Aula> getAulas() {
		return this.aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public Aula addAula(Aula aula) {
		getAulas().add(aula);
		aula.setTurma(this);

		return aula;
	}

	public Aula removeAula(Aula aula) {
		getAulas().remove(aula);
		aula.setTurma(null);

		return aula;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setTurma(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setTurma(null);

		return matricula;
	}
*/
}