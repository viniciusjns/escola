package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import util.MensagemUtil;
import RN.AnoRN;
import RN.SerieRN;
import RN.TurmaRN;
import RN.TurnoRN;
import entities.Ano;
import entities.Serie;
import entities.Turma;
import entities.Turno;

@ManagedBean
@RequestScoped
public class TurmaBean {
	
	private Turma turma = new Turma();
	private Turno turno = new Turno();
	private Ano ano = new Ano();
	private Serie serie = new Serie();
	
	private List<Turma> turmaList;
	private List<Turma> filteredTurma;
	
	private int idTurno;
	private int idAno;
	private int idSerie;
	
	private MensagemUtil msg = new MensagemUtil();
	
	public String novaTurma() {
		
		this.turma = new Turma();
		this.turno = new Turno();
		this.ano = new Ano();
		this.serie = new Serie();
		return "novaTurma.jsf?faces-redirect=true";
		
	}
	
	public String cancelar() {
		
		this.turma = null;
		this.ano = null;
		this.turno = null;
		
		return "gerenciarTurmas.jsf?faces-redirect=true";
		
	}
	
	public String salvar() {
		
		TurmaRN turmaRN = new TurmaRN();
		TurnoRN turnoRN = new TurnoRN();
		AnoRN anoRN = new AnoRN();
		SerieRN serieRN = new SerieRN();
		
		this.turno = turnoRN.buscarPorId(this.idTurno);
		this.ano = anoRN.buscarPorId(this.idAno);
		this.serie = serieRN.buscarPorId(this.idSerie);
		
		this.turma.setTurno(this.turno);
		this.turma.setAno(this.ano);
		this.turma.setSerie(this.serie);
		
		turmaRN.salvar(this.turma);
		
		//this.turma = new Turma();
		this.turmaList = null;
		
		return "gerenciarTurmas.jsf?faces-redirect=true";
		
	}	
	
	public String editar() {
		
		this.idAno = this.turma.getAno().getIdAno();
		this.idTurno = this.turma.getTurno().getIdTurno();
		this.idSerie = this.turma.getSerie().getIdSerie();
		
		return "novaTurma.jsf";
		
	}
	
	public String excluir() {
		
		TurmaRN turmaRN = new TurmaRN();
		turmaRN.excluir(this.turma);
		turmaList = null;
		
		return null;
		
	}
	
	public List<Turno> getBuscarTurno() {
		
		TurnoRN turnoRN = new TurnoRN();
		return turnoRN.buscarTodos();
		
	}
	
	public List<Ano> getBuscarAno() {
		
		AnoRN anoRN = new AnoRN();
		return anoRN.buscarTodos();
		
	}
	
	public List<Serie> getBuscarSerie() {
		
		SerieRN serieRN = new SerieRN();
		return serieRN.buscarTodos();
		
	}
	

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Turma> getBuscarTodos() {
		
		if(this.turmaList == null) {
			
			TurmaRN turmaRN = new TurmaRN();
			this.turmaList = turmaRN.buscarTodos();
			
		}
		
		return turmaList;
	}

	public void setTurmaList(List<Turma> turmaList) {
		this.turmaList = turmaList;
	}

	public List<Turma> getFilteredTurma() {
		return filteredTurma;
	}

	public void setFilteredTurma(List<Turma> filteredTurma) {
		this.filteredTurma = filteredTurma;
	}

	public int getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}

	public int getIdAno() {
		return idAno;
	}

	public void setIdAno(int idAno) {
		this.idAno = idAno;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Ano getAno() {
		return ano;
	}

	public void setAno(Ano ano) {
		this.ano = ano;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public int getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(int idSerie) {
		this.idSerie = idSerie;
	}

	public List<Turma> getTurmaList() {
		return turmaList;
	}
	

}
