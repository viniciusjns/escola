package util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;


public class AbreBrowser 
{
    public static String URLCaracteresInvalidos(String URL){  
          
        final StringBuilder result = new StringBuilder();  
        final StringCharacterIterator iterator = new StringCharacterIterator(URL);  
        char character =  iterator.current();  
        while (character != CharacterIterator.DONE ) {  
            if (character == '\\') {  
                result.append("/");  
            } else if (character == ' ') {  
                result.append("%20");  
            } else {   
                result.append(character);  
            }  
        character = iterator.next();  
        }  
        return result.toString();  
    }  
      
   public void AbreBrowser(String URL){  
      
    Desktop desktop = null;       
       //Primeiro verificamos se é possível a integração com o desktop       
       if (!Desktop.isDesktopSupported())       
            throw new IllegalStateException("Desktop resources not supported!");       
              
        desktop = Desktop.getDesktop();       
        //Agora vemos se é possível disparar o browser default.       
        if (!desktop.isSupported(Desktop.Action.BROWSE))       
            throw new IllegalStateException("No default browser set!");       
              
        //Pega a URI de um componente de texto.       
        URI uri = null;    
        try {    
            uri = new URI(URL);    
        } catch (URISyntaxException e1) {    
            e1.printStackTrace();    
        }       
              
        //Dispara o browser default, que pode ser o Explorer, Firefox ou outro.       
        try {    
            desktop.browse(uri);    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
   } 
}