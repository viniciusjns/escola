package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import RN.HorarioRN;
import RN.TurnoRN;
import entities.Horario;
import entities.Turno;

@ManagedBean
@RequestScoped
public class HorarioBean {
	
	private Horario horario = new Horario();
	private Turno turno;
	private List<Horario> horarioList;
	private List<Horario> filteredHorario;
	
	private int idTurnoSelecionado;
	
	public String novoHorario() {
		
		this.horario = new Horario();
		return "novoHorario.jsf?faces-redirect=true";
		
	}
	
	public String salvar() {
		
		TurnoRN turnoRN = new TurnoRN();
		this.turno = turnoRN.buscarPorId(this.idTurnoSelecionado);
		this.horario.setTurno(this.turno);
		
		HorarioRN horarioRN = new HorarioRN();
		horarioRN.salvar(this.horario);
		
		return "gerenciarHorarios.jsf?faces-redirect=true";
		
	}
	
	public String cancelar() {
		
		this.horario = null;
		
		return "gerenciarHorarios?faces-redirect=true";
		
	}
	
	public String editar() {
		
		this.idTurnoSelecionado = this.horario.getTurno().getIdTurno();
		
		return "novoHorario";
		
	}
	
	public String excluir() {
		
		HorarioRN horarioRN = new HorarioRN();
		horarioRN.excluir(this.horario);
		
		this.horarioList = null;
		
		return null;
		
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public List<Horario> getBuscarTodos() {
		
		if(this.horarioList == null) {
			
			HorarioRN horarioRN = new HorarioRN();
			this.horarioList = horarioRN.buscarTodos();
			
		}
		
		return horarioList;
	}

	public void setHorarioList(List<Horario> horarioList) {
		this.horarioList = horarioList;
	}

	public List<Horario> getFilteredHorario() {
		return filteredHorario;
	}

	public void setFilteredHorario(List<Horario> filteredHorario) {
		this.filteredHorario = filteredHorario;
	}

	public int getIdTurnoSelecionado() {
		return idTurnoSelecionado;
	}

	public void setIdTurnoSelecionado(int idTurnoSelecionado) {
		this.idTurnoSelecionado = idTurnoSelecionado;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	

}
