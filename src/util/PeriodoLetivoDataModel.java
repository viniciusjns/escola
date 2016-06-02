package util;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import entities.Aluno;
import entities.PeriodoLetivo;

public class PeriodoLetivoDataModel extends ListDataModel<PeriodoLetivo> implements SelectableDataModel<PeriodoLetivo>, Serializable {

	private static final long serialVersionUID = 1L;

	public PeriodoLetivoDataModel() {  
    }  
  
    public PeriodoLetivoDataModel(List<PeriodoLetivo> data) {  
        super(data);  
    }  
      
    @Override  
    public PeriodoLetivo getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<PeriodoLetivo> periodos = (List<PeriodoLetivo>) getWrappedData();  
          
        for(PeriodoLetivo periodo : periodos) {  
            if(periodo.getDescricao().equals(rowKey))  
                return periodo;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(PeriodoLetivo periodo) {  
        return periodo.getIdPeriodoLetivo();  
    }

}
