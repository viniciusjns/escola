package util;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import entities.Aluno;

public class AlunoDataModel extends ListDataModel<Aluno> implements SelectableDataModel<Aluno>, Serializable {
	
	private static final long serialVersionUID = 1L;

	public AlunoDataModel() {  
    }  
  
    public AlunoDataModel(List<Aluno> data) {  
        super(data);  
    }  
      
    @Override  
    public Aluno getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Aluno> alunos = (List<Aluno>) getWrappedData();  
          
        for(Aluno aluno : alunos) {  
            if(aluno.getNome().equals(rowKey))  
                return aluno;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Aluno aluno) {  
        return aluno.getIdPessoa();
    }

}
