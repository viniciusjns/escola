package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import RN.SituacaoAlunoRN;
import entities.SituacaoAluno;

@ManagedBean
@RequestScoped
public class SituacaoAlunoBean {
	
	public List<SituacaoAluno> situacaoList;

	public List<SituacaoAluno> getBuscarTodos() {
		
		if(this.situacaoList == null) {
			SituacaoAlunoRN situacaoRN = new SituacaoAlunoRN();
			this.situacaoList = situacaoRN.buscarTodos();
		}
		
		return this.situacaoList;
		
	}

	public void setSituacaoList(List<SituacaoAluno> situacaoList) {
		this.situacaoList = situacaoList;
	}

}
