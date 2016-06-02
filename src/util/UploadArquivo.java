/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.UploadedFile;

/**
 *
 * @author MARA NETBOOK
 */
public class UploadArquivo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String caminho;
    private byte[] arquivo;
    private String nome;

    public UploadArquivo() {
    }
    
    public String getRealPath() {
    	ServletContext sContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();

        return sContext.getRealPath("/");
    }

    public void fileUpload(long id, UploadedFile uploadedFile, String diretorio) {
        try {
            this.nome = uploadedFile.getFileName().replace(" ", "");
            this.caminho = getRealPath() + diretorio + id + "/" + this.nome;
            this.arquivo = uploadedFile.getContents();
            
            File file = new File(getRealPath() + diretorio + id);
            
            if(!file.exists())
            	file.mkdirs();
            
        } catch (Exception ex) {
            System.out.println("Erro no upload do arquivo" + ex);
        }
    }
    
    public void gravar(){
        
        try {
            FileOutputStream fos;
            fos = new FileOutputStream(this.caminho);
            fos.write(this.arquivo);
            fos.close();
            
        } catch (Exception ex) {
            Logger.getLogger(UploadArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}