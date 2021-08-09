/*
 * 
 	The application will provide the following JSON web services:
	/api/user/ (GET): return the list of all users.
	/api/user/{username}/ (GET): return a single user.
	/api/user/ (POST): create a user.
	/api/user/{username}/ (PUT): update the information of a single user.
	/api/user/{username}/ (DELETE): delete a single user.
	/api/user/generate/{number}/ (GET): generate a number, provided as a parameter, of random users.
		To create the users you have to use the Random User Generator service. Users
		will be added to the collection of existing users.
	
 */


package com.ricgonmen.ms_user.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MsUserController {
	private final UserRepository usuarioRepositorio;
	
	MsUserController(UserRepository usuarioRepositorio) {
		log.info("*** Repo inyectado");
	    this.usuarioRepositorio = usuarioRepositorio;
	}

	/**
	 * Obtenemos todos los usuarios
	 * /api/user/ (GET): return the list of all users.
	 * @return
	 */
	@GetMapping("/user")
	public List<User> obtenerTodos() {
		log.info("*** Recuperando todos los usuarios");
		return usuarioRepositorio.findAll();
	}

	/**
	 * Obtenemos un usuario en base a su username
	 * /api/user/{username}/ (GET): return a single user.
	 * @param username
	 * @return Null si no encuentra el usuario
	 */
	@GetMapping("/user/{username}")
	public User obtenerUnoPorUsername(@PathVariable String username) {
		log.info("*** Recuperando el usuario username=" + username);
		return usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
	}

	/**
	 * Insertamos un nuevo usuario
	 * /api/user/ (POST): create a user.
	 * @param nuevo
	 * @return usuario insertado
	 */
	@PostMapping("/user")
	public User nuevousuario(@RequestBody User nuevo) {
		log.info("*** Añadiendo el usuario " + nuevo.toString());
		return usuarioRepositorio.save(nuevo);
	}
	
	/**
	 * /api/user/{username}/ (PUT): update the information of a single user.
	 * @param editar
	 * @param username
	 * @return
	 */
	@PutMapping("/user/{username}")
	public User editarusuarioPorUsername(@RequestBody User editar, @PathVariable String username) {
		log.info("*** Editando el usuario '" +editar.toString() + "' con username " + username);
		
		User user = usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

		if (user != null) {
			editar.setId(user.getId());
			return usuarioRepositorio.save(editar);
		} else {
			return null;
		}
	}

	
	/**
	 * Borra un usuario del catálogo en base a su username
	 * /api/user/{username}/ (DELETE): delete a single user.
	 * @param id
	 * @return
	 */
	@DeleteMapping("/user/{username}")
	public User borrarusuarioPorUsername(@PathVariable String username) {
		log.info("*** Borrando el usuario username=" + username);
		
		User user = usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

		if (user != null) {
			usuarioRepositorio.deleteById(user.getId());
			return user;
		} else
			return null;
	}

	/**
	 * /api/user/generate/{number}/ (GET): generate a number, provided as a parameter, of random users.
	To create the users you have to use the Random User Generator service. Users
	will be added to the collection of existing users.
	 * @param username
	 * @return Null si no encuentra el usuario
	 */
	@GetMapping("/user/generate/{number}")
	public List<User> generarUsuariosRandom(@PathVariable Long number) {
		log.info("*** Creando " + number + " usuarios aleatorios.");
		// return usuarioRepositorio.findById(username).orElse(null);
		return null;
	}
}
