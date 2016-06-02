package control;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.periodoLetivo.PeriodoLetivoRN;
import pojo.PeriodoLetivo;

@ManagedBean
@RequestScoped
public class PeriodoLetivoBean {
	
	private PeriodoLetivo periodoLetivo = new PeriodoLetivo();
	private List<PeriodoLetivo> periodoLetivoList;
	
	public String novoPeriodoLetivo() {
		
		this.periodoLetivo = new PeriodoLetivo();
		return "novoPeriodoLetivo.jsf?faces-redirect=true";
		
	}
	
	public String salvar() {
		
		PeriodoLetivoRN periodoLetivoRN = new PeriodoLetivoRN();
		periodoLetivoRN.salvar(this.periodoLetivo);
		
		//this.disciplina = new Disciplina();
		//this.periodoLetivoList = null;
		
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
	
	

}
