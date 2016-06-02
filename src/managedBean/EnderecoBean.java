package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import RN.EnderecoRN;
import entities.Uf;

@ManagedBean
@RequestScoped
public class EnderecoBean {
	
	public List<Uf> getBuscarUf() {
		
		EnderecoRN enderecoRN = new EnderecoRN();
		return enderecoRN.buscarUf();
		
	}

}
