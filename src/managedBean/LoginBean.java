package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class LoginBean {
	
	public String trocaLink(){
        FacesContext context = FacesContext.getCurrentInstance().getCurrentInstance();
        ExternalContext external= context.getExternalContext();
        HttpServletRequest request =(HttpServletRequest) external.getRequest();
        
        if(request.isUserInRole("ROLE_SECRETARIA")){
            
        	return "secretaria/index.jsf";
        
        } else if(request.isUserInRole("ROLE_PROFESSOR")){
            
        	return "professor/index.jsf";
        
        } else if(request.isUserInRole("ROLE_ALUNO")){
            
        	return "aluno/index.jsf";
        
        } else if(request.isUserInRole("ROLE_ADMINISTRADOR")){
            
        	return "administrador/index.jsf";
        
        } else
            return null;
    }

}
