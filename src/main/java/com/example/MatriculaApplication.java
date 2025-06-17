package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.entidad.Usuario;
import com.example.repositories.UsuarioRepository;

import util.Rol;

@SpringBootApplication
public class MatriculaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatriculaApplication.class, args);
	}


	//Creamos un usuario para hacer pruebas 
	@Bean
	CommandLineRunner commandLineRunner (UsuarioRepository usuarioRepository, PasswordEncoder encoder){
		return args -> {
			System.out.println("Creando usuario");
			if (usuarioRepository.findByCorreo("durand@gmail.com").isEmpty()) { //Ponemos la condicional para ver si exite  no lo crea , pero si no exite lo crea en la databse
				Usuario usuario = new Usuario();
				usuario.setTelefono("123456789");
				usuario.setApellido("durand");
				usuario.setNombre("durand");
				usuario.setCorreo("durand@gmail.com");
				usuario.setPassword(encoder.encode("123456"));
				usuario.setRol(Rol.ADMIN);
				usuarioRepository.save(usuario);
			}
		};
	}
		

}
