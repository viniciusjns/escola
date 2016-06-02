package util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class Encriptografar {
	
	public static String encriptografarMD5(String senha) {
		
		PasswordEncoder encoder = new Md5PasswordEncoder();
		senha = encoder.encodePassword(senha, null);
		return senha;
		
	}

}
