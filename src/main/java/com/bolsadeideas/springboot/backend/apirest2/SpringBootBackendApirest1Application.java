package com.bolsadeideas.springboot.backend.apirest2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*generaremos un script para generar nuestras claves
 * implementamos la inerface CommandLineRunner
 * y generamos un metodo este metodo es para ejecutar algo
 * antes de arrancar la aplicacion para realizar alguna tarea
 * es el lugar ieal para generar las contraeñas 
 * 1. Ncesitamos primero inyectar  el beans BycriptpasswordEncoder
 * inyecctamso con @Autowired, lo sugiente es tener la clace que estara
 * en el metodo run por ejemplo unstring y vamos a generar cuatro contraseñas
 * encriptadas para la misma clave para el msmo string usamos un for que
 * cminece en 0  a  4 dentro de for tendremos otro password pero 
 * encriptado */
@SpringBootApplication
public class SpringBootBackendApirest1Application implements  CommandLineRunner{
	@Autowired
	
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApirest1Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		String password = "12345";
		
		for(int i = 0; i<4; i++) {
			String  passwordBcrypt = passwordEncoder.encode(password);
 			System.out.println(passwordBcrypt);
			
		}
	}

}
