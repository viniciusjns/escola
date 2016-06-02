package control;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.turma.TurmaRN;
import pojo.Turma;

@ManagedBean
@RequestScoped
public class TurmaBean {
	
	private Turma turma = new Turma();
	private List<Turma> turmaList;
	
	public String novaTurma() {
		
		this.turma = new Turma();
		return "novaTurma.jsf?faces-redirect=true";
		
	}
	
	public String salvar() {
		
		TurmaRN turmaRN = new TurmaRN();
		turmaRN.salvar(this.turma);
		
		//this.turma = new Turma();
		//this.turmaList = null;
		
		return "gerenciarTurmas.jsf?faces-redirect=true";
		
	}
	
	public String editar() {
		
		return "novaTurma.jsf";
		
	}
	
	public String excluir() {
		
		TurmaRN turmaRN = new TurmaRN();
		turmaRN.excluir(this.turma);
		
		turmaList = null;
		return null;
		
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

}
