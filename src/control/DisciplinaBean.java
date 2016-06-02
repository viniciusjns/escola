package control;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.disciplina.DisciplinaRN;
import pojo.Disciplina;

@ManagedBean
@RequestScoped
public class DisciplinaBean {
	
	private Disciplina disciplina = new Disciplina();
	private List<Disciplina> disciplinaList;
	
	public String novaDisciplina() {
		
		this.disciplina = new Disciplina();
		return "novaDisciplina.jsf?faces-redirect=true";
		
	}
	
	public String salvar() {
		
		DisciplinaRN disciplinaRN = new DisciplinaRN();
		disciplinaRN.salvar(this.disciplina);
		
		//this.disciplina = new Disciplina();
		//this.disciplinaList = null;
		
		return "gerenciarDisciplinas.jsf?faces-redirect=true";
		
	}
	
	public String editar() {
		
		return "novaDisciplina";
		
	}
	
	public String excluir() {
		
		DisciplinaRN disciplinaRN = new DisciplinaRN();
		disciplinaRN.excluir(this.disciplina);
		
		disciplinaList = null;
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
	
	

}
