package pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@NamedQuery(name="PeriodoLetivo.findAll", query="SELECT p FROM PeriodoLetivo p")
public class PeriodoLetivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPeriodoLetivo;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataFim", nullable = false)
	@NotNull(message = "Informe a data fim")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataInicio", nullable = false)
	@NotNull(message = "Informe a data início")
	private Date dataInicio;

	private String descricao;
/*
	//bi-directional many-to-one association to Aula
	@OneToMany(mappedBy="periodoletivo")
	private List<Aula> aulas;
*/
	public PeriodoLetivo() {
	}

	public int getIdPeriodoLetivo() {
		return this.idPeriodoLetivo;
	}

	public void setIdPeriodoLetivo(int idPeriodoLetivo) {
		this.idPeriodoLetivo = idPeriodoLetivo;
	}

	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
/*
	public List<Aula> getAulas() {
		return this.aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public Aula addAula(Aula aula) {
		getAulas().add(aula);
		aula.setPeriodoletivo(this);

		return aula;
	}

	public Aula removeAula(Aula aula) {
		getAulas().remove(aula);
		aula.setPeriodoletivo(null);

		return aula;
	}
*/
}