package pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;


@Entity
//@PrimaryKeyJoinColumn(name="idPessoa")
@NamedQuery(name="Aluno.findAll", query="SELECT a FROM Aluno a")
public class Aluno extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nomeMae;

	private String nomePai;
	
/*
	//bi-directional many-to-one association to Entrega
	@OneToMany(mappedBy="aluno")
	private List<Entrega> entregas;

	//bi-directional many-to-one association to Falta
	@OneToMany(mappedBy="aluno")
	private List<Falta> faltas;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="aluno")
	private List<Matricula> matriculas;

	//bi-directional many-to-one association to Nota
	@OneToMany(mappedBy="aluno")
	private List<Nota> notas;
*/
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
	
/*
	public List<Entrega> getEntregas() {
		return this.entregas;
	}

	public void setEntregas(List<Entrega> entregas) {
		this.entregas = entregas;
	}

	public Entrega addEntrega(Entrega entrega) {
		getEntregas().add(entrega);
		entrega.setAluno(this);

		return entrega;
	}

	public Entrega removeEntrega(Entrega entrega) {
		getEntregas().remove(entrega);
		entrega.setAluno(null);

		return entrega;
	}

	public List<Falta> getFaltas() {
		return this.faltas;
	}

	public void setFaltas(List<Falta> faltas) {
		this.faltas = faltas;
	}

	public Falta addFalta(Falta falta) {
		getFaltas().add(falta);
		falta.setAluno(this);

		return falta;
	}

	public Falta removeFalta(Falta falta) {
		getFaltas().remove(falta);
		falta.setAluno(null);

		return falta;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setAluno(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setAluno(null);

		return matricula;
	}

	public List<Nota> getNotas() {
		return this.notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public Nota addNota(Nota nota) {
		getNotas().add(nota);
		nota.setAluno(this);

		return nota;
	}

	public Nota removeNota(Nota nota) {
		getNotas().remove(nota);
		nota.setAluno(null);

		return nota;
	}
*/
}