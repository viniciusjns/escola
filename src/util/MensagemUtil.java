package util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagemUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public void msgSucesso(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, msg, null));
	}
	
	public void msgErro(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR, msg, null));
	}

}
