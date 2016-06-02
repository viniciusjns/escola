package managedBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;

import util.MensagemUtil;
import RN.DisciplinaRN;
import entities.Disciplina;

@ManagedBean
@RequestScoped
public class DisciplinaBean {
	
	private Disciplina disciplina = new Disciplina();
	private List<Disciplina> disciplinaList;
	private List<Disciplina> filteredDisciplina;
	
	public String novaDisciplina() {
		
		this.disciplina = new Disciplina();
		return "novaDisciplina.jsf?faces-redirect=true";
		
	}
	
	public String salvar() {
		
		DisciplinaRN disciplinaRN = new DisciplinaRN();
		disciplinaRN.salvar(this.disciplina);
		
		return "gerenciarDisciplinas.jsf?faces-redirect=true";
		
	}
	
	public String cancelar() {
		
		this.disciplina = null;
		
		return "gerenciarDisciplinas?faces-redirect=true";
		
	}
	
	public String editar() {
		
		return "novaDisciplina";
		
	}
	
	public String excluir() {
		
		DisciplinaRN disciplinaRN = new DisciplinaRN();
		disciplinaRN.excluir(this.disciplina);
		
		this.disciplinaList = null;
		
		return null;
		
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Disciplina> getBuscarTodos() {
		
		if(this.disciplinaList == null) {
			
			DisciplinaRN disciplinaRN = new DisciplinaRN();
			this.disciplinaList = disciplinaRN.buscarTodos();
			
		}
		
		return disciplinaList;
	}

	public void setDisciplinaList(List<Disciplina> disciplinaList) {
		this.disciplinaList = disciplinaList;
	}

	public List<Disciplina> getFilteredDisciplina() {
		return filteredDisciplina;
	}

	public void setFilteredDisciplina(List<Disciplina> filteredDisciplina) {
		this.filteredDisciplina = filteredDisciplina;
	}
}
