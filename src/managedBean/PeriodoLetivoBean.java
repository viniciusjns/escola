package managedBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;

import util.MensagemUtil;
import RN.AnoRN;
import RN.PeriodoLetivoRN;
import entities.Ano;
import entities.PeriodoLetivo;

@ManagedBean
@RequestScoped
public class PeriodoLetivoBean {
	
	private PeriodoLetivo periodoLetivo = new PeriodoLetivo();
	private Ano ano = new Ano();
	private List<PeriodoLetivo> periodoLetivoList;
	private List<PeriodoLetivo> filteredPeriodoLetivo;
	
	private int idAno;
	
	public String novoPeriodoLetivo() {
		
		this.periodoLetivo = new PeriodoLetivo();
		this.ano = new Ano();
		
		return "novoPeriodoLetivo.jsf?faces-redirect=true";
		
	}
	
	public String cancelar() {
		
		this.periodoLetivo = null;
		
		return "gerenciarPeriodosLetivos.jsf?faces-redirect=true";
		
	}
	
	public String salvar() {
		
		PeriodoLetivoRN periodoLetivoRN = new PeriodoLetivoRN();
		AnoRN anoRN = new AnoRN();
		this.ano = anoRN.buscarPorId(this.idAno);
		this.periodoLetivo.setAno(this.ano);
		
		periodoLetivoRN.salvar(this.periodoLetivo);
		
		this.periodoLetivoList = null;
		
		return "gerenciarPeriodosLetivos.jsf?faces-redirect=true";
		
	}
	
	public String editar() {
		
		return "novoPeriodoLetivo";
		
	}
	
	public String excluir() {
		
		PeriodoLetivoRN periodoLetivoRN = new PeriodoLetivoRN();
		periodoLetivoRN.excluir(this.periodoLetivo);
		periodoLetivoList = null;
		return null;
		
	}
	
	public List<Ano> getBuscarAno() {
		
		AnoRN anoRN = new AnoRN();
		return anoRN.buscarTodos();
		
	}

	public PeriodoLetivo getPeriodoLetivo() {
		return periodoLetivo;
	}

	public void setPeriodoLetivo(PeriodoLetivo periodoLetivo) {
		this.periodoLetivo = periodoLetivo;
	}

	public List<PeriodoLetivo> getBuscarTodos() {
		
		if(this.periodoLetivoList == null) {
			
			PeriodoLetivoRN periodoLetivoRN = new PeriodoLetivoRN();
			this.periodoLetivoList = periodoLetivoRN.buscarTodos();
			
		}
		
		return periodoLetivoList;
	}

	public void setPeriodoLetivoList(List<PeriodoLetivo> periodoLetivoList) {
		this.periodoLetivoList = periodoLetivoList;
	}

	public List<PeriodoLetivo> getFilteredPeriodoLetivo() {
		return filteredPeriodoLetivo;
	}

	public void setFilteredPeriodoLetivo(List<PeriodoLetivo> filteredPeriodoLetivo) {
		this.filteredPeriodoLetivo = filteredPeriodoLetivo;
	}
	
	public int getIdAno() {
		return idAno;
	}

	public void setIdAno(int idAno) {
		this.idAno = idAno;
	}
	

}
