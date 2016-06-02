package util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Email {
	
	private SimpleEmail email = new SimpleEmail();
	
	public Email() {
		 
	}
	
	public void enviarEmail(String destinatario, String assunto, String mensagem) {
		try {  
		    email.setDebug(true);  
		    email.setHostName("smtp.gmail.com");  
		    email.setAuthentication("viniciusjns","@nalondon123");  
		    email.setSSL(true);  
		    email.addTo(destinatario); //pode ser qualquer um email  
		    email.setFrom("viniciusjns@gmail.com"); //aqui necessita ser o email que voce fara a autenticacao  
		    email.setSubject(assunto);  
		    email.setMsg(mensagem);  
	        email.send();  
	  
	        } catch (EmailException e) {  
	  
	        	System.out.println("****ERRO**** :" + e.getMessage());  
	  
	        }
	}

}
