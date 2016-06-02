package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompactadorArquivo {        
	  
	public byte[] criarZip(Map<String, byte[]> arquivos) throws IOException { 
		
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();  
	    ZipOutputStream zip = new ZipOutputStream(bos);  
	    Iterator iterator = arquivos.keySet().iterator();  
	    String nomeArquivo = null;  
	    ZipEntry zipEntry = null;
	    
	    while (iterator.hasNext()) {
	    	
	        nomeArquivo = (String) iterator.next();  
	        zipEntry = new ZipEntry(nomeArquivo);  
	        zip.putNextEntry(zipEntry);  
	        zip.write((byte[]) arquivos.get(nomeArquivo));
	        
	    }
	    
	    zip.close();  
	    return bos.toByteArray();
	    
	}
	
	public void gravar(String caminho, byte[] arquivo){
        
        try {
        	
        	File file = new File(new File(caminho).getParent());
        	if(!file.exists())
        		file.mkdirs();
        	
            FileOutputStream fos;
            fos = new FileOutputStream(caminho);
            fos.write(arquivo);
            fos.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
    }
	
	public byte[] convertFileToByteArray(String arquivo) {
		
		File file = new File(arquivo);
        byte[] b = new byte[(int) file.length()];
        
        try {
        	
        	FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
            fileInputStream.close();
            
        } catch (FileNotFoundException e) {
        	
        	System.out.println("File Not Found.");
            e.printStackTrace();
            
        }
        catch (IOException e1) {
        	
        	System.out.println("Error Reading The File.");
            e1.printStackTrace();
            
        }
        
		return b;
		
	}
	
}